package com.xxx.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MyBatisUtils {
    //利用static属于类而不属于对象，且全局唯一
    private static SqlSessionFactory sqlSessionFactory = null;
    //利用静态代码块在初始化时实例化sqlSessionFactory
    static {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
            //初始化错误时，通过抛出异常ExceptionInInitializerError通知调用者
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * openSession创建一个新的SqlSession对象
     * @return SqlSession
     */
    public static SqlSession openSession(){
        return sqlSessionFactory.openSession();
    }

    /**
     * 释放一个有效的SqlSession对象
     * @param sqlSession
     */
    public static void closeSession(SqlSession sqlSession){
        if (sqlSession!=null){
            sqlSession.close();
        }
    }
}
