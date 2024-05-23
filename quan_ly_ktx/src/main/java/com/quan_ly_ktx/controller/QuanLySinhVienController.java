package com.quan_ly_ktx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.quan_ly_ktx.DAO.SinhVienDAO;
import com.quan_ly_ktx.DAO.TaiKhoanDAO;
import com.quan_ly_ktx.Entity.SinhVien.SinhVien;
import com.quan_ly_ktx.Entity.TaiKhoan.TaiKhoan;
import com.quan_ly_ktx.DTO.SinhVienDetailsDTO;
import com.quan_ly_ktx.DTO.MapperSinhVienDetailsDTO;

@Controller
@RequestMapping("/quanly")
public class QuanLySinhVienController {
	@Autowired
	SinhVienDAO sinhVienDAO;
	@Autowired
	TaiKhoanDAO taiKhoanDAO;
	
	
	@RequestMapping(value = "/QLSinhVien", method = RequestMethod.GET)
	public String QLSinhVien(ModelMap modelMap,  @RequestParam(required = false) String sort,
            @RequestParam(required = false) String direction) {
		if((sort == null) && (direction == null)){
			List<SinhVien> resultSinhVien = sinhVienDAO.GetDataSinhVien();
			List<SinhVienDetailsDTO> resultSinhVienCoViPham = sinhVienDAO.GetDataSinhVienCoViPham();
			modelMap.addAttribute("ListSV", resultSinhVien);
			modelMap.addAttribute("ListSV_CoViPham", resultSinhVienCoViPham);
		}else {
			List<SinhVien> sortedSinhVien = sinhVienDAO.sortSinhVienByColumn(sort, direction);
			modelMap.addAttribute("ListSV", sortedSinhVien);
			List<SinhVienDetailsDTO> resultSinhVienCoViPham = sinhVienDAO.GetDataSinhVienCoViPham();
			modelMap.addAttribute("ListSV_CoViPham", resultSinhVienCoViPham);
		}
		List<String> listQuanLySinhVienList = taiKhoanDAO.getListQuanLySinhVien();
		modelMap.addAttribute("ListQuanLySinhVien", listQuanLySinhVienList);
		return "QuanLy/SinhVien/QuanLySinhVien";
	}
		@RequestMapping(value = "QLSinhVien/{maSinhVien}/edit", method = RequestMethod.GET)	public String editSinhVien(@PathVariable("maSinhVien") String maSV, ModelMap modelMap) {		SinhVien sinhVien = sinhVienDAO.getSinhVienByMaSV(maSV);		modelMap.addAttribute("sinhVien", sinhVien);		return "QuanLy/SinhVien/editSinhVien";	}
	
	
	@RequestMapping(value = "QLSinhVien/{maSinhVien}/update", method = RequestMethod.POST)
	public String updateSinhVien(ModelMap modelMap, @ModelAttribute("sinhVienEdit") SinhVien sinhVien,
			@PathVariable String maSinhVien, RedirectAttributes redirectAttributes) {
		System.out.println("Thông tin sinh viên sửa: " + sinhVien);
		/* Truyền thêm mã sinh viên trường hợp sửa thông tin mã sinh viên */
		sinhVienDAO.updateSinhVien(sinhVien, maSinhVien);
		redirectAttributes.addFlashAttribute("successMessage", "Sửa thành công!");
		return "redirect:/quanly/QLSinhVien";
	}
	
	@RequestMapping(value = "QLSinhVien/{maSinhVien}/delete", method = RequestMethod.GET)
	public String deleteSinhVien(ModelMap modelMap, @PathVariable String maSinhVien, RedirectAttributes redirectAttributes) {
		sinhVienDAO.deleteSinhVien(maSinhVien);
		redirectAttributes.addFlashAttribute("successMessage", "Đã xoá sinh viên " + maSinhVien + " thành công!");
		return "redirect:/quanly/QLSinhVien";
	}
	
	@RequestMapping(value = "QLSinhVien/createSV", method = RequestMethod.POST)
	public String createSinhVien(ModelMap modelMap, @ModelAttribute("newSinhVien") SinhVien newSinhVien, RedirectAttributes redirectAttributes) {
		
		if(sinhVienDAO.getSinhVienByMaSV(newSinhVien.getMaSV()) != null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Sinh viên này đã tồn tại!");
			return "redirect:/quanly/QLSinhVien";
		}else {
			redirectAttributes.addFlashAttribute("successMessage", "Đã thêm sinh viên " + newSinhVien.getMaSV() + " thành công!");
			sinhVienDAO.createSinhVien(newSinhVien);
		}
		return "redirect:/quanly/QLSinhVien";
	}
	
	/*
	  @RequestMapping(value = "/QLSinhVien/{column}/sort/{mode}", method =
	  RequestMethod.GET) public String sortSinhVien(ModelMap
	  modelMap, @PathVariable("column") String column, @PathVariable("mode") String
	  mode) { List<SinhVien> sortedSinhVien =
	  sinhVienDAO.sortSinhVienByColumn(column, mode);
	  System.out.println("Danh sách sau khi sort: " + sortedSinhVien);
	  modelMap.addAttribute("ListSV", sortedSinhVien); return
	  "QuanLy/QuanLySinhVien"; }
	 */
	
	@RequestMapping(value = "QLSinhVien/searchSinhVien", method = RequestMethod.GET)
	public String searchSinhVien(@RequestParam(name = "maSV", required = false) String maSV,
            @RequestParam(name = "ho", required = false) String ho,
            @RequestParam(name = "ten", required = false) String ten,
            @RequestParam(name = "gioiTinh", required = false) String gioiTinh,
            @RequestParam(name = "ngaySinh", required = false) String ngaySinh,
            @RequestParam(name = "diaChi", required = false) String diaChi,
            @RequestParam(name = "lop", required = false) String lop,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String direction,
            ModelMap modelMap) {
		if(maSV.equals("") && ho.equals("") && ten.equals("") && gioiTinh.equals("Giới tính") && ngaySinh.equals("")&& diaChi.equals("") && lop.equals("")) {
			System.out.println("hello");
			return "redirect:/quanly/QLSinhVien";
		}else {
				List<SinhVien> resultList  = sinhVienDAO.searchSinhVien(maSV, ho, ten, gioiTinh, ngaySinh, diaChi, lop, sort, direction);
				modelMap.addAttribute("ListSV", resultList);
				/* Bốn câu lệnh dưới gọi lại từ hàm QLSinhVien() */
				List<String> listQuanLySinhVienList = taiKhoanDAO.getListQuanLySinhVien();
				modelMap.addAttribute("ListQuanLySinhVien", listQuanLySinhVienList);
				List<SinhVienDetailsDTO> resultSinhVienCoViPham = sinhVienDAO.GetDataSinhVienCoViPham();
				modelMap.addAttribute("ListSV_CoViPham", resultSinhVienCoViPham);
			
		}
		
		return "QuanLy/SinhVien/QuanLySinhVien";
	}
	
	/*----------------------------------------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------------------------------------*/
	/* Phần này cho mục sinh viên vi phạm */
	@RequestMapping(value = "QuanLySinhVien/SinhVienViPham", method = RequestMethod.GET)
	public String QLSinhVienViPham(ModelMap modelMap,  @RequestParam(required = false) String sort,
            @RequestParam(required = false) String direction) {
			List<SinhVienDetailsDTO> resultSinhVienCoViPham = sinhVienDAO.GetDataSinhVienCoViPham();
			modelMap.addAttribute("ListSV_ViPhamNoiQuy", resultSinhVienCoViPham);
		return "QuanLy/SinhVien/QuanLySinhVienViPham";
	}
}
