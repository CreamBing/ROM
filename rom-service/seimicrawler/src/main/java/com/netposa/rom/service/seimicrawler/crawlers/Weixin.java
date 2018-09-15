package com.netposa.rom.service.seimicrawler.crawlers;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.struct.Response;
import com.netposa.rom.service.seimicrawler.thread.ThreadDownloadImg;
import org.seimicrawler.xpath.JXDocument;

import java.util.List;

/**
 * <p>Title: Weixin</p>
 * <p>Description: 为孙菊霞做的</p>
 *
 * @author bing
 * @date 2018/9/15
 * @Company 东方网力
 */
@Crawler(name = "weixin")
public class Weixin extends BaseSeimiCrawler {

    String host = "https://mp.weixin.qq.com";
    String startUrl = host + "/s?__biz=MjM5NjgzMzkwMQ==&mid=2653646951&idx=1&sn=91eb95724a15762f333393283435644d&chksm=bd3cef658a4b66738811c3a429386fa98ea7475adf905c6d12cca641e926ebfd2bc1959d1a89&mpshare=1&scene=1&srcid=091558u2GBo5mSeg9749VEu3#rd";

    @Override
    public String[] startUrls() {
        //两个是测试去重的
        return new String[]{startUrl};
    }

    @Override
    public void start(Response response) {
        JXDocument doc = response.document();
        try {
            List<Object> imgs = doc.sel("//img/@data-src");
            for (Object o : imgs) {
                System.out.println(o.toString());
                download(o.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void download(JXDocument doc) {
        List<Object> urls = doc.sel("//img[@class='BDE_Image']/@src");
        urls.forEach(e -> {
            Thread thread = new Thread(new ThreadDownloadImg(e.toString()));
            thread.start();
        });
    }

    private void download(String url) {
        Thread thread = new Thread(new ThreadDownloadImg(url));
        thread.start();
    }

}
