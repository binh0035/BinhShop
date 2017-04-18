package com.binh.homework.service;

import com.binh.homework.meta.Person;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by binh on 2017/4/10.
 */
public interface IPersonService {

    public Person checkUser(HttpServletRequest request, ModelMap map);
}
