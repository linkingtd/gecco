package com.geccocrawler.gecco.downloader.autoproxy;

import org.apache.log4j.Logger;

import com.geccocrawler.gecco.downloader.autoproxy.core.util.SimpleLogger;
import com.geccocrawler.gecco.downloader.autoproxy.proxy.ProxyHttpClient;

/**
 * 爬虫入口
 */
public class Main {
    private static Logger logger = SimpleLogger.getSimpleLogger(Main.class);
    public static void main(String args []){
        ProxyHttpClient.getInstance().startCrawl();
    }
}
