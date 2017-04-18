package com.binh.homework.controller;

import com.binh.homework.dao.ProductDao;
import com.binh.homework.meta.*;
import com.binh.homework.service.IPersonService;
import com.binh.homework.service.IProductService;
import com.binh.homework.service.impl.PersonServiceImpl;
import com.binh.homework.service.impl.ProductServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

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


    private IPersonService mPersonService = new PersonServiceImpl();

    private IProductService mProductService = new ProductServiceImpl();

    // OK
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, ModelMap map) {
        mPersonService.checkUser(request, map);
        return "login";
    }

    // OK
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

    // 可优化
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
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        ProductDao dao = context.getBean("productDao", ProductDao.class);
        int result = dao.insertProduct(product);

        if (result > 0) {
            map.addAttribute("product", product);
        }

        return "publicSubmit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap map) {
        mPersonService.checkUser(request, map);
        int productId = Integer.valueOf( request.getParameter("id") );
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        ProductDao dao = context.getBean("productDao", ProductDao.class);
        Product product = dao.getProduct(productId);

        map.addAttribute("product", product);

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

        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        ProductDao dao = context.getBean("productDao", ProductDao.class);
        int result = dao.updateProduct(product);
        if (result > 0) {
            map.addAttribute("product", product);
        }

        return "editSubmit";
    }

    @RequestMapping(value = "/settleAccount", method = RequestMethod.GET)
    public String settleAccount(HttpServletRequest request, ModelMap map) {
        mPersonService.checkUser(request, map);

        return "settleAccount";
    }
}
