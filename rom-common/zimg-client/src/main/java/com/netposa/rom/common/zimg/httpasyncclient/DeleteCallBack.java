package com.netposa.rom.common.zimg.httpasyncclient;

import org.apache.http.HttpResponse;
import org.apache.http.concurrent.FutureCallback;

public interface DeleteCallBack extends FutureCallback<HttpResponse> {

    @Override
    void completed(HttpResponse httpResponse);

    @Override
    void failed(Exception e);

    @Override
    void cancelled();
}
