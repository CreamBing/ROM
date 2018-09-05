package com.netposa.rom.common.zimg;

import com.netposa.rom.common.httpasyncclient.HttpAsyncClientUtils;
import com.netposa.rom.common.zimg.bean.ZimgFile;
import com.netposa.rom.common.zimg.constants.Constants;
import com.netposa.rom.common.zimg.httpasyncclient.DeleteCallBack;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.nutz.http.Request;
import org.nutz.http.Response;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * zimg客户端 使用方法如下： ZimgClient zimgClient = new
 * ZimgClient("http://192.168.2.199:4869"); ZimgInfo upload =
 * zimgClient.upload("d:\\tmp\\timg.jpg");
 * 
 * @author mulhayc
 */
@Data
public class ZimgClient {

	private String baseUrl;

	private DeleteCallBack deleteCallBack;

	public ZimgClient(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public ZimgFile upload(File file) {
		Request req = Request.create(baseUrl + "/upload", Request.METHOD.POST);
		req.getParams().put("file", file);
		return upload(req);
	}

    public ZimgFile upload(Request req){
	    boolean isSuccess = false;
        ZimgFilePostSender sender = new ZimgFilePostSender(req);
        Response upload = sender.send();
        String content = upload.getContent();
        ZimgFile zimgInfo = new ZimgFile();
        if(StringUtils.isNotBlank(content)){
            Document document = Jsoup.parse(content);
            Element h1 = document.getElementsByTag("h1").get(0);
            if(h1.hasText()){
                String h1test = h1.text();
                if(h1test.contains(Constants.SUCCESS_FLAG)){
                    String md5 = md5(h1test);
                    if(StringUtils.isNotBlank(md5)){
                        if(StringUtils.isNotBlank(baseUrl)){
                            String url;
                            if(baseUrl.endsWith("/")){
                                url = baseUrl + md5;
                            }else {
                                url = baseUrl+"/"+md5;
                            }
                            zimgInfo.setZimgPath(url);
                            zimgInfo.setZimgName(md5);
                            isSuccess = true;
                        }
                    }
                }
            }
        }
        if(isSuccess){
            return zimgInfo;
        }else {
            return null;
        }
    }

	public ZimgFile upload(String file) {
		return upload(new File(file));
	}

	public ZimgFile upload(InputStream inputStream) {
		Request req = Request.create(baseUrl + "/upload", Request.METHOD.POST);
		req.getParams().put("file", inputStream);
		return upload(req);
	}

	public String md5(String h1){
        Pattern r = Pattern.compile(Constants.MAD5_PATTERN);
        Matcher m = r.matcher(h1);
        if(m.find()){
            return m.group(1);
        }else {
            return null;
        }
    }

    public void delete(String md5) throws Exception{
	    String deleteUrl = baseUrl+Constants.DELETE_URL;
        List<BasicNameValuePair> urlParams = new ArrayList<>();
        urlParams.add(new BasicNameValuePair("md5", md5));
        urlParams.add(new BasicNameValuePair("t", "1"));
        HttpAsyncClientUtils.exeAsyncReq(deleteUrl, false, urlParams, null, deleteCallBack);
    }



    public static void main(String[] args) {
        String s = "MD5: 5257c117e3ab37393b419a8962e6d247";
        Pattern r = Pattern.compile(Constants.MAD5_PATTERN);
        Matcher m = r.matcher(s);
        if(m.find()){
            System.out.println(m.group(1));
        }
    }

}
