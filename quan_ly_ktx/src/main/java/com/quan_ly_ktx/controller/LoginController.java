package com.quan_ly_ktx.controller;



import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public ModelAndView processLogin(HttpServletRequest request, HttpSession session, Model model) {
	    ModelAndView mv = new ModelAndView();
	    
	    /* Lấy dữ liệu đăng nhập từ form */
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    // Lưu tên người dùng vào session
	    session.setAttribute("username", username);

	    // Lưu tên người dùng vào model để truyền cho header.jsp
	    model.addAttribute("username", username);
	    // Gọi phương thức xác thực từ TaiKhoanDAO
	    String logResult = loginDAO.loginMethod(username, password);
	    List<String> values = Arrays.asList(logResult.split("%"));
	    if(values.get(0).equals("Success")) {
	        if(values.get(1).equals("ADMIN")) {
	            mv = new ModelAndView("redirect:../quan_ly_ktx/quanly/");
	        }
	        else if(values.get(1).equals("Quản lý vật tư")){
	            mv = new ModelAndView("redirect:../quan_ly_ktx/vattu/list/");
	        }
	        else {
	        	 mv = new ModelAndView("redirect:../quan_ly_ktx/phong/list/");
	        }
	        // Đặt biến username vào model
	        mv.addObject("username", username);
	    }
	    return mv; 
	}

}

