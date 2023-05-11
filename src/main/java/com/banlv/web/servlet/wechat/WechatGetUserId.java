package com.banlv.web.servlet.wechat;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/wechatgetuserid")
public class WechatGetUserId extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        String code = request.getParameter("code");

        String openid = WeChatUtil.getSessionKeyOrOpenId(code);
        System.out.println(openid);

        Map<String,Object> map = new HashMap<>();

        // 判断用户是否注册

        // 已注册
        map.put("msg","true");
        // 通过openid 获取user_id
        map.put("user_id",1);
        // 未注册
//        map.put("msg","102");
//        map.put("user_id",0);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        this.doPost(request, response);
    }
}
