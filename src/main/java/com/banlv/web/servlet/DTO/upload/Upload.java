package com.banlv.web.servlet.DTO.upload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiniu.util.Auth;
import com.util.utils.QiniuUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//前端上传
@WebServlet("/upload")
public class Upload extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Map<String, Object> map = new HashMap<>();
        String suffix = request.getParameter("suffix");
        // 设置需要操作的账号的AK和SK
        String accessKey = "6Is6dqNLykME-nZW0IllnAWljpBFJquJgcYHHIuY";
        String secretKey = "0m2-9F0UNicAZePqlAMZHVl7xgVRcOVn6in8MKke";

        // 要上传的空间
        String bucket = "banlvserver";

        //七牛云域名
//        String domain = "http://rsorarkap.hd-bkt.clouddn.com/";
        String domain = "http://cdn.uemodel.com/";
        try {
            //验证七牛云身份是否通过
            Auth auth = Auth.create(accessKey, secretKey);
            //生成凭证
            String upToken = auth.uploadToken(bucket);
            map.put("token", upToken);
            //存入外链默认域名，用于拼接完整的资源外链路径
            map.put("domain", domain);

            // 是否可以上传的图片格式
            boolean flag = false;
            String[] imgTypes = new String[]{"jpg","jpeg","bmp","gif","png"};
            for(String fileSuffix : imgTypes) {
                if(suffix.substring(suffix.lastIndexOf(".") + 1).equalsIgnoreCase(fileSuffix)) {
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                map.put("msgInfo", "图片：" + suffix + " 上传格式不对！");
            }

            //生成实际路径名
            String randomFileName = UUID.randomUUID().toString() + suffix;
            map.put("imgUrl", randomFileName);
            map.put("success", 1);
        } catch (Exception e) {
            map.put("msg", false);

        } finally {

            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getWriter(), map);
        }









    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

