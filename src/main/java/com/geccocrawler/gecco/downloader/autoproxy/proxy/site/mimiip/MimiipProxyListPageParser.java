package com.geccocrawler.gecco.downloader.autoproxy.proxy.site.mimiip;


import static com.geccocrawler.gecco.downloader.autoproxy.core.util.Constants.TIME_INTERVAL;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import com.geccocrawler.gecco.downloader.autoproxy.core.parser.ProxyListPageParser;
import com.geccocrawler.gecco.downloader.autoproxy.proxy.entity.TempProxy;

public class MimiipProxyListPageParser implements ProxyListPageParser{
    @Override
    public List<TempProxy> parse(String hmtl) {
        Document document = Jsoup.parse(hmtl);
        Elements elements = document.select("table[class=list] tr");
        List<TempProxy> proxyList = new ArrayList<>(elements.size());
        for (int i = 1; i < elements.size(); i++){
            String isAnonymous = elements.get(i).select("td:eq(3)").first().text();
            if(!anonymousFlag || isAnonymous.contains("匿")){
                String ip = elements.get(i).select("td:eq(0)").first().text();
                String port  = elements.get(i).select("td:eq(1)").first().text();
                proxyList.add(new TempProxy(ip, Integer.valueOf(port), TIME_INTERVAL));
            }
        }
        return proxyList;
    }
}
