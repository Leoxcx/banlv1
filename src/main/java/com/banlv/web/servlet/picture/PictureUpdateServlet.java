package com.banlv.web.servlet.picture;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.banlv.bean.Picture;
import com.banlv.service.PictureService;
import com.banlv.service.impl.PictureServiceImpl;
import com.util.utils.MyTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/pictureupdateservlet")
public class PictureUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        Map<String, String[]> parameterMap = request.getParameterMap();

        Picture picture= new Picture();
        Picture picturefromWeb = new MyTool<Picture>(Picture.class).getObjectFromWeb(picture, parameterMap);

        PictureService pictureService = new PictureServiceImpl();
        int i = pictureService.updatePicture(picturefromWeb);

        Map<String,Object> map = new HashMap<>();
        if (i == 1) map.put("message",true);
        else map.put("message",false);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
