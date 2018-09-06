package service;

import com.netposa.ZimgApplication;
import com.netposa.rom.service.zimg.callback.impl.DeleteFromMysqlCallBack;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ZimgApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class TestDeleteFromMysqlCallBack {

    @Test
    public void deleteFromDb(){
        String md5 = "5257c117e3ab37393b419a8962e6d247";
        DeleteFromMysqlCallBack deleteFromMysqlCallBack = new DeleteFromMysqlCallBack();
        deleteFromMysqlCallBack.deleteFromDb(md5);
    }
}
