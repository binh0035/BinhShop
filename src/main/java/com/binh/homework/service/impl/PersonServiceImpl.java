package com.binh.homework.service.impl;

import com.binh.homework.dao.PersonDao;
import com.binh.homework.meta.User;
import com.binh.homework.service.IPersonService;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by binh on 2017/4/10.
 */
public class PersonServiceImpl implements IPersonService {

    @Override
    public void checkUser(HttpServletRequest request, ModelMap map) {
//        HttpSession session = request.getSession();
//        String userName = (String) session.getAttribute("userName");
//        if (userName != null) {
//            WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
//            PersonDao dao = context.getBean("personDao", PersonDao.class);
//            User user = dao.getUser(userName);
//            map.put("user", user);
//        }
    }


}
