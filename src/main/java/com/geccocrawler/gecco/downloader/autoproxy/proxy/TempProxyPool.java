package com.geccocrawler.gecco.downloader.autoproxy.proxy;


import static com.geccocrawler.gecco.downloader.autoproxy.core.util.Constants.TIME_INTERVAL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.geccocrawler.gecco.downloader.autoproxy.proxy.entity.Direct;
import com.geccocrawler.gecco.downloader.autoproxy.proxy.entity.TempProxy;
import com.geccocrawler.gecco.downloader.autoproxy.proxy.site.ip181.Ip181ProxyListPageParser;
import com.geccocrawler.gecco.downloader.autoproxy.proxy.site.ip66.Ip66ProxyListPageParser;
import com.geccocrawler.gecco.downloader.autoproxy.proxy.site.mimiip.MimiipProxyListPageParser;
import com.geccocrawler.gecco.downloader.autoproxy.proxy.site.xicidaili.XicidailiProxyListPageParser;

/**
 * 代理池
 */
public class TempProxyPool {
    /**
     * proxySet读写锁
     */
    public final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public final static Set<TempProxy> proxySet = new HashSet<TempProxy>();
    /**
     * 代理池延迟队列
     */
    public final static DelayQueue<TempProxy> proxyQueue = new DelayQueue();
    public final static Map<String, Class> proxyMap = new HashMap<>();
    static {
        int pages = 2;
        for(int i = 1; i <= pages; i++){
//            proxyMap.put("http://www.xicidaili.com/wt/" + i + ".html", XicidailiProxyListPageParser.class);
//            proxyMap.put("http://www.xicidaili.com/nn/" + i + ".html", XicidailiProxyListPageParser.class);
            proxyMap.put("http://www.xicidaili.com/wn/" + i + ".html", XicidailiProxyListPageParser.class);
//            proxyMap.put("http://www.xicidaili.com/nt/" + i + ".html", XicidailiProxyListPageParser.class);
//            proxyMap.put("http://www.ip181.com/daili/" + i + ".html", Ip181ProxyListPageParser.class);
//            proxyMap.put("http://www.mimiip.com/gngao/" + i, MimiipProxyListPageParser.class);//高匿
//            proxyMap.put("http://www.mimiip.com/gnpu/" + i, MimiipProxyListPageParser.class);//普匿
//            proxyMap.put("http://www.66ip.cn/" + i + ".html", Ip66ProxyListPageParser.class);
//            for(int j = 1; j < 34; j++){
//                proxyMap.put("http://www.66ip.cn/areaindex_" + j + "/" + i + ".html", Ip66ProxyListPageParser.class);
//            }
        }
        proxyQueue.add(new Direct(TIME_INTERVAL));
    }

}
