package com.geccocrawler.gecco.downloader.autoproxy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.geccocrawler.gecco.downloader.autoproxy.proxy.ProxyHttpClient;

public class Main {
	private static Log logger = LogFactory.getLog(Main.class);
    public static void main(String args []){
        ProxyHttpClient.getInstance().startCrawl();
    }
}
