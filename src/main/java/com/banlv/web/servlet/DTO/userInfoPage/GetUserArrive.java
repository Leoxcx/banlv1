package com.banlv.web.servlet.DTO.userInfoPage;

import com.banlv.bean.User;
import com.banlv.bean.UserArrive;
import com.banlv.service.impl.UserArriveServiceImpl;
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

import static com.util.utils.transTool.TransTool.openIdToUserInfo;

//通过用户openid获取用户所有的打开信息（useArrive）表 获取用户打卡记录
@WebServlet("/getuserarrive")
public class GetUserArrive extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> map = new HashMap<>();

        //通过用户openid获取用户所有的打开信息（useArrive）表

        //获取openId
        String openId = request.getParameter("user_openid");

        if(StringUtils.isEmpty(openId)) {
            map.put("msg", false);
            map.put("userArrives", null);
        }else {
            //查询user_id
            List<User> users = openIdToUserInfo(openId);

            if (users.isEmpty()) {
                map.put("msg", false);
                map.put("userArrives", null);
            } else {
                //获取userId
                long userId = users.get(0).getUser_id();
                UserArrive userArrive = new UserArrive();
                userArrive.setUser_id(userId);

                UserArriveServiceImpl userArriveService = new UserArriveServiceImpl();
                List<UserArrive> userArrives = userArriveService.searchAll(userArrive);

                if(userArrives.isEmpty()) {
                    map.put("msg", false);
                    map.put("userArrives", null);
                } else {
                    map.put("msg", true);
                    map.put("userArrives", userArrives);
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
