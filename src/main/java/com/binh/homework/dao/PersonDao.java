package com.binh.homework.dao;

import com.binh.homework.meta.Person;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by binh on 2017/4/10.
 */

public interface PersonDao {

    @Select("select * from person where userName=#{userName} and password=#{password}")
    public Person getPerson(@Param("userName")String userName, @Param("password") String password);

    @Select("select * from person where userName=#{userName}")
    public Person getPersonByName(@Param("userName")String userName);
}
