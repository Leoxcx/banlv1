package com.banlv.web.servlet.agent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.banlv.bean.Agent;
import com.banlv.service.AgentService;
import com.banlv.service.impl.AgentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/agenttotallistservlet")
public class AgentTotalListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        AgentService agentService = new AgentServiceImpl();
        List<Agent> agents = agentService.findAll();

        Map<String,Object> map = new HashMap<>();
        map.put("agents",agents);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
