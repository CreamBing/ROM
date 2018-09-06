package com.netposa.rom.service.zimg.callback;

import com.netposa.rom.common.zimg.constants.Constants;
import com.netposa.rom.service.zimg.mysql.ZimgMysqlService;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.utils.HttpClientUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@Component
public class DeleteFromMysqlThread implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteFromMysqlThread.class);

    private String res;
    private ZimgMysqlService mysqlService;
    private HttpResponse httpResponse;

    @Override
    public void run() {
        if (StringUtils.isBlank(res)) {
            LOGGER.error("zimg删除失败:[{}]", res);
            return;
        }
        boolean deleteFlag = false;
        Document document = Jsoup.parse(res);
        Element h1 = document.getElementsByTag("h1").get(0);
        String md5 = "", h1test, failContent = "";
        if (h1.hasText()) {
            h1test = h1.text();
            Element p1 = h1.nextElementSibling();
            if (p1.hasText()) {
                String p1Test = p1.text();
                md5 = md5(p1Test);
            }
            if (h1test.contains(Constants.DELETE_SUCCESS_FLAG)) {
                deleteFlag = true;
            } else {
                Element failp = h1.lastElementSibling();
                failContent = failp.text();
            }
        }
        if (deleteFlag) {
            LOGGER.info("zimg[{}]删除成功!", md5);
            mysqlService.deleteByMD5(md5);
        } else {
            LOGGER.error("zimg[{}]删除失败:[{}]!", md5, failContent);
        }
        HttpClientUtils.closeQuietly(httpResponse);
    }

    public String md5(String h1) {
        Pattern r = Pattern.compile(Constants.MAD5_PATTERN);
        Matcher m = r.matcher(h1);
        if (m.find()) {
            return m.group(1);
        } else {
            return null;
        }
    }
}
