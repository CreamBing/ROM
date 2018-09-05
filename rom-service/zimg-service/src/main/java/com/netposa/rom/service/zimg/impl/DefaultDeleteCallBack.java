package com.netposa.rom.service.zimg.impl;

import com.netposa.rom.common.httpasyncclient.HttpAsyncClientUtils;
import com.netposa.rom.common.zimg.httpasyncclient.DeleteCallBack;
import org.apache.http.HttpResponse;
import org.apache.http.client.utils.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultDeleteCallBack implements DeleteCallBack {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultDeleteCallBack.class);

    @Override
    public void completed(HttpResponse httpResponse) {
        LOGGER.info(httpResponse.getStatusLine().getStatusCode()+":"+HttpAsyncClientUtils.getHttpContent(httpResponse));
        HttpClientUtils.closeQuietly(httpResponse);
    }

    @Override
    public void failed(Exception e) {

    }

    @Override
    public void cancelled() {

    }
}
