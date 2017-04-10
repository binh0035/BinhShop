package com.binh.homework.dao;

import com.binh.homework.meta.Trx;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by binh on 2017/4/10.
 */
public interface TrxDao {

    @Select("select * from Trx where contentId=#{contentId}")
    public List<Trx> getTrxByContentId(@Param("contentId") int contentId);

    @Select("select * from Trx where contentId=#{contentId} and personId=#{personId}")
    public List<Trx> getTrxByContentIdPersonId(@Param("contentId") int contentId, @Param("personId") int personId);
}
