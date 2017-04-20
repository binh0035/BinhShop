package com.binh.homework.controller;

import com.alibaba.fastjson.JSONObject;
import com.binh.homework.dao.PersonDao;
import com.binh.homework.dao.ProductDao;
import com.binh.homework.dao.TrxDao;
import com.binh.homework.meta.*;
import com.binh.homework.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by binh on 2017/4/8.
 */
@Controller
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private IPersonService mPersonService;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private TrxDao trxDao;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage login(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        Person person = personDao.getPerson(userName, password);

        HttpSession session = request.getSession();
        ReturnMessage message;
        if (person != null) {
            session.setAttribute("userName", userName);
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

        int result = productDao.delete(productId);
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
    public ReturnMessage buy (HttpServletRequest request, ModelMap map, @RequestBody String param) throws IOException {
        ReturnMessage message;
        Person person = mPersonService.checkUser(request, map);

        JSONObject jo = new JSONObject();
        List<BuyItem> buyList = jo.parseArray(param, BuyItem.class);

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
                    succNum++;
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
    public UploadMessage upload(HttpServletRequest request, ModelMap map, @RequestParam("file") MultipartFile file) {
        UploadMessage message;
        mPersonService.checkUser(request, map);

        String oldfileName = file.getOriginalFilename();
        String[] names = oldfileName.split("\\.");
        String fileName = String.valueOf((new Date()).getTime());
        if (names.length > 0) {
            fileName = fileName + "." + names[names.length - 1];
        }
        fileName = "/image/" + fileName;
        String savePath = request.getServletContext().getRealPath("/") + fileName;
        File saveFile = new File(savePath);
        try {
            file.transferTo(saveFile);
            message = new UploadMessage(200, "", fileName);
        } catch (IOException e) {
            e.printStackTrace();
            message = new UploadMessage(400, "上传失败", fileName);
        }

        return message;
    }
}
