package com.util.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/test")
public class Test extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> map = new HashMap<>();

        //判断上传的文件是普通的表单还是带文件的表单
        if(!ServletFileUpload.isMultipartContent(request)) {
            return;
        }

        //创建上传文件的保存路径，建议在WEB-INF目录下，安全，用户无法直接访问上传的文件
        String uploadPath = this.getServletContext().getRealPath("WEB-IF/upload");
        File uploadFile = new File(uploadPath);
        if(!uploadFile.exists()) {
            uploadFile.mkdir();
        }

        //缓存，临时文件
        //临时路径，将超出预期大小的文件放在临时文件中，过几天自动删除，或者由用户转为永久文件
        String tmpPath = this.getServletContext().getRealPath("WEB-IF/tmp");
        File tmpFile = new File(tmpPath);
        if(!tmpFile.exists()) {
            tmpFile.mkdir();
        }

        //处理上传的文件，通常用流来获取，使用request.getInputStream()，原生态的文件上传流获取，
        //可以使用Apache文件上传组件来实现，common-fileupload，需要依赖于common-io组件

        //创建DishFileItemFactory对象，处理文件上传路径或者缓冲区大小限制

        //获取ServletFileUpload，监听文件上传进度，处理乱码，设置单个上传文件的大小，设置总共能够上传文件的大小


        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}