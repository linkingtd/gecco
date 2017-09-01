package com.geccocrawler.gecco.downloader.proxy;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.DelayQueue;

/**
 * 代理池
 * 
 * @author ddd
 * @version 创建时间：2017年8月31日 下午11:45:35
 * 
 */
public class ProxyPool {
	//public static DelayQueue<Proxy> proxyQueue = new DelayQueue<Proxy>();
	public static ConcurrentLinkedQueue<Proxy> proxyQueue = new ConcurrentLinkedQueue<>();
}
