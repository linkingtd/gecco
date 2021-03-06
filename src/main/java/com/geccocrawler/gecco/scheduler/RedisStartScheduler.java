package com.geccocrawler.gecco.scheduler;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Config;
import org.redisson.Redisson;
import org.redisson.RedissonClient;
import org.redisson.core.RScoredSortedSet;

import com.geccocrawler.gecco.request.HttpRequest;

public class RedisStartScheduler implements Scheduler {

	private RScoredSortedSet<HttpRequest> set;

	public RedisStartScheduler(String address, String password) {
		Config config = new Config();
		config.useSingleServer().setAddress(address);
		if (StringUtils.isNotBlank(password)) {
			config.useSingleServer().setPassword(password);
		}
		RedissonClient redisson = Redisson.create(config);
		set = redisson.getScoredSortedSet("Gecco-StartScheduler-Redis-ScoreSet");
	}

	@Override
	public HttpRequest out() {
		HttpRequest request = set.pollFirst();
		return request;
	}

	@Override
	public void into(HttpRequest request) {
		set.add(System.nanoTime(), request);
	}
}
