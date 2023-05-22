package com.zhj.dao;

import com.zhj.domain.Student;

import java.util.List;

/**
 * @author FortuneHao
 * @date 2021-04-06 14:29
 */
//接口操作Student类
public interface StudentDao {
    //查询student表的所有的数据
    public List<Student> selectStudents();
    //插入student中的数据
    public int insertStudent(Student student);
}
