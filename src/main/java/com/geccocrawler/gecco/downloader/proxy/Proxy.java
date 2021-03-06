package com.geccocrawler.gecco.downloader.proxy;

import java.util.concurrent.atomic.AtomicLong;
import org.apache.http.HttpHost;

/**
 * 代理对象 封装了HttpHost对象
 * @author ddd
 *
 */
public class Proxy{
	
	private HttpHost httpHost;
	
	//20次请求内每个代理ip成功的个数
	private AtomicLong successCount;
	//20次请求内每个代理ip失败的个数
	private AtomicLong failureCount;
	
	private String src;//来源
	
	//和一个httpHost对应
	private String username;
	private String password;
	//代理ip是否可用
	private boolean availableFlag;
	
	public Proxy(String host, int port,String username,String password) {
		this.httpHost = new HttpHost(host, port);
		this.src = "custom";
		this.successCount = new AtomicLong(0);
		this.failureCount = new AtomicLong(0);
		this.username = username;
		this.password = password;
	}

	public HttpHost getHttpHost() {
		return httpHost;
	}

	public void setHttpHost(HttpHost httpHost) {
		this.httpHost = httpHost;
	}

	public AtomicLong getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(AtomicLong successCount) {
		this.successCount = successCount;
	}

	public AtomicLong getFailureCount() {
		return failureCount;
	}

	public void setFailureCount(AtomicLong failureCount) {
		this.failureCount = failureCount;
	}
	
	public String getIP() {
		return this.getHttpHost().getHostName();
	}
	
	public int getPort() {
		return this.getHttpHost().getPort();
	}

	public String toHostString() {
		return httpHost.toHostString();
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAvailableFlag() {
		return availableFlag;
	}

	public void setAvailableFlag(boolean availableFlag) {
		this.availableFlag = availableFlag;
	}

	@Override
	public String toString() {
		return "Proxy [httpHost=" + httpHost + ", successCount=" + successCount + ", failureCount=" + failureCount
				+ ", src=" + src + ", username=" + username + ", password=" + password + ", availableFlag="
				+ availableFlag + "]";
	}
}
