package service;

import com.alibaba.fastjson.JSON;
import com.netposa.ZimgApplication;
import com.netposa.rom.common.zimg.bean.ZimgFile;
import com.netposa.rom.service.zimg.ZimgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ZimgApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class TestZimgService {

    @Autowired
    ZimgService zimgService;

    @Test
    public void upload(){
        String img = "C:\\Users\\lenovo\\Pictures\\Camera Roll\\timg.jpg";
        ZimgFile zimgFile = zimgService.upload(img);
        System.out.println(JSON.toJSONString(zimgFile));
    }

    @Test
    public void delete(){
        String md5 = "5257c117e3ab37393b419a8962e6d247";
        zimgService.delete(md5);
    }
}
