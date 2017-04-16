package com.binh.homework.dao;

import com.binh.homework.meta.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by binh on 2017/4/13.
 */
public interface ProductDao {

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "summary", column = "abstract"),
            @Result(property = "detail", column = "text"),
            @Result(property = "image", column = "icon"),
            @Result(property = "price", column = "price")
    })
    @Select("select * from content where id=#{id}")
    public Product getProduct(@Param("id") int id);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "summary", column = "abstract"),
            @Result(property = "detail", column = "text"),
            @Result(property = "image", column = "icon"),
            @Result(property = "price", column = "price")
    })
    @Select("select * from content")
    public List<Product> getProductList();

    @Insert("insert into content (title, abstract, text, icon, price) values (#{title}, #{summary}, #{detail}, #{image}, #{price})")
    public int insertProduct(Product product);

    @Update("update content set title=#{title}, abstract=#{summary}, text=#{detail}, icon=#{image}, price=#{price} where id=#{id}")
    public int updateProduct(Product product);

    @Delete("delete from content where id=#{id}")
    public int delete(@Param("id") int id);

}
