package com.binh.homework.controller;

import com.binh.homework.dao.PersonDao;
import com.binh.homework.dao.ProductDao;
import com.binh.homework.meta.BuyItem;
import com.binh.homework.meta.Person;
import com.binh.homework.meta.Product;
import com.binh.homework.meta.ReturnMessage;
import com.binh.homework.service.IPersonService;
import com.binh.homework.service.IProductService;
import com.binh.homework.service.impl.PersonServiceImpl;
import com.binh.homework.service.impl.ProductServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by binh on 2017/4/8.
 */
@Controller
@RequestMapping("/api")
public class ApiController {
    IPersonService mPersonService = new PersonServiceImpl();
    IProductService mProductService = new ProductServiceImpl();

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage login(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        PersonDao dao = context.getBean("personDao", PersonDao.class);
        Person person = dao.getPerson(userName, password);

        HttpSession session = request.getSession();
        ReturnMessage message;
        if (person != null) {
            session.setAttribute("userName", userName);
            //session.setAttribute("password", password);
            message = new ReturnMessage(200, "", true);
        } else {
            session.invalidate();
            message = new ReturnMessage(400, "帐号或密码错误", false);
        }

        return message;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage delete(HttpServletRequest request, ModelMap map) {
        mPersonService.checkUser(request, map);
        int productId = Integer.valueOf(request.getParameter("id"));
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        ProductDao dao = context.getBean("productDao", ProductDao.class);
        int result = dao.delete(productId);
        ReturnMessage message;
        if (result > 0) {
            message = new ReturnMessage(200, "", true);
        } else {
            message = new ReturnMessage(400, "删除失败", false);
        }
        return message;
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage buy(HttpServletRequest request, ModelMap map) {
        ReturnMessage message;
        mPersonService.checkUser(request, map);
        //List<BuyItem> buyList = (List<BuyItem>)request.getParameter("buyList");


        return message;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage upload(HttpServletRequest request, ModelMap map) {
        ReturnMessage message;
        mPersonService.checkUser(request, map);

        return message;
    }
}
