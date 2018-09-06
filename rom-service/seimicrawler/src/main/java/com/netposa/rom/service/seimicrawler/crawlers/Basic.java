package com.netposa.rom.service.seimicrawler.crawlers;


import ch.qos.logback.core.net.SyslogOutputStream;
import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import com.netposa.rom.service.seimicrawler.thread.ThreadDownloadImg;
import oracle.jrockit.jfr.jdkevents.ThrowableTracer;
import org.seimicrawler.xpath.JXDocument;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 汪浩淼 [et.tw@163.com]
 * @since 2015/10/21.
 */
@Crawler(name = "basic")
public class Basic extends BaseSeimiCrawler {

    String host = "http://tieba.baidu.com";
    String startUrl = host + "/p/5016084556?pn=1";
    List<String> allUrls = new ArrayList<>();

    @Override
    public String[] startUrls() {
        //两个是测试去重的
        return new String[]{startUrl};
    }

    @Override
    public void start(Response response) {
        JXDocument doc = response.document();
        try {
            download(doc);
            allUrls.add(startUrl);
            Object next = doc.selOne("//a[contains(text(), \"下一页\")]/@href");
            if (next != null) {
                push(Request.build(host + next.toString(), Basic::getNext));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void download(JXDocument doc){
        List<Object> urls = doc.sel("//img[@class='BDE_Image']/@src");
        urls.forEach(e -> {
            Thread thread = new Thread(new ThreadDownloadImg(e.toString()));
            thread.start();
        });
    }

    public void getNext(Response response) {
        JXDocument doc = response.document();
        try {
            download(doc);
            Object next = doc.selOne("//a[contains(text(), \"下一页\")]/@href");
            allUrls.add(response.getUrl());
            if (next != null) {
                logger.info("url:{} {}", response.getUrl(), next.toString());
                push(Request.build(host + next.toString(), Basic::getNext));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void getTitle(Response response) {
//        JXDocument doc = response.document();
//        try {
//            logger.info("url:{} {}", response.getUrl(), doc.sel("//h1[@class='postTitle']/a/text()|//a[@id='cb_post_title_url']/text()"));
//            //do something
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}