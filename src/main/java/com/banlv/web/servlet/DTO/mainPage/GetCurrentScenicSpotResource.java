package com.banlv.web.servlet.DTO.mainPage;

import com.banlv.bean.Resource;
import com.banlv.bean.ScenicSpot;
import com.banlv.bean.User_resource;
import com.banlv.service.impl.User_resourceServiceImpl;
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

import static com.util.utils.transTool.TransTool.*;

//通过经纬度获取当前景点的所有资源，并为用户添加到用户已解锁资源
@WebServlet("/getcurrentscenicspotresource")
public class GetCurrentScenicSpotResource extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> map = new HashMap<>();
        String user_id = request.getParameter("user_id");
        String scenicSpot_longitude = request.getParameter("scenicSpot_longitude");
        String scenicSpot_latitude = request.getParameter("scenicSpot_latitude");

        map.put("msg", false);
        map.put("resources", null);

        if(StringUtils.isNotEmpty(user_id) && StringUtils.isNotEmpty(scenicSpot_longitude) && StringUtils.isNotEmpty(scenicSpot_latitude)) {
            //查到所在景点
            ScenicSpot scenicSpot = findScenicSpot(Double.parseDouble(scenicSpot_longitude), Double.parseDouble(scenicSpot_latitude));
            if(null != scenicSpot) {
                //查到景点内所有资源
                List<Resource> resources = spotToResource(scenicSpot.getScenicSpot_id());
                User_resourceServiceImpl user_resourceService = new User_resourceServiceImpl();

                //通过userId查到对应用户资源中间表
                List<User_resource> user_resources = userIdToUserResource(Long.parseLong(user_id));
                if(!resources.isEmpty()) {
                    //遍历景区所有资源
                    boolean flag = true;
                    for (Resource re : resources) {
                        User_resource user_resource = new User_resource();
                        user_resource.setUser_id(Long.parseLong(user_id));
                        user_resource.setResource_id(re.getResource_id());

                        for (User_resource ur: user_resources) {
                            if (ur.getResource_id() == user_resource.getResource_id()) {
                                flag = false;
                            }
                        }
                        if(flag) {
                            user_resourceService.addUser_resource(user_resource);
                        }

                    }

                    map.put("msg", true);
                    map.put("resources", resources);
                }
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}