package com.geccocrawler.gecco.downloader.proxy;

/**
 * 代理对象的操作,比如添加
 * @author ddd
 *
 */
public interface Proxys {
	
	public Proxy getProxy();

	public boolean addProxy(String host, int port,String username,String password);
	
	public boolean addProxy(String host, int port,String username,String password, String src);
	
	public void failure(String host, int port,String username,String password);
	
	public void success(String host, int port,String username,String password);
	
}
