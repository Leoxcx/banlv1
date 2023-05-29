package com.banlv.web.servlet.DTO.userInfoPage;

import com.banlv.bean.User;
import com.banlv.bean.UserPlay;
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
import static com.util.utils.transTool.TransTool.userIdToUserPlay;

//通过用户id  user_openid 获取播放记录
@WebServlet("/playrecord")
public class PlayRecord extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        Map<String,Object> map = new HashMap<>();

        //通过用户openid获取用户所有的播放记录信息（use_play）表

        //获取openId
        String openId = request.getParameter("user_openid");

        if(StringUtils.isEmpty(openId)) {
            map.put("msg", false);
            map.put("userPlays", null);
        }else {
            //查询user_id
            List<User> users = openIdToUserInfo(openId);

            if (users.isEmpty()) {
                map.put("msg", false);
                map.put("userPlays", null);
            } else {
                //获取userId
                long userId = users.get(0).getUser_id();

                //获取播放记录
                List<UserPlay> userPlays = userIdToUserPlay(userId);

                if(userPlays.isEmpty()) {
                    map.put("msg", false);
                    map.put("userPlays", null);
                } else {
                    map.put("msg", true);
                    map.put("userPlays", userPlays);
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