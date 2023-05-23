package com.banlv.web.servlet.DTO.upload;

import com.banlv.bean.Picture;
import com.banlv.bean.Resource;
import com.banlv.service.impl.PictureServiceImpl;
import com.banlv.service.impl.ResourceServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.util.utils.QiniuCloudUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.util.utils.UploadUtil.*;

//后端上传本地图片
@WebServlet("/uploadpicture")
public class QiniuCloudUpload extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Map<String, Object> map = new HashMap<>();
        ResourceServiceImpl resourceService = new ResourceServiceImpl();
        List<Resource> all1 = resourceService.findAll();
        List<Resource> obj = new ArrayList<>();

        for (Resource p : all1) {
            String picture_url = p.getResource_url();


            if (picture_url.length() >4 && picture_url.substring(picture_url.length() - 4).equals(".jpg")) {
                obj.add(p);
            }
        }
        System.out.println("num" + obj.size());
        for (Resource pi : obj) {
            new QiniuTool().upload(pi.getResource_url());
        }
//
//        PictureServiceImpl pictureService = new PictureServiceImpl();
//        List<Picture> all = pictureService.findAll();
//        List<Picture> myAll = new ArrayList<>();
//        for (Picture p : all) {
//            String picture_url = p.getPicture_url();
//            String last = picture_url.substring(picture_url.length() - 4);
//
//            if (last.equals(".jpg")) {
//                myAll.add(p);
//            }
//        }
//        System.out.println("num" + myAll.size());
//        for (Picture pi : myAll) {
//            new QiniuTool().upload(pi.getPicture_url());
//        }





        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), map);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}


//
