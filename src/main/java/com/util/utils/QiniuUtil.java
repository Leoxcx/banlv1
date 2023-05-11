package com.util.utils;

import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;
import java.io.InputStream;
import java.util.UUID;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;

public class QiniuUtil {

    // 设置需要操作的账号的AK和SK
    private static final String accessKey = "6Is6dqNLykME-nZW0IllnAWljpBFJquJgcYHHIuY";
    private static final String secretKey = "0m2-9F0UNicAZePqlAMZHVl7xgVRcOVn6in8MKke";

    // 要上传的空间
    private static final String bucket = "banlv1";

    // 密钥
    private static final Auth auth = Auth.create(accessKey, secretKey);
    //七牛云域名
    private static final String domain = "http://rsorarkap.hd-bkt.clouddn.com/";

    /**
     * 上传文件并且返回文件地址
     *
     * @param inputStream 文件
     */
    public String setUploadManager(InputStream inputStream) {
        //设置密钥、文件连接、文件名等等属性
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huanan());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //设置连接地址
        Auth auth = Auth.create(accessKey, secretKey);
        String prefix = "";
        int Guid = 100;
        try {
            String s = auth.uploadToken(bucket);
            //生成文件名
            String s1 = UUID.randomUUID().toString().replaceAll("-","");
            //实现文件上传
            Response response = uploadManager.put(inputStream, s1, s, null, null);
            //解析上传成功结果
            DefaultPutRet defaultPutRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            //返回文件外链地址
            return domain + defaultPutRet.key;
        } catch (QiniuException e) {
            e.printStackTrace();
            return null;
        }
    }

}
