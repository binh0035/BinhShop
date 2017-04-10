package com.binh.homework.dao;

import com.binh.homework.meta.Content;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by binh on 2017/4/10.
 */
public interface ContentDao {

    @Select("select * from content")
    public List<Content> getContentList();
}
