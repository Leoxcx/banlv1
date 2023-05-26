package com.banlv.web.servlet.DTO.mainPage;

import com.banlv.bean.ScenicZone;
import com.banlv.bean.ScenicZone_scenicZoneEntry;
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
@WebServlet("/getsceinczoneentriesbysceniczoneid")
public class GetSceincZoneEntriesByScenicZoneId extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> map = new HashMap<>();

        map.put("msg",false);
        map.put("sceincZoneEntries",null);
        List<ScenicZone_scenicZoneEntry> scenicZone_sceincZoneEntries = new ArrayList<>();


        String scenicZone_id = request.getParameter("scenicZone_id");
        if(!scenicZone_id.isEmpty()) {
            long l = Long.parseLong(scenicZone_id);
            ScenicZone_scenicZoneEntry scenicZone_scenicZoneEntry = new ScenicZone_scenicZoneEntry();
            scenicZone_scenicZoneEntry.setScenicZone_id(l);
            scenicZone_scenicZoneEntry.setScenicZone_scenicSpot_use(true);
            List<ScenicZone_scenicZoneEntry> szszEntries = new ScenicZone_scenicZoneEntryServiceImpl().searchAll(scenicZone_scenicZoneEntry);
            if(!szszEntries.isEmpty()) {
                for (ScenicZone_scenicZoneEntry sze : szszEntries) {
                    if(sze.isScenicZone_scenicSpot_use()) {
                        scenicZone_sceincZoneEntries.add(sze);
                    }
                }
                if(!scenicZone_sceincZoneEntries.isEmpty()) {
                    map.put("msg",true);
                    map.put("sceincZoneEntries",scenicZone_sceincZoneEntries);
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