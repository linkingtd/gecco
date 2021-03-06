package com.geccocrawler.gecco.downloader.autoproxy.proxy.site.ip66;


import static com.geccocrawler.gecco.downloader.autoproxy.core.util.Constants.TIME_INTERVAL;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.geccocrawler.gecco.downloader.autoproxy.core.parser.ProxyListPageParser;
import com.geccocrawler.gecco.downloader.autoproxy.proxy.entity.TempProxy;

/**
 * http://www.66ip.cn/
 */
public class Ip66ProxyListPageParser implements ProxyListPageParser {
    @Override
    public List<TempProxy> parse(String content) {
        List<TempProxy> proxyList = new ArrayList<>();
        if (content == null || content.equals("")){
            return proxyList;
        }
        Document document = Jsoup.parse(content);
        Elements elements = document.select("table tr:gt(1)");
        for (Element element : elements){
            String ip = element.select("td:eq(0)").first().text();
            String port  = element.select("td:eq(1)").first().text();
            String isAnonymous = element.select("td:eq(3)").first().text();
//            if(!anonymousFlag || isAnonymous.contains("匿")){
                proxyList.add(new TempProxy(ip, Integer.valueOf(port), TIME_INTERVAL));
//            }
        }
        return proxyList;
    }
}
