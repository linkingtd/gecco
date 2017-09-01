package com.geccocrawler.gecco.downloader.autoproxy.proxy.task;

import java.io.IOException;
import org.apache.http.HttpHost;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.log4j.Logger;
import com.geccocrawler.gecco.downloader.autoproxy.core.util.Constants;
import com.geccocrawler.gecco.downloader.autoproxy.proxy.TempProxyPool;
import com.geccocrawler.gecco.downloader.autoproxy.proxy.TestHttpClient;
import com.geccocrawler.gecco.downloader.autoproxy.proxy.entity.Page;
import com.geccocrawler.gecco.downloader.autoproxy.proxy.entity.TempProxy;
import com.geccocrawler.gecco.downloader.proxy.Proxy;
import com.geccocrawler.gecco.downloader.proxy.ProxyPool;

/**
 * 代理检测task
 * 通过访问知乎首页，能否正确响应
 * 将可用代理添加到DelayQueue延时队列中
 */
public class ProxyTestTask implements Runnable{
    private final static Logger logger = Logger.getLogger(ProxyTestTask.class);
    private TempProxy proxy;
    public ProxyTestTask(TempProxy proxy){
        this.proxy = proxy;
    }
    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        HttpGet request = new HttpGet(Constants.INDEX_URL);
        try {
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(Constants.TIMEOUT).
                    setConnectTimeout(Constants.TIMEOUT).
                    setConnectionRequestTimeout(Constants.TIMEOUT).
                    setProxy(new HttpHost(proxy.getIp(), proxy.getPort())).
                    setCookieSpec(CookieSpecs.STANDARD).
                    build();
            request.setConfig(requestConfig);
            Page page = new TestHttpClient().getWebPage(request);
            long endTime = System.currentTimeMillis();
            String logStr = Thread.currentThread().getName() + " " + proxy.getProxyStr() +
                    "  executing request " + page.getUrl()  + " response statusCode:" + page.getStatusCode() +
                    "  request cost time:" + (endTime - startTime) + "ms";
            if (page == null || page.getStatusCode() != 200){
                logger.warn(logStr);
                return;
            }
            request.releaseConnection();

            if(!TempProxyPool.proxySet.contains(proxy)){
                logger.info(proxy.toString() + "----------代理可用--------请求耗时:" + (endTime - startTime) + "ms");
                TempProxyPool.lock.writeLock().lock();
                try {
                    TempProxyPool.proxySet.add(proxy);
                } finally {
                    TempProxyPool.lock.writeLock().unlock();
                }
                //ProxyPool.proxyQueue.add(proxy);
                Proxy proxy2 = new com.geccocrawler.gecco.downloader.proxy.Proxy(proxy.getIp(), proxy.getPort(), null, null);
                ProxyPool.proxyQueue.add(proxy2);
            }
        } catch (IOException e) {
            logger.debug("IOException:", e);
        } finally {
            if (request != null){
                request.releaseConnection();
            }
        }
    }
}
