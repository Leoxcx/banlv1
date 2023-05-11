package com.util.bean.transTool;

import com.banlv.bean.*;
import com.banlv.model.PlayNum;
import com.banlv.service.ResourceService;
import com.banlv.service.ScenicSpot_resourceService;
import com.banlv.service.UserService;
import com.banlv.service.impl.*;

import java.util.ArrayList;
import java.util.List;

public class TransTool {

    // 单例
    // 空参构造
    private TransTool() {
        // 禁止该类被实例化
    }
    private static TransTool transTool = null;
    //静态工厂方法
    public static TransTool getTransTool() {
        if (transTool == null) {
            transTool = new TransTool();

           // init();
        }
        return transTool;
    }


//    景点id找当前景点的所有资源resource
    public static List<Resource> spotToResource(long spotId) {
        //景点资源中间表
        ScenicSpot_resource scenicSpot_resource = new ScenicSpot_resource();
        scenicSpot_resource.setScenicSpot_id(spotId);
        scenicSpot_resource.setScenicSpot_resource_use(1);

        List<Resource> resources = new ArrayList<>();

        ScenicSpot_resourceService spotResource = new ScenicSpot_resourceServiceImpl();
        List<ScenicSpot_resource> scenicSpot_resources = spotResource.searchAll(scenicSpot_resource);

        if (!scenicSpot_resources.isEmpty()) {

            Resource resource = new Resource();
            ResourceService resourceService = new ResourceServiceImpl();

            for (ScenicSpot_resource i : scenicSpot_resources) {
                resource.setResource_id(i.getResource_id());
                List<Resource> res = resourceService.searchAll(resource);
                resources.add(res.get(0));
            }
        }
        return  resources;
    }

//    通过资源id找到所在景点scenicSpot
    public static List<ScenicSpot> resourceToScenicSpot(long resourceId) {
        //通过景点资源中间表，获取scenicSpotId景点id
        ScenicSpot_resource scenicSpot_resource = new ScenicSpot_resource();
        scenicSpot_resource.setResource_id(resourceId);
        ScenicSpot_resourceService spotResource = new ScenicSpot_resourceServiceImpl();
        List<ScenicSpot_resource> scenicSpot_resources = spotResource.searchAll(scenicSpot_resource);

        List<ScenicSpot> scenicSpots = new ArrayList<>();


        if (!scenicSpot_resources.isEmpty()) {
            ScenicSpot scenicSpot = new ScenicSpot();
            ScenicSpotServiceImpl scenicSpotService = new ScenicSpotServiceImpl();

            scenicSpot.setScenicSpot_id(scenicSpot_resources.get(0).getScenicSpot_id());
            scenicSpots = scenicSpotService.searchAll(scenicSpot);
        }
        return scenicSpots;
    }


//    通过景点id找到所在景区scenicZone
    public static List<ScenicZone> scenicSpotToScenicZone(long scenicSpotId) {
        ScenicZone_scenicSpot scenicZone_scenicSpot = new ScenicZone_scenicSpot();
        scenicZone_scenicSpot.setScenicSpot_id(scenicSpotId);
        ScenicZone_scenicSpotServiceImpl scenicZoneScenicSpot = new ScenicZone_scenicSpotServiceImpl();
        List<ScenicZone_scenicSpot> scenicZone_scenicSpots = scenicZoneScenicSpot.searchAll(scenicZone_scenicSpot);

        List<ScenicZone> scenicZones =new ArrayList<>();

        if(!scenicZone_scenicSpots.isEmpty()) {
            ScenicZone scenicZone = new ScenicZone();
            ScenicZoneServiceImpl scenicZoneService = new ScenicZoneServiceImpl();

            scenicZone.setScenicZone_id(scenicZone_scenicSpots.get(0).getScenicZone_id());
            scenicZones = scenicZoneService.searchAll(scenicZone);
        }

        return scenicZones;
    }

//    通过景区id找到所有景点
    public static List<ScenicSpot> scenicZoneSpotToScenic(long scenicZoneId) {
        ScenicZone_scenicSpot scenicZoneScenicSpot = new ScenicZone_scenicSpot();
        scenicZoneScenicSpot.setScenicZone_id(scenicZoneId);
        ScenicZone_scenicSpotServiceImpl scenicZone_ScenicSpot = new ScenicZone_scenicSpotServiceImpl();
        List<ScenicZone_scenicSpot> scenicZone_scenicSpots = scenicZone_ScenicSpot.searchAll(scenicZoneScenicSpot);
        List<ScenicSpot> scenicSpots = new ArrayList<>();
        if(!scenicZone_scenicSpots.isEmpty()){

            ScenicSpotServiceImpl scenicSpotService = new ScenicSpotServiceImpl();

            for (ScenicZone_scenicSpot ss:scenicZone_scenicSpots) {
                ScenicSpot scenicSpot = new ScenicSpot();
                scenicSpot.setScenicSpot_id(ss.getScenicSpot_id());
                scenicSpots.add(scenicSpotService.searchAll(scenicSpot).get(0));
            }
        }
        return scenicSpots;

    }

    //通过openId查用户表信息
    public static List<User> openIdToUserInfo(String openId) {
        //查询user_id
        UserService userService = new UserServiceImpl();
        User user = new User();
        user.setUser_openid(openId);
        List<User> users = userService.searchAll(user);
        return users;
    }

    //通过userId查用户播放记录
    public static List<UserPlay> userIdToUserPlay(long userId) {
        UserPlay userPlay = new UserPlay();
        userPlay.setUser_id(userId);

        UserPlayServiceImpl userPlayService = new UserPlayServiceImpl();

        List<UserPlay> userPlays = userPlayService.searchAll(userPlay);
        return userPlays;
    }

    //通过userId查用户播放记录
    public static List<User_resource> userIdToUserResource(long userId) {
        User_resource userResource = new User_resource();
        userResource.setUser_id(userId);

        User_resourceServiceImpl userResourceService = new User_resourceServiceImpl();

        List<User_resource> userResources = userResourceService.searchAll(userResource);
        return userResources;
    }

    //通过经纬度查当前所在景点
    public static ScenicSpot findScenicSpot(double scenicSpot_longitude, double scenicSpot_latitude) {
        ScenicSpotServiceImpl scenicSpotService = new ScenicSpotServiceImpl();
        List<ScenicSpot> all = scenicSpotService.findAll();
        ScenicSpot scenicSpot = new ScenicSpot();

        double lng, lat, scenicSpot_range, deffLat, deffLng, distance;

        for (ScenicSpot ss: all) {
            lng = ss.getScenicSpot_longitude();     //当前景点经度
            lat = ss.getScenicSpot_latitude();      //当前景点纬度
            scenicSpot_range = ss.getScenicSpot_range();        //当前景点范围
            deffLat = lat - scenicSpot_latitude;        //经度差值
            deffLng = lng - scenicSpot_longitude;       //纬度差值

            distance = Math.sqrt( ( deffLat * deffLat ) + ( deffLng * deffLng ) );      //经纬度差值

            if(distance <= scenicSpot_range) {
                scenicSpot = ss;
                return scenicSpot;
            }
        }
        return scenicSpot;
    }

    //通过city_name查城市信息
    public static List<City> cityNameToCityInfo(String cityName) {
        City city = new City();
        city.setCity_name(cityName);

        CityServiceImpl cityService = new CityServiceImpl();

        return cityService.searchAll(city);
    }
}
