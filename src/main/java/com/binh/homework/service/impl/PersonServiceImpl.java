package com.binh.homework.service.impl;

import com.binh.homework.dao.PersonDao;
import com.binh.homework.meta.Person;
import com.binh.homework.meta.User;
import com.binh.homework.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by binh on 2017/4/10.
 */

@Service("mPersonService")
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private PersonDao personDao;

    @Override
    public Person checkUser(HttpServletRequest request, ModelMap map) {
        Person person = null;
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("userName");
        if (userName != null) {
            person = personDao.getPersonByName(userName);
            User user = new User(person);
            map.put("user", user);
        }
        return person;
    }


}
