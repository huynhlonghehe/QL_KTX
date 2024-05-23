package com.quan_ly_ktx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.quan_ly_ktx.DAO.HopDongDAO;
import com.quan_ly_ktx.Entity.HopDong.HopDong;

@Controller
@RequestMapping("/quanly")
public class QuanLyHopDongController {
	@Autowired
	HopDongDAO hopDongDAO;
	
	@RequestMapping(value = "/QLHopDong", method = RequestMethod.GET)
	public String QLSinhVien(ModelMap modelMap) {
		List<HopDong> resultHopDong = hopDongDAO.GetDataHopDong();
		modelMap.addAttribute("ListHD", resultHopDong);
		System.out.println("Danh sách hợp dồng: " + resultHopDong);
		return "QuanLy/HopDong/QuanLyHopDong";
	}
	
	
	@RequestMapping(value = "QLHopDong/{maHopDong}/edit", method = RequestMethod.GET)
	public String editHopDong(@PathVariable("maHopDong") String maHD, ModelMap modelMap) {
		HopDong hopDong = hopDongDAO.GetHopDongByMaHD(maHD);
		modelMap.addAttribute("hopDong", hopDong);
		return "QuanLy/HopDong/editHopDong";
	}
	
	@RequestMapping(value = "QLHopDong/createHD", method = RequestMethod.POST)
	public String createHopDong(@ModelAttribute("hopDongMoi") HopDong hopDongMoi, RedirectAttributes redirectAttributes) {
		String checkMaSVCoHopDong = hopDongMoi.getMaSV();
		String checkMaHD = hopDongMoi.getMaHD();
		if(!hopDongDAO.checkSVDangConHopDong(checkMaSVCoHopDong)) {
			if(!hopDongDAO.checkMaHDExists(checkMaHD)) {
				hopDongDAO.createHD(hopDongMoi);
				redirectAttributes.addFlashAttribute("successMessage", "Hợp đồng đã được tạo thành công.");
				return "redirect:/quanly/QLHopDong";
			}else {
				redirectAttributes.addFlashAttribute("errorMessage", "Mã hợp đồng đã tồn tại!");
				return "redirect:/quanly/QLHopDong";
			}
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "Sinh viên này đang còn hợp đồng. Không thể tạo hợp đồng mới!");
			return "redirect:/quanly/QLHopDong";
		}
		
	}
}
