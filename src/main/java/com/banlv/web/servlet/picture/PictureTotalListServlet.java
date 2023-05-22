package com.banlv.web.servlet.picture;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.banlv.bean.Picture;
import com.banlv.service.PictureService;
import com.banlv.service.impl.PictureServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/picturetotallistservlet")
public class PictureTotalListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        PictureService pictureService = new PictureServiceImpl();
        List<Picture> pictures = pictureService.findAll();

        Map<String,Object> map = new HashMap<>();
        map.put("pictures",pictures);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}