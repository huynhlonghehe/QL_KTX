package com.quan_ly_ktx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.quan_ly_ktx.DAO.TaiKhoanDAO;
import com.quan_ly_ktx.Entity.TaiKhoan;

@Controller
@RequestMapping("/quanly")
public class QuanLyTaiKhoanController {
	@Autowired
	TaiKhoanDAO taiKhoanDAO;
	
	@RequestMapping("/")
	public ModelAndView trangChu() {
		ModelAndView mv = new ModelAndView("QuanLy/main");
		
		return mv;
	}
	
	@RequestMapping("QLTaiKhoan")
	public ModelAndView QLTaiKhoan() {
		ModelAndView mv = new ModelAndView("QuanLy/QuanLyTaiKhoan");
		List<TaiKhoan> resultTaiKhoan = taiKhoanDAO.GetDataTaiKhoan();
		/* System.out.println("resultTaiKhoan " + resultTaiKhoan); */
		mv.addObject("ListTK", resultTaiKhoan);
		return mv;
	}
	
	@RequestMapping(value = "QLTaiKhoan/{tenDangNhap}/edit", method = RequestMethod.GET)
	public String showEditForm(@PathVariable("tenDangNhap") String tenDangNhap, ModelMap modelMap) {
		TaiKhoan taiKhoan = taiKhoanDAO.getTaiKhoanByUsername(tenDangNhap);
		/* System.out.println("Tài khoản để edit: " + taiKhoan); */
		modelMap.addAttribute("taiKhoan", taiKhoan);
		return "QuanLy/editTaiKhoan";
	}
	
	@RequestMapping(value = "QLTaiKhoan/{tenDangNhap}/update", method = RequestMethod.POST)
	public String updateTaiKhoan(ModelMap modelMap, @ModelAttribute("taiKhoan") TaiKhoan taiKhoan,
			@PathVariable String tenDangNhap) {
		/* System.out.println("Tên đăng nhập từ url: " + tenDangNhap); */
		TaiKhoan foundTaiKhoan = taiKhoanDAO.getTaiKhoanByUsername(tenDangNhap);
		/* System.out.println("Tài khoản cập nhật: " + taiKhoan); */
		String username = taiKhoan.getTenDangNhap();
		String password = taiKhoan.getMatKhau();
		String state = taiKhoan.getTinhTrang();
		String permissions = taiKhoan.getQuyen();
		if(foundTaiKhoan != null) { 
			if(username != null) {
				foundTaiKhoan.setTenDangNhap(username);
			}
			if(password != null) {
				foundTaiKhoan.setMatKhau(password);
			}
			if(state != null) {
				foundTaiKhoan.setTinhTrang(state);
			}
			if(permissions != null) {
				if(permissions.equals("QUANLY")) {
					foundTaiKhoan.setQuyen("Quản lý");
				}
				if(permissions.equals("SINHVIEN")) {
					foundTaiKhoan.setQuyen("Sinh viên");
				}
				
			}
			taiKhoanDAO.updateTaiKhoan(foundTaiKhoan);
		}
		return "redirect:/quanly/QLTaiKhoan";
	}
	
	
	@RequestMapping(value = "QLTaiKhoan/{tenDangNhap}/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteTaiKhoan(ModelMap modelMap, @ModelAttribute("taiKhoan") TaiKhoan taiKhoan,
	        @PathVariable String tenDangNhap) {
	    TaiKhoan foundTaiKhoan = taiKhoanDAO.getTaiKhoanByUsername(tenDangNhap);
	    taiKhoanDAO.deleteTaiKhoan(foundTaiKhoan);
	    return "redirect:/quanly/QLTaiKhoan";
	}

}
