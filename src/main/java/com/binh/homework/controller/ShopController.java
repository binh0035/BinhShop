package com.binh.homework.controller;

import com.binh.homework.dao.ContentDao;
import com.binh.homework.dao.PersonDao;
import com.binh.homework.dao.TrxDao;
import com.binh.homework.meta.*;
import com.binh.homework.service.IPersonService;
import com.binh.homework.service.impl.PersonServiceImpl;
import com.binh.homework.utils.Utils;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by binh on 2017/3/23.
 */

@Controller
public class ShopController {

    // OK
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, ModelMap map) {
        Utils.checkUser(request, map);
        return "login";
    }

    // OK
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("userName");
        if (userName != null) {
            session.invalidate();
        }
        response.sendRedirect("/login");
    }

    // 可优化
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap map) {
        Utils.checkUser(request, map);

        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        ContentDao contentDao = context.getBean("contentDao", ContentDao.class);
        List<Content> contentList = contentDao.getContentList();
        TrxDao trxDao = context.getBean("trxDao", TrxDao.class);
        List<Trx> trxList;
        List<Product> productList = new ArrayList<>();
        Product product;
        for (Content content : contentList) {
            product = new Product(content.getId(), content.getTitle(), content.getIcon().toString(), content.getPrice());
            trxList = trxDao.getTrxByContentId(content.getId());
            product.setSell(trxList.size() > 0);
            if (product.isSell() && map.get("user") != null) {
                PersonDao personDao = context.getBean("personDao", PersonDao.class);
                Person person = personDao.getPersonByName(((User)map.get("user")).getUsername());
                trxList = trxDao.getTrxByContentIdPersonId(content.getId(), person.getId());
                product.setBuy(trxList.size() > 0);
            }
            productList.add(product);
        }

        map.addAttribute("productList", productList);
        return "index";
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show(ModelMap map) {
        return "show";
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String account(ModelMap map) {
        return "account";
    }

    @RequestMapping(value = "/public", method = RequestMethod.GET)
    public String publicProduct(ModelMap map) {
        return "public";
    }

    @RequestMapping(value = "/publicSubmit", method = RequestMethod.POST)
    public String publicSubmit(ModelMap map) {
        return "publicSubmit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(ModelMap map) {
        return "edit";
    }

    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editSubmit(ModelMap map) {
        return "editSubmit";
    }
}
