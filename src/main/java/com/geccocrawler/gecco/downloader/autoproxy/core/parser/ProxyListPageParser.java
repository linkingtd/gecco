package com.geccocrawler.gecco.downloader.autoproxy.core.parser;
import java.util.List;
import com.geccocrawler.gecco.downloader.autoproxy.core.parser.Parser;
import com.geccocrawler.gecco.downloader.autoproxy.proxy.entity.TempProxy;

public interface ProxyListPageParser extends Parser{
    /**
     * 是否只要匿名代理
     */
    static final boolean anonymousFlag = true;
    List<TempProxy> parse(String content);
}
