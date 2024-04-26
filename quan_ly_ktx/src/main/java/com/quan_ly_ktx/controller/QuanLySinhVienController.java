package com.quan_ly_ktx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.quan_ly_ktx.DAO.SinhVienDAO;
import com.quan_ly_ktx.Entity.SinhVien;

@Controller
@RequestMapping("/quanly")
public class QuanLySinhVienController {
	@Autowired
	SinhVienDAO sinhVienDAO;
	
	@RequestMapping(value = "/QLSinhVien", method = RequestMethod.GET)
	public String QLSinhVien(ModelMap modelMap) {
		List<SinhVien> resultSinhVien = sinhVienDAO.GetDataSinhVien();
		modelMap.addAttribute("ListSV", resultSinhVien);
		return "QuanLy/QuanLySinhVien";
	}
		@RequestMapping(value = "QLSinhVien/{maSinhVien}/edit", method = RequestMethod.GET)	public String editSinhVien(@PathVariable("maSinhVien") String maSV, ModelMap modelMap,			@PathVariable String maSinhVien) {		SinhVien sinhVien = sinhVienDAO.getSinhVienByMaSV(maSV);		modelMap.addAttribute("sinhVien", sinhVien);		return "QuanLy/editSinhVien";	}
	
	
	@RequestMapping(value = "QLSinhVien/{maSinhVien}/update", method = RequestMethod.POST)
	public String updateSinhVien(ModelMap modelMap, @ModelAttribute("sinhVien") SinhVien sinhVien,
			@PathVariable String maSinhVien) {
		sinhVienDAO.updateSinhVien(sinhVien);
		return "redirect:/quanly/QLSinhVien";
	}
	
	@RequestMapping(value = "QLSinhVien/{maSinhVien}/delete", method = RequestMethod.GET)
	public String deleteSinhVien(ModelMap modelMap, @ModelAttribute("sinhVien") SinhVien sinhVien,
			@PathVariable String maSinhVien) {
		System.out.println("Mã sinh viên được gửi đi: " + maSinhVien);
		System.out.println("Thông tin sinh viên từ formmmm: " + sinhVien);
		sinhVienDAO.updateSinhVien(sinhVien);
		return "redirect:/quanly/QLSinhVien";
	}
	
	@RequestMapping(value = "/QLSinhVien/{column}/sort/{mode}", method = RequestMethod.GET)
	public String sortSinhVien(ModelMap modelMap, @PathVariable("column") String column, @PathVariable("mode") String mode) {
	    List<SinhVien> sortedSinhVien = sinhVienDAO.sortSinhVienByColumn(column, mode);
	    System.out.println("Danh sách sau khi sort: " + sortedSinhVien);
	    modelMap.addAttribute("ListSV", sortedSinhVien);
	    return "QuanLy/QuanLySinhVien";
	}
}
