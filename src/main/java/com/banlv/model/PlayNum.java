package com.banlv.model;

import com.banlv.bean.Resource;
import com.banlv.bean.ScenicSpot;
import com.banlv.bean.ScenicZone;
import com.banlv.service.impl.ResourceServiceImpl;
import com.banlv.service.impl.ScenicSpotServiceImpl;
import com.banlv.service.impl.ScenicZoneServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//播放数记录
public class PlayNum {

    public static Map<Long, Integer> resourceMap;
    public static Map<Long, Integer> scenicSpotMap;
    public static Map<Long, Integer> scenicZoneMap;
    // 单例
    // 空参构造
    private PlayNum() {}
    private static PlayNum playNumModel = null;
    //静态工厂方法
    public static PlayNum getPlayNumModel() {
        if (playNumModel == null) {
            playNumModel = new PlayNum();

            init();
        }
        return playNumModel;
    }

    // 重新初始化单例的方法
    public static void reinitialize() {
        if (playNumModel != null) {
            // 执行销毁或重置操作，例如将实例设置为 null
            // ...
            playNumModel = null;
        }
        // 执行初始化操作
        playNumModel = new PlayNum();
    }

    private static void init(){
        //初始化存资源播放记录hashmap
        resourceMap = new HashMap<>();

        //初始化存资源对应景点播放记录hashmap
        scenicSpotMap = new HashMap<>();

        //初始化存资源对应景区播放记录hashmap
        scenicZoneMap = new HashMap<>();
    }

    //存资源播放记录
    public void resourcesRecord(long resource_id) {
        updateRecord(resourceMap,resource_id);
    }

    //存资源对应景点播放记录
    public void scenicSpotRecord(long scenicSpot_id) {
        updateRecord(scenicSpotMap,scenicSpot_id);
    }

    //存资源对应景区播放记录
    public void scenicZoneRecord(long scenicZone_id) {
        updateRecord(scenicZoneMap,scenicZone_id);
    }

    private void updateRecord(Map<Long, Integer> map,long id) {
        int num = 1;//初始化播放次数
        //判断是否存有记录
        if(map.containsKey(id)) {
            num = map.get(id);
            num++;
        }
        // 添加键值对
        map.put(id, num);

    }

    public int getResourceRecord(long resource_id) {
        if(resourceMap.get(resource_id) != null){
            return resourceMap.get(resource_id);
        }
        return 0;
    }

    public int getScenicSpotRecord(long scenicSpot_id) {
        if(scenicSpotMap.get(scenicSpot_id) != null){
            return scenicSpotMap.get(scenicSpot_id);
        }
        return 0;
    }

    public int getScenicZoneRecord(long scenicZone_id) {
        if(scenicZoneMap.get(scenicZone_id) != null){
            return scenicZoneMap.get(scenicZone_id);
        }
        return 0;
    }

    public boolean saveResourceRecordToSql(){
        // 使用迭代器遍历 HashMap
        for (Map.Entry<Long, Integer> entry : resourceMap.entrySet()) {
            Long key = entry.getKey();
            Integer value = entry.getValue();
            // 在这里处理每个键值对，可以输出、处理或存储到其他数据结构中
//            System.out.println("Key: " + key + ", Value: " + value);
            Resource resource = new Resource();

            resource.setResource_id(key);
            //通过ID查对应资源表，将资源数据中的num累加再更新入库
            ResourceServiceImpl resourceService = new ResourceServiceImpl();
            Resource resource1 = resourceService.searchAll(resource).get(0);
            int rsNum = resource1.getResource_num() + value;
            resource1.setResource_num(rsNum);
            System.out.println(resource1.toString());
            int i = resourceService.updateResource(resource1);

            if(i == 0) {
                return false;
            }
        }

        return true;
    }

    public boolean saveScenicSpotRecordToSql(){
        // 使用迭代器遍历 HashMap
        for (Map.Entry<Long, Integer> entry : scenicSpotMap.entrySet()) {
            Long key = entry.getKey();
            Integer value = entry.getValue();
            // 在这里处理每个键值对，可以输出、处理或存储到其他数据结构中
//            System.out.println("Key: " + key + ", Value: " + value);
            ScenicSpot scenicSpot = new ScenicSpot();

            scenicSpot.setScenicSpot_id(key);
            //获取数据库中的播放记录，于当前记录相加
            ScenicSpotServiceImpl scenicSpotService = new ScenicSpotServiceImpl();
            ScenicSpot scenicSpot1 = scenicSpotService.searchAll(scenicSpot).get(0);
            int ssNum = scenicSpot1.getScenicSpot_num() + value;
            scenicSpot1.setScenicSpot_num(ssNum);
            System.out.println(scenicSpot1.toString());
//            scenicSpot.setScenicSpot_num(value);
            int i = scenicSpotService.updateScenicSpot(scenicSpot1);

            if(i == 0) {
                return false;
            }
        }

        return true;
    }


    public boolean saveScenicZoneRecordToSql(){
        // 使用迭代器遍历 HashMap
        for (Map.Entry<Long, Integer> entry : scenicZoneMap.entrySet()) {
            Long key = entry.getKey();
            Integer value = entry.getValue();
            // 在这里处理每个键值对，可以输出、处理或存储到其他数据结构中
//            System.out.println("Key: " + key + ", Value: " + value);
            ScenicZone scenicZone = new ScenicZone();

            scenicZone.setScenicZone_id(key);
            ScenicZoneServiceImpl scenicZoneService = new ScenicZoneServiceImpl();
            ScenicZone scenicZone1 = scenicZoneService.searchAll(scenicZone).get(0);
            int ssNum = scenicZone1.getScenicZone_number() + value;
            scenicZone1.setScenicZone_number(ssNum);
            System.out.println(scenicZone1.toString());
//            scenicZone.setScenicZone_number(value);
            int i = scenicZoneService.updateScenicZone(scenicZone1);

            if(i == 0) {
                return false;
            }
        }

        return true;
    }

}
