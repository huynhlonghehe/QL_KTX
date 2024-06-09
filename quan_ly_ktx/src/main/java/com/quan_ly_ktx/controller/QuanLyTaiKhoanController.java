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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.quan_ly_ktx.DAO.SinhVienDAO;
import com.quan_ly_ktx.DAO.TaiKhoanDAO;
import com.quan_ly_ktx.Entity.SinhVien.SinhVien;
import com.quan_ly_ktx.Entity.TaiKhoan.TaiKhoan;

@Controller
@RequestMapping("/quanly")
public class QuanLyTaiKhoanController {
	@Autowired
	TaiKhoanDAO taiKhoanDAO;
	@Autowired
	SinhVienDAO sinhVienDAO;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView trangChu() {
		ModelAndView mv = new ModelAndView("QuanLy/main");
		
		return mv;
	}
	
	@RequestMapping(value = "QLTaiKhoan", method = RequestMethod.GET)
	public ModelAndView QLTaiKhoan() {
		ModelAndView mv = new ModelAndView("QuanLy/TaiKhoan/QuanLyTaiKhoan");
		List<TaiKhoan> resultTaiKhoan = taiKhoanDAO.GetDataTaiKhoan();
		List<SinhVien> resultNumOfRequestCreateAccount = taiKhoanDAO.getNumberOfRequestCreateAccount();
		int countRequestCreateAccount = resultNumOfRequestCreateAccount.size();
		System.out.println("Số lượng sv chưa có tài khoản: " + resultNumOfRequestCreateAccount);
		mv.addObject("ListTK", resultTaiKhoan);
		mv.addObject("ListSV_ChuaCoTK", resultNumOfRequestCreateAccount);
		mv.addObject("SoLuongSV_ChuaCoTk", countRequestCreateAccount);
		return mv;
	}
	
	@RequestMapping(value = "QLTaiKhoan/{tenDangNhap}/edit", method = RequestMethod.GET)
	public String showEditForm(@PathVariable("tenDangNhap") String tenDangNhap, ModelMap modelMap) {
		TaiKhoan taiKhoan = taiKhoanDAO.getTaiKhoanByUsername(tenDangNhap);
		/* System.out.println("Tài khoản để edit: " + taiKhoan); */
		modelMap.addAttribute("taiKhoan", taiKhoan);
		return "QuanLy/TaiKhoan/editTaiKhoan";
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
	
	
	@RequestMapping(value = "QLTaiKhoan/createTK", method = RequestMethod.POST)
	public String createTaiKhoan(ModelMap modelMap, @ModelAttribute("newTaiKhoan") TaiKhoan newTaiKhoan, RedirectAttributes redirectAttributes) {
		 if(taiKhoanDAO.getTaiKhoanByUsername(newTaiKhoan.getTenDangNhap()) != null) {
			 redirectAttributes.addFlashAttribute("errorMessage","Tài khoản này đã tồn tại!");
			 return "redirect:/quanly/QLTaiKhoan";
		 }else {
			 taiKhoanDAO.createTaiKhoan(newTaiKhoan);
			 redirectAttributes.addFlashAttribute("successMessage", "Đã thêm tài khoản " +newTaiKhoan.getTenDangNhap() + " thành công!"); }
		return "redirect:/quanly/QLTaiKhoan";
	}
	
	@RequestMapping(value = "QLTaiKhoan/{tenDangNhap}/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteTaiKhoan(@PathVariable String tenDangNhap, RedirectAttributes redirectAttributes) {
	    TaiKhoan foundTaiKhoan = taiKhoanDAO.getTaiKhoanByUsername(tenDangNhap);
	    SinhVien sinhVien = sinhVienDAO.getSinhVienByMaSV(tenDangNhap);
	    if(sinhVien != null) {
	    	//thông báo lỗi lên view
	    	redirectAttributes.addFlashAttribute("errorMessage", "Không thể xóa tài khoản vì đã có sinh viên sử dụng.");
	    	return "redirect:/quanly/QLTaiKhoan";
	    }else {
	    	taiKhoanDAO.deleteTaiKhoan(foundTaiKhoan);
	    	redirectAttributes.addFlashAttribute("successMessage", "Tài khoản đã được xoá thành công.");
	    	return "redirect:/quanly/QLTaiKhoan";
	    }
	}

}
