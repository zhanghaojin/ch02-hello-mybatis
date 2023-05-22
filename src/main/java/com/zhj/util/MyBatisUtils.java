package com.zhj.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author FortuneHao
 * @date 2021-04-07 10:52
 */
public class MyBatisUtils {
    //创建这个对象加载时间长，故定义一个可以复用的
    private static SqlSessionFactory factory = null;
    //静态代码块，一加载就创建好SqlSessionFactory的对象，并进行赋值
    static{
        //定义mybatis主配置文件的名称，从类路径根开始(target/classes)
        String config = "mybatis.xml";
        //读取这个config表示的文件
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream(config);
            //通过SqlSessionFactoryBuilder的build(inputstream in);方法获取SqlSessionFactory的对象
            factory = new SqlSessionFactoryBuilder().build(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static SqlSession getSqlSession(){
        SqlSession sqlSession = null;
        if(factory != null){
            //5、【重要】获取SqlSession对象，从SqlSessionFactory对象中通过openSession()获取SqlSession对象
            sqlSession = factory.openSession();//非自动提交事务,可以openSession(boolean)若为true则是自动提交事务
        }
        return sqlSession;
    }
}
