package com.binh.homework.service;

import com.binh.homework.meta.Person;
import com.binh.homework.meta.ProductAccount;
import com.binh.homework.meta.ProductIndex;
import com.binh.homework.meta.ProductShow;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by binh on 2017/4/15.
 */
public interface IProductService {
    public List<ProductIndex> getProductIndex (HttpServletRequest request, Person person);

    public ProductShow getProductShow(HttpServletRequest request, Person person, int productId);

    public List<ProductAccount> getProductAccount(HttpServletRequest request, Person person);
}
