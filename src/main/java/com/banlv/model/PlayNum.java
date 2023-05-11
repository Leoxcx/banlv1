package com.banlv.model;

import java.util.HashMap;
import java.util.Map;

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


}
