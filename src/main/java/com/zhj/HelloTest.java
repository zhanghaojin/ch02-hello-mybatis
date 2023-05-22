package com.zhj;

import com.zhj.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author FortuneHao
 * @date 2021-04-06 17:03
 */
public class HelloTest {
    public static void main(String[] args) throws IOException {
        //访问mybatis读取student数据
        //1、定义mybatis主配置文件的名称，从类路径的根开始（target/classes) 这个类路径下有mybatis.xml
        String config = "mybatis.xml";
        //2、读取这个config表示的文件
        InputStream in = Resources.getResourceAsStream(config);
        //3、创建了sqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //4、创建sqlSessionFactory对象
        SqlSessionFactory factory = builder.build(in);
        //5、【重要】获取SqlSession对象，从SqlSessionFactory中获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //6、【重要】指定要执行的SQL语句标识。 sql映射文件中的 namespace + "." + 标签的id值
        String sqlId = "com.zhj.dao.StudentDao" + "." + "selectStudents";
        List<Student> students = sqlSession.selectList(sqlId);
        for(Student student:students){
            System.out.println(student);
        }
        //7、关闭SqlSession对象
        sqlSession.close();
    }
}
