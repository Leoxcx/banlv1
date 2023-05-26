package com.banlv.web.servlet.DTO.mainPage;

import com.banlv.bean.ScenicZone;
import com.banlv.bean.ScenicZoneEntry;
import com.banlv.bean.ScenicZone_scenicZoneEntry;
import com.banlv.service.ScenicZoneEntryService;
import com.banlv.service.impl.ScenicZoneEntryServiceImpl;
import com.banlv.service.impl.ScenicZone_scenicZoneEntryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//通过景区词条中间表的景区id查询当前景区的所有词条
@WebServlet("/getsceniczoneentriesbysceniczoneid")
public class GetScenicZoneEntriesByScenicZoneId extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> map = new HashMap<>();

        map.put("msg",false);
        map.put("scenicZoneEntries",null);

        String scenicZone_id = request.getParameter("scenicZone_id");
        if(!scenicZone_id.isEmpty()) {
            long l = Long.parseLong(scenicZone_id);
            ScenicZone_scenicZoneEntry scenicZone_scenicZoneEntry = new ScenicZone_scenicZoneEntry();
            scenicZone_scenicZoneEntry.setScenicZone_id(l);
            scenicZone_scenicZoneEntry.setScenicZone_scenicSpot_use(true);
            List<ScenicZone_scenicZoneEntry> scenicZone_sceincZoneEntries = new ScenicZone_scenicZoneEntryServiceImpl().searchAll(scenicZone_scenicZoneEntry);
            if(!scenicZone_sceincZoneEntries.isEmpty()) {
                ScenicZoneEntryService scenicZoneEntryService = new ScenicZoneEntryServiceImpl();
                List scenicZoneEntryList = new ArrayList<>();
                for (ScenicZone_scenicZoneEntry scenicZone_sceincZoneEntry : scenicZone_sceincZoneEntries) {
                    ScenicZoneEntry searchScenicZoneEntry = new ScenicZoneEntry();
                    searchScenicZoneEntry.setScenicZoneEntry_id(scenicZone_sceincZoneEntry.getScenicZoneEntry_id());
                    List<ScenicZoneEntry> scenicZoneEntries = scenicZoneEntryService.searchAll(searchScenicZoneEntry);
                    if (!scenicZoneEntries.isEmpty()){
                        scenicZoneEntryList.add(scenicZoneEntries.get(0));
                    }
                }
                if (!scenicZoneEntryList.isEmpty()){
                    map.put("msg",true);
                    map.put("scenicZoneEntries",scenicZoneEntryList);
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