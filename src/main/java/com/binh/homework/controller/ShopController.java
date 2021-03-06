package com.binh.homework.controller;

import com.binh.homework.meta.*;
import com.binh.homework.service.IPersonService;
import com.binh.homework.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by binh on 2017/3/23.
 */

@Controller
public class ShopController {

    @Autowired
    private IPersonService mPersonService;
    @Autowired
    private IProductService mProductService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, ModelMap map) {
        mPersonService.checkUser(request, map);
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws IOException {
        Person person = mPersonService.checkUser(request, map);
        if (person != null) {
            HttpSession session = request.getSession();
            session.invalidate();
            //session.setAttribute("userName", null);
        }
        response.sendRedirect("/login");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap map) {
        Person person = mPersonService.checkUser(request, map);

        List<ProductIndex> productList = mProductService.getProductIndex(request, person);
        map.addAttribute("productList", productList);
        return "index";
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show(HttpServletRequest request, ModelMap map) {
        Person person = mPersonService.checkUser(request, map);

        int productId = Integer.valueOf( request.getParameter("id") );
        ProductShow productShow = mProductService.getProductShow(request, person, productId);
        map.addAttribute("product", productShow);
        return "show";
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String account(HttpServletRequest request, ModelMap map) {
        Person person = mPersonService.checkUser(request, map);

        List<ProductAccount> productAccountList = mProductService.getProductAccount(request, person);
        map.addAttribute("buyList", productAccountList);

        return "account";
    }

    // OK
    @RequestMapping(value = "/public", method = RequestMethod.GET)
    public String publicProduct(HttpServletRequest request, ModelMap map) {
        mPersonService.checkUser(request, map);
        return "public";
    }

    @RequestMapping(value = "/publicSubmit", method = RequestMethod.POST)
    public String publicSubmit(HttpServletRequest request, ModelMap map) {
        mPersonService.checkUser(request, map);
        String title = request.getParameter("title");
        String image = request.getParameter("image");
        String detail = request.getParameter("detail");
        long price = Long.parseLong(request.getParameter("price"));
        String summary = request.getParameter("summary");

        Product product = new Product(title, summary, detail, image, price);
        product = mProductService.insertProduct(product);

        map.addAttribute("product", product);

        return "publicSubmit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap map) {
        mPersonService.checkUser(request, map);
        int productId = Integer.valueOf( request.getParameter("id") );

        Product product = mProductService.getProductById(productId);

        map.addAttribute("product", product);
        System.out.println(product.getDetail());

        return "edit";
    }

    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editSubmit(HttpServletRequest request, ModelMap map) {
        mPersonService.checkUser(request, map);
        int id = Integer.valueOf(request.getParameter("id"));
        String title = request.getParameter("title");
        String image = request.getParameter("image");
        String detail = request.getParameter("detail");
        long price = Long.parseLong(request.getParameter("price"));
        String summary = request.getParameter("summary");

        Product product = new Product(title, summary, detail, image, price);
        product.setId(id);

        product = mProductService.updateProduct(product);

        map.addAttribute("product", product);

        return "editSubmit";
    }

    @RequestMapping(value = "/settleAccount", method = RequestMethod.GET)
    public String settleAccount(HttpServletRequest request, ModelMap map) {
        mPersonService.checkUser(request, map);

        return "settleAccount";
    }
}
