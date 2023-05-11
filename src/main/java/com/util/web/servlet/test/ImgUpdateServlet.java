package com.util.web.servlet.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@WebServlet("/imageupdateservlet")
public class ImgUpdateServlet extends HttpServlet {
    private boolean isMultipart;
    private int maxFileSize = 1024 * 1024 * 10;
    private int maxMemSize = 100 * 1024;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 检查是否有一个文件上传请求
        isMultipart = ServletFileUpload.isMultipartContent(request);
        boolean result = false;
        response.setContentType("text/html;charset=utf-8");
        if (!isMultipart) {
            result = false;
            response.getWriter().println(result);
            return;
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 文件大小的最大值将被存储在内存中
        factory.setSizeThreshold(maxMemSize);
        // Location to save data that is larger than maxMemSize.
//        String path = getServletContext().getRealPath("/") + "/";
        String path = "C:\\Users\\Administrator\\Desktop\\Main.0000_(3)";
        factory.setRepository(new File(path));
        // System.out.println(path);
        // 创建一个新的文件上传处理程序
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 允许上传的文件大小的最大值
        upload.setSizeMax(maxFileSize);

        try {
            // 解析请求，获取文件项
            List fileItems = upload.parseRequest(request);
            // 处理上传的文件项
            Iterator i = fileItems.iterator();
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (!fi.isFormField()) {
                    // 获取上传文件的参数
                    String fieldName = fi.getFieldName();
                    String fileName = fi.getName();
                    String contentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();
                    // 写入文件
                    File file = new File(path +"\\" +  System.currentTimeMillis() / 1000 + fileName);
                    fi.write(file);
                }
            }
            result = true;
        } catch (Exception ex) {
            System.out.println("ex:" + ex.getMessage());
            result = false;
        }

        Map<String,Object> map = new HashMap<>();
        if (result){
            map.put("msg",true);
        }else {
            map.put("msg",false);
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
        response.getWriter().println(result);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

}
