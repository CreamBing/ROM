package com.netposa.rom.service.zimg.callback.impl;

import com.netposa.rom.common.httpasyncclient.HttpAsyncClientUtils;
import com.netposa.rom.common.zimg.httpasyncclient.DeleteCallBack;
import com.netposa.rom.service.zimg.callback.DeleteFromMysqlThread;
import com.netposa.rom.service.zimg.mysql.ZimgMysqlService;
import com.netposa.rom.service.zimg.mysql.impl.ZimgMysqlServiceImpl;
import com.netposa.rom.service.zimg.utils.SpringContextUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.utils.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteFromMysqlCallBackImpl implements DeleteCallBack {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteFromMysqlCallBackImpl.class);

    ZimgMysqlService mysqlService = SpringContextUtils.getBean(ZimgMysqlServiceImpl.class);

    DeleteFromMysqlThread deleteFromMysqlThread = SpringContextUtils.getBean(DeleteFromMysqlThread.class);

    @Override
    public void completed(HttpResponse httpResponse) {
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        String res = HttpAsyncClientUtils.getHttpContent(httpResponse);
        LOGGER.info(statusCode + ":" + res);
        deleteFromMysqlThread.setRes(res);
        deleteFromMysqlThread.setMysqlService(mysqlService);
        deleteFromMysqlThread.setHttpResponse(httpResponse);
        Thread thread = new Thread(deleteFromMysqlThread);
        thread.start();
    }

    @Override
    public void failed(Exception e) {

    }

    @Override
    public void cancelled() {

    }

}
