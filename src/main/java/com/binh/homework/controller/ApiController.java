package com.binh.homework.controller;

import com.alibaba.fastjson.JSONObject;
import com.binh.homework.dao.PersonDao;
import com.binh.homework.dao.ProductDao;
import com.binh.homework.dao.TrxDao;
import com.binh.homework.meta.*;
import com.binh.homework.service.IPersonService;
import com.binh.homework.service.IProductService;
import com.binh.homework.service.impl.PersonServiceImpl;
import com.binh.homework.service.impl.ProductServiceImpl;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.ch.IOUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public ReturnMessage buy(HttpServletRequest request, ModelMap map, @RequestBody String param) throws IOException {
        ReturnMessage message;
        Person person = mPersonService.checkUser(request, map);

        JSONObject jo = new JSONObject();
        List<BuyItem> buyList = jo.parseArray(param, BuyItem.class);

        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        TrxDao trxDao = context.getBean("trxDao", TrxDao.class);
        ProductDao productDao = context.getBean("productDao", ProductDao.class);

        Trx trx;
        int succNum = 0, allNum = 0, result;
        for (BuyItem buyItem : buyList) {
            trx = new Trx();
            trx.setContentId(buyItem.getId());
            trx.setPersonId(person.getId());
            trx.setPrice((int) productDao.getProduct(buyItem.getId()).getPrice());
            Date now = new Date();
            trx.setTime(now.getTime());
            for (int i = 0; i < buyItem.getNumber(); i++) {
                result = trxDao.insertTrx(trx);
                if (result > 0) {
                    succNum ++;
                }
                allNum++;
            }
        }

        if (succNum == allNum) {
            message = new ReturnMessage(200, "", true);
        } else if (succNum == 0) {
            message = new ReturnMessage(400, "所有订单均失败", false);
        } else {
            message = new ReturnMessage(400, "订单只成功了 " + succNum + "/" + allNum, false);
        }

        return message;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public UploadMessage upload(HttpServletRequest request, ModelMap map, @RequestParam("file")MultipartFile file) throws IOException {
        UploadMessage message;
        mPersonService.checkUser(request, map);
        //int id = Integer.valueOf(request.getParameter("id"));
        //String url = request.getParameter("url");
        //System.out.println("id:" + id + "; " + url);

        String savePath = request.getServletContext().getRealPath("/") + "/uploadImage" + file.getOriginalFilename();
        File saveFile = new File(savePath);
        file.transferTo(saveFile);

        String path = "/uploadImage" + file.getOriginalFilename();
        message = new UploadMessage(200, "", path);
        return message;
    }
}
