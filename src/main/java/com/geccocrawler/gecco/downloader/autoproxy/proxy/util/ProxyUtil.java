package com.geccocrawler.gecco.downloader.autoproxy.proxy.util;
import com.geccocrawler.gecco.downloader.autoproxy.proxy.entity.TempProxy;
public class ProxyUtil {
    /**
     * 是否丢弃代理
     * 失败次数大于３，且失败率超过60%，丢弃
     */
    public static boolean isDiscardProxy(TempProxy proxy){
        int succTimes = proxy.getSuccessfulTimes();
        int failTimes = proxy.getFailureTimes();
        if(failTimes >= 3){
            double failRate = (failTimes + 0.0) / (succTimes + failTimes);
            if (failRate > 0.6){
                return true;
            }
        }
        return false;
    }
}
