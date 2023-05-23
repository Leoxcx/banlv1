package com.banlv.web.servlet.DTO.upload;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.File;

public class QiniuTool {
    // 设置好账号的ACCESS_KEY和SECRET_KEY
    String ACCESS_KEY = "6Is6dqNLykME-nZW0IllnAWljpBFJquJgcYHHIuY";
    String SECRET_KEY = "0m2-9F0UNicAZePqlAMZHVl7xgVRcOVn6in8MKke";
    // 要上传的空间
    String bucketName = "banlvserver";
    // 上传到七牛后保存的文件名
//    String key;
    // 上传文件的路径
    String filePath = "D:\\L\\jingquspider\\scenicSpot\\";
    // 指定上传到的文件夹
    String folder = "resource";

    // 密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    // 创建上传对象
    Configuration cfg = new Configuration();
    UploadManager uploadManager = new UploadManager(cfg);

    public void upload(String path) {
        try {
            // 调用put方法上传
            Response res = uploadManager.put(filePath + path, folder + "/" + path, auth.uploadToken(bucketName));
            // 打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常信息
            System.out.println(r.toString());
            try {
                // 响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException qe) {
                // ignore
            }
        }
    }
}