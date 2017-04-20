package com.binh.homework.service;

import com.binh.homework.meta.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by binh on 2017/4/15.
 */
public interface IProductService {
    public List<ProductIndex> getProductIndex (HttpServletRequest request, Person person);

    public ProductShow getProductShow(HttpServletRequest request, Person person, int productId);

    public List<ProductAccount> getProductAccount(HttpServletRequest request, Person person);

    public Product insertProduct(Product product);

    Product getProductById(int id);

    Product updateProduct(Product product);
}