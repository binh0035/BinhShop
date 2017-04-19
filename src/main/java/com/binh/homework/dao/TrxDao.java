package com.binh.homework.dao;

import com.binh.homework.meta.Trx;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by binh on 2017/4/10.
 */
public interface TrxDao {

    @Select("select * from Trx where contentId=#{contentId} and personId=#{personId}")
    public List<Trx> getTrxByContentIdPersonId(@Param("contentId") int contentId, @Param("personId") int personId);


    @Select("select count(*) from Trx where contentId=#{contentId}")
    public int getCountByContentId(@Param("contentId") int contentId);

    @Select("select count(*) from Trx where contentId=#{contentId} and personId=#{personId}")
    public int getCountByContentIdPersonId(@Param("contentId") int contentId, @Param("personId") int personId);

    @Select("select * from Trx where personId=#{personId}")
    public List<Trx> getTrxByPersonId(@Param("personId") int personId);

    @Insert("insert into trx (contentId, personId, price, time) values (#{contentId}, #{personId}, #{price}, #{time})")
    public int insertTrx(Trx trx);
}
