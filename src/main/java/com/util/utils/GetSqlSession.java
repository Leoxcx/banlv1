package com.util.utils;

import com.util.service.AttributeService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSessionManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class GetSqlSession {
    private static SqlSession sqlSession;
    private static SqlSessionFactoryBuilder sqlSessionFactoryBuilder;
    private static SqlSessionFactory sqlSessionFactory;
    private static SqlSessionManager sqlSessionManager;

    public static SqlSession getSqlSession(){
        if(sqlSession == null){
            InputStream resourceAsStream = null;
            try {
                URL path_1 = AttributeService.class.getResource("/");
                String xmlPath = path_1 + "SqlMapConfig.xml";
                xmlPath = xmlPath.substring(6);
                resourceAsStream = new FileInputStream(xmlPath);
//            resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
            } catch (IOException e) {
                e.printStackTrace();
            }
            sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            sqlSessionFactory = sqlSessionFactoryBuilder.build(resourceAsStream);
            sqlSessionManager = SqlSessionManager.newInstance(sqlSessionFactory);
            sqlSession = sqlSessionFactory.openSession(true);
            return sqlSession;
        }
        return sqlSession;
    }

    public static SqlSessionManager safeSqlSession(){
        if(sqlSessionManager == null){
            InputStream resourceAsStream = null;
            try {
                URL path_1 = AttributeService.class.getResource("/");
                String xmlPath = path_1 + "SqlMapConfig.xml";
                xmlPath = xmlPath.substring(6);
                resourceAsStream = new FileInputStream(xmlPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            sqlSessionFactory = sqlSessionFactoryBuilder.build(resourceAsStream);
            sqlSessionManager = SqlSessionManager.newInstance(sqlSessionFactory);
            return sqlSessionManager;
        }
        return sqlSessionManager;
    }

    public static void cleanSqlSession(){
        if (sqlSession != null){
            sqlSession.clearCache();
        }
    }
}
