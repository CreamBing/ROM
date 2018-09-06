package com.netposa.rom.service.zimg.callback;

import com.netposa.rom.common.httpasyncclient.HttpAsyncClientUtils;
import com.netposa.rom.common.zimg.constants.Constants;
import com.netposa.rom.common.zimg.httpasyncclient.DeleteCallBack;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.utils.HttpClientUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class DeleteCallBackAbstract implements DeleteCallBack{

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteCallBackAbstract.class);

    public abstract void deleteFromDb(String md5);

    @Override
    public void completed(HttpResponse httpResponse) {
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        String res = HttpAsyncClientUtils.getHttpContent(httpResponse);
        LOGGER.info(statusCode + ":" + res);
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
            deleteFromDb(md5);
        } else {
            LOGGER.error("zimg[{}]删除失败:[{}]!", md5, failContent);
        }
        HttpClientUtils.closeQuietly(httpResponse);
    }

    @Override
    public void failed(Exception e) {

    }

    @Override
    public void cancelled() {

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
