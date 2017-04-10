package com.binh.homework.controller;

import com.binh.homework.dao.PersonDao;
import com.binh.homework.meta.Person;
import com.binh.homework.meta.ReturnMessage;
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

/**
 * Created by binh on 2017/4/8.
 */
@Controller
@RequestMapping("/api")
public class ApiController {

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
    public void delete() {

    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public void buy() {

    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload() {

    }
}
