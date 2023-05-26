package com.banlv.model;

import com.util.bean.dto.CoordinateInfoDto;

import java.util.*;

//定位坐标记录
public class CoordinateRecord {
    public static Map<Long, List<CoordinateInfoDto>> recordMap;
    // 单例
    // 空参构造
    private CoordinateRecord() {}
    private static CoordinateRecord coordinateRecordModel = null;
    //静态工厂方法
    public static CoordinateRecord getCoordinateRecordModel() {
        if (coordinateRecordModel == null) {
            coordinateRecordModel = new CoordinateRecord();

            init();
        }
        return coordinateRecordModel;
    }

    private static void init(){
        recordMap = new HashMap<>();

    }

    public void setRecord(long agentId, CoordinateInfoDto coordinateInfoDto) {
        if(recordMap.containsKey(agentId)) {
            List<CoordinateInfoDto> coordinateInfoDtoList = recordMap.get(agentId);
            boolean judge = false;
            for(CoordinateInfoDto dto:coordinateInfoDtoList) {
                if(dto.equals(coordinateInfoDto)) {
                    judge = true;
                    break;
                }
            }
            if (!judge) coordinateInfoDtoList.add(coordinateInfoDto);
        } else {
            List<CoordinateInfoDto> coordinateInfoDtoList = new ArrayList<>();
            coordinateInfoDtoList.add(coordinateInfoDto);
            recordMap.put(agentId,coordinateInfoDtoList);
        }
    }
//    获取定位记录
    public List<CoordinateInfoDto> getRecord(long agentId) {
        return recordMap.get(agentId);
    }

//    删除定位记录
    public boolean removeRecord(int index, long agentId) {
        List<CoordinateInfoDto> coordinateInfoDtoList = recordMap.get(agentId);
        if(coordinateInfoDtoList == null) {
            return false;
        }
        if(coordinateInfoDtoList.size() <= index) {
            return false;
        }
        Iterator<CoordinateInfoDto> iterator = coordinateInfoDtoList.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            CoordinateInfoDto next = iterator.next();
            if (i == index) {
                iterator.remove();
            }
            i++;
        }
        return true;
    }

//    修改定位记录
    public boolean modifyRecord(int index, long agentId, CoordinateInfoDto coordinateInfoDto) {
        List<CoordinateInfoDto> coordinateInfoDtoList = recordMap.get(agentId);
        if(coordinateInfoDtoList == null) {
            return false;
        }
        if(coordinateInfoDtoList.size() <= index) {
            return false;
        }

        for(CoordinateInfoDto dto:coordinateInfoDtoList) {
            if(dto.equals(coordinateInfoDto)) {
                return false;
            }
        }
        coordinateInfoDtoList.set(index, coordinateInfoDto);

        return true;
    }

}
