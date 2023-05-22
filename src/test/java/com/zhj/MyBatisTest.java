package com.zhj;

import com.zhj.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author FortuneHao
 * @date 2021-04-07 9:41
 */
public class MyBatisTest {
    //访问mybatis插入student
    @Test
    public void insertStudent() throws IOException {
        //1、定义mybatis主配置文件的名称，从类路径的根开始（target/calsses)
        String config = "mybatis.xml";
        //2、读取这个config表示的文件
        InputStream in = Resources.getResourceAsStream(config);
        //3、创建sqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //4、创建sqlSessionFactory对象
        SqlSessionFactory factory = builder.build(in);
        //5、【重要】获取SqlSession对象，从SqlSessionFactory对象中通过openSession()获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //6、【重要】指定要执行的SQL语句标识。sql映射文件中的 namespace + "." + 标签的id值
        String sqlId = "com.zhj.dao.StudentDao" + "." + "insertStudent";
        Student student = new Student();
        student.setId(1003);
        student.setName("张飞");
        student.setEmail("zhangfei@163.com");
        student.setAge(20);
        int nums = sqlSession.insert(sqlId,student);
        //因为mybatis自动提交事务是关闭的，故需要手动提交
        sqlSession.commit();
        System.out.println("插入数据库影响的行数="+nums);
        //7、关闭SqlSession对象
        sqlSession.close();
    }
}
