package com.quan_ly_ktx.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.quan_ly_ktx.DAO.LoginDAO;


@Controller
public class LoginController {
	@Autowired
	LoginDAO loginDAO;
	
	/*
	 * @RequestMapping(value = "/login") public String login() { return
	 * "/DangNhap/login"; }
	 */
	
	@RequestMapping(value = "login", method = RequestMethod.GET) 
	 public ModelAndView Login() { 
		 ModelAndView mv = new ModelAndView("/DangNhap/login");
		 return mv; 
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST) 
	public ModelAndView processLogin(HttpServletRequest request) {
	    ModelAndView mv = new ModelAndView();
	    
		/* Lấy dữ liệu đăng nhập từ form */
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    
	    // Gọi phương thức xác thực từ TaiKhoanDAO
	    String logResult = loginDAO.loginMethod(username, password);
	    List<String> values = Arrays.asList(logResult.split("%"));
	    if(values.get(0).equals("Success")) {
	    	request.getSession().setAttribute("USERNAME", username);
	    	if(values.get(1).equals("ADMIN") || values.get(1).equals("Quản lý")) {
	    		mv = new ModelAndView("redirect:../quan_ly_ktx/quanly/");
	    	}
	    	else {
	    		mv = new ModelAndView("SinhVien/SinhVien_main");
	    	}
	    }
	    return mv; 
	}
}

