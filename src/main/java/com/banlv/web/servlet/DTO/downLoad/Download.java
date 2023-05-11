package com.banlv.web.servlet.DTO.downLoad;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiniu.storage.DownloadUrl;
import com.qiniu.util.Auth;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/download")
public class Download extends HttpServlet {

//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder().url(privateDownloadUrl).build();
//        Response response = client.newCall(request).execute();
//        System.out.println(response.body().string());
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> map = new HashMap<>();

        String key = "1_1(1).mp4";
        String accessKey = "6Is6dqNLykME-nZW0IllnAWljpBFJquJgcYHHIuY";
        String secretKey = "0m2-9F0UNicAZePqlAMZHVl7xgVRcOVn6in8MKke";

        // 要上传的空间
        String bucket = "banlv1";

        //七牛云域名
        String domainOfBucket = "http://rrjtoy5ol.hn-bkt.clouddn.com";


        Auth auth = Auth.create(accessKey, secretKey);
        String privateDownloadUrl = auth.privateDownloadUrl(domainOfBucket + "/" + key, 61);
        System.out.println(privateDownloadUrl);

        map.put("msg", true);
        map.put("DownloadURL", privateDownloadUrl);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

//        String fileName = "公司/存储/qiniu.jpg";
//        String domainOfBucket = "http://devtools.qiniu.com";
//        String encodedFileName = URLEncoder.encode(fileName, "utf-8").replace("+", "%20");
//        String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
//        String accessKey = "your access key";
//        String secretKey = "your secret key";
//        Auth auth = Auth.create(accessKey, secretKey);
//        long expireInSeconds = 3600;//1小时，可以自定义链接过期时间
//        String finalUrl = auth.privateDownloadUrl(publicUrl, expireInSeconds);
//        System.out.println(finalUrl);

// domain   下载 domain, eg: qiniu.com【必须】
// useHttps 是否使用 https【必须】
// key      下载资源在七牛云存储的 key【必须】
//        DownloadUrl url = new DownloadUrl(domain, useHttps, key);
//        url.setAttname(attname) // 配置 attname
//                .setFop(fop) // 配置 fop
//                .setStyle(style, styleSeparator, styleParam) // 配置 style
//        // 带有效期
//        long expireInSeconds = 3600;//1小时，可以自定义链接过期时间
//        Auth auth = Auth.create("your access key", "your secret key");
//        String urlString = url.buildURL(auth, deadline);
//        System.out.println(urlString);