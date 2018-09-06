package service;

import com.netposa.ZimgApplication;
import com.netposa.rom.common.httpasyncclient.HttpAsyncClientUtils;
import com.netposa.rom.common.zimg.constants.Constants;
import com.netposa.rom.service.zimg.callback.impl.DeleteFromMysqlCallBackImpl;
import com.netposa.rom.service.zimg.mysql.ZimgMysqlService;
import com.netposa.rom.service.zimg.mysql.impl.ZimgMysqlServiceImpl;
import com.netposa.rom.service.zimg.utils.SpringContextUtils;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = ZimgApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class TestHttpAsyncDelete {

    @Test
    public void testhttpasyncdelete() throws Exception {
        String baseUrl = "http://192.168.30.128:4869";
        String md5 = "5257c117e3ab37393b419a8962e6d247";
        String deleteUrl = baseUrl+ Constants.DELETE_URL;
        List<BasicNameValuePair> urlParams = new ArrayList<>();
        urlParams.add(new BasicNameValuePair("md5", md5));
        urlParams.add(new BasicNameValuePair("t", "1"));
//        HttpClientUtil.httpAsyncGet(deleteUrl, urlParams,  new DeleteFromMysqlCallBackImpl());
        HttpAsyncClientUtils.exeAsyncReq(deleteUrl, false, urlParams, null, new DeleteFromMysqlCallBackImpl());
    }

    @Test
    public void testDeleteMysql(){
        String md5 = "5257c117e3ab37393b419a8962e6d247";
        ZimgMysqlService mysqlService = SpringContextUtils.getBean(ZimgMysqlServiceImpl.class);
        mysqlService.deleteByMD5(md5);
    }
}
