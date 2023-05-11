package com.banlv.web.servlet.scenicManage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.utils.MyTool;
import com.banlv.bean.ScenicManage;
import com.banlv.service.ScenicManageService;
import com.banlv.service.impl.ScenicManageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/scenicManagetotalsearchservlet")
public class ScenicManageTotalSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        Map<String, String[]> parameterMap = request.getParameterMap();

        ScenicManage scenicManage = new ScenicManage();
        ScenicManage scenicManagefromWeb = new MyTool<ScenicManage>(ScenicManage.class).getObjectFromWeb(scenicManage, parameterMap);

        ScenicManageService scenicManageService = new ScenicManageServiceImpl();
        List<ScenicManage> scenicManages = scenicManageService.searchAll(scenicManagefromWeb);

        Map<String,Object> map = new HashMap<>();
        map.put("scenicManages",scenicManages);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
