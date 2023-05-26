package com.util.bean.timer;


import com.banlv.model.PlayNum;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Timer;
import java.util.TimerTask;


@WebListener
public class TomcatTimer implements ServletContextListener {

    private Timer timer;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // 在Web应用程序启动时调用此方法
        timer = new Timer(true);
//        timer.scheduleAtFixedRate(new MyTask(), 5*60*1000, 24 * 60 * 60 * 1000); // 每隔5秒执行任务

        timer.scheduleAtFixedRate(new MyTask(), timeCalc(), 24 * 60 * 60 * 1000);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // 在Web应用程序关闭时调用此方法
        timer.cancel();
    }

    private class MyTask extends TimerTask {
        @Override
        public void run() {
            // 在这里编写您需要定期执行的任务代码
//            保存资源播放数定时任务
            boolean a = PlayNum.getPlayNumModel().saveResourceRecordToSql();

//            保存景点播放数定时任务
            boolean b = PlayNum.getPlayNumModel().saveScenicSpotRecordToSql();

//            保存景区播放数定时任务
            boolean c = PlayNum.getPlayNumModel().saveScenicZoneRecordToSql();

            if(a && b && c){
//                重置单例
                PlayNum.reinitialize();
                System.out.println("已完成播放记录持久化");
            }else{
                System.out.println("持久化播放记录失败");
            }


        }
    }

    private long timeCalc() {
        ZonedDateTime midnight = ZonedDateTime.now().with(LocalTime.MIDNIGHT).plusDays(1);
        long initialDelay = midnight.toInstant().toEpochMilli() - System.currentTimeMillis();

        return initialDelay;
    }
}
