package com.binh.homework.controller;

import com.binh.homework.meta.Product;
import com.binh.homework.meta.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by binh on 2017/3/23.
 */

@Controller
public class ShopController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(HttpServletResponse response) throws IOException {
        response.sendRedirect("/login");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap map) {
        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setId(1);
        product.setPrice(100);
        product.setTitle("111");
        productList.add(product);
        map.addAttribute("productList", productList);
        return "index";
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show(ModelMap map) {
        return "show";
    }


}
