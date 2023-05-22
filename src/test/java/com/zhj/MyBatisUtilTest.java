package com.zhj;

import com.zhj.domain.Student;
import com.zhj.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @author FortuneHao
 * @date 2021-04-07 10:52
 */
public class MyBatisUtilTest {
    @Test
    public void insertStudentByMyBatisUtils(){
        //获取sqlSession对象
        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        //6、【重要】指定要执行的SQL语句标识。sql映射文件中的 namespace + "." + 标签的id值
        String sqlId = "com.zhj.dao.StudentDao" + "." + "insertStudent";
        Student student = new Student();
        student.setId(1004);
        student.setName("刘备");
        student.setEmail("liubei@163.com");
        student.setAge(28);
        int nums = sqlSession.insert(sqlId,student);
        sqlSession.commit();
        System.out.println("插入语句影响的行数="+nums);
        //因为SqlSession不安全，故需要close();
        sqlSession.close();
    }
}
