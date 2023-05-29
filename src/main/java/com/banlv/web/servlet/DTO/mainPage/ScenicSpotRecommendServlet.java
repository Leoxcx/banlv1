package com.banlv.web.servlet.DTO.mainPage;

import com.banlv.bean.Resource;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.util.utils.transTool.TransTool.spotToResource;

//景点scenicSpot_id获取资源（前十个）
@WebServlet("/spotrecommendservlet")
public class ScenicSpotRecommendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        Map<String,Object> map = new HashMap<>();

//        前端获取SpotId

        String spotId = request.getParameter("scenicSpot_id");


        //景点资源中间表
        if(StringUtils.isEmpty(spotId)) {
            map.put("msg", false);
            map.put("resources", null);
        }else {

            List<Resource> resources = spotToResource(Long.parseLong(spotId));
            if (resources.isEmpty()) {
                map.put("msg", false);
                map.put("resources", null);
            }
            map.put("msg", true);
            map.put("resources", resources);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }


}
