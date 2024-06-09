package com.quan_ly_ktx.controller;

import java.awt.print.Printable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import com.quan_ly_ktx.DAO.SinhVienDAO;
import com.quan_ly_ktx.DTO.SinhVienDetailsDTO;
import com.quan_ly_ktx.Entity.HopDong.HopDong;
import com.quan_ly_ktx.Entity.PHONG.Phong;
import com.quan_ly_ktx.Entity.SinhVien.SinhVien;
import com.quan_ly_ktx.service.PHONG.PhongService;

@Controller
@RequestMapping("/quanly")
public class QuanLyHopDongController {
	@Autowired
	HopDongDAO hopDongDAO;
	@Autowired
	SinhVienDAO sinhVienDAO;
	@Autowired
    PhongService phongService;
	
	@RequestMapping(value = "/QLHopDong", method = RequestMethod.GET)
	public String QLHopDong(ModelMap modelMap) {
		List<HopDong> resultHopDong = hopDongDAO.GetDataHopDong();
		modelMap.addAttribute("ListHD", resultHopDong);
		
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<HopDong> listHopDongHetHan = new ArrayList<HopDong>();
		for(HopDong hopDong : resultHopDong) {
			LocalDate ngayHetHan = LocalDate.parse(hopDong.getNgayHetHan(), formatter);
			if(currentDate.isAfter(ngayHetHan)) {
				listHopDongHetHan.add(hopDong);
			}
		}
		modelMap.addAttribute("ListHDHetHan", listHopDongHetHan);
		
		int soLuongHDHetHan = listHopDongHetHan.size();
		modelMap.addAttribute("soLuongHDHetHan", soLuongHDHetHan);
		
		List<Phong> allMaPhongList = phongService.getAllPhongs();
		modelMap.addAttribute("listPhong", allMaPhongList);
		
		List<SinhVien> sinhVienChuaCoHDList = sinhVienDAO.getSVChuaCoHD();
		modelMap.addAttribute("ListSVChuaCoHD", sinhVienChuaCoHDList);
		
		int soLuongSVChuaCoHD = sinhVienChuaCoHDList.size();
		modelMap.addAttribute("soLuongSVChuaCoHD", soLuongSVChuaCoHD);
		
		List<SinhVienDetailsDTO> resultSinhVienCoViPham = sinhVienDAO.GetDataSinhVienCoViPham();
		modelMap.addAttribute("ListSV_CoViPham", resultSinhVienCoViPham);
		
		String maxMaHD = hopDongDAO.getMaxMaHD();
		String nextMaHD = String.format("HD%03d", Integer.parseInt(maxMaHD.substring(2)) + 1);
		modelMap.addAttribute("nextMaHD", nextMaHD);
		
		List<String> allNamHocList = hopDongDAO.getAllNamHoc();
		modelMap.addAttribute("AllNamHoc", allNamHocList);
		return "QuanLy/HopDong/QuanLyHopDong";
	}
	
	
	@RequestMapping(value = "QLHopDong/{maHopDong}/edit", method = RequestMethod.GET)
	public String editHopDong(@PathVariable("maHopDong") String maHD, ModelMap modelMap) {
		HopDong hopDong = hopDongDAO.GetHopDongByMaHD(maHD);
		modelMap.addAttribute("hopDong", hopDong);
		return "QuanLy/HopDong/editHopDong";
	}
	
	@RequestMapping(value = "QLHopDong/{maHopDong}/update", method = RequestMethod.POST)
	public String updateHopDong(@PathVariable("maHopDong") String maHD, ModelMap modelMap, 
			@ModelAttribute("hopDong") HopDong hopDong, RedirectAttributes redirectAttributes) {
		hopDongDAO.updateHopDong(hopDong, maHD);
		redirectAttributes.addFlashAttribute("successMessage", "Sửa thông tin hợp đồng thành công!");
		return "redirect:/quanly/QLHopDong";
	}
	
	@RequestMapping(value = "QLHopDong/createHD", method = RequestMethod.POST)
	public String createHopDong(@ModelAttribute("hopDongMoi") HopDong hopDongMoi, RedirectAttributes redirectAttributes) {
		String maSVTrongHopDong = hopDongMoi.getMaSV();
		int soLuongSVHienTaiTrongPhong = phongService.getPhongById(hopDongMoi.getMaPhong()).getSoLuong();
		int sucChuaToiDaCuaPhong = phongService.getPhongById(hopDongMoi.getMaPhong()).getSucChua();
		System.out.println("soLuongSVHienTaiTrongPhong: " + soLuongSVHienTaiTrongPhong);
		System.out.println("sucChuaToiDaCuaPhong: " + sucChuaToiDaCuaPhong);
		String checkMaHD = hopDongMoi.getMaHD();
		if(!hopDongDAO.checkSVDangConHopDong(maSVTrongHopDong)) {
			if (sinhVienDAO.getSinhVienByMaSV(maSVTrongHopDong) != null) {
				if(!hopDongDAO.checkMaHDExists(checkMaHD)) {
					if(soLuongSVHienTaiTrongPhong != sucChuaToiDaCuaPhong) {
						hopDongDAO.createHD(hopDongMoi, soLuongSVHienTaiTrongPhong);
						redirectAttributes.addFlashAttribute("successMessage", "Hợp đồng đã được tạo thành công.");
						return "redirect:/quanly/QLHopDong";
					}else {
						redirectAttributes.addFlashAttribute("errorMessage", "Phòng " + hopDongMoi.getMaPhong() + " đã đầy!");
						return "redirect:/quanly/QLHopDong";
					}
					
				}else {
					redirectAttributes.addFlashAttribute("errorMessage", "Mã hợp đồng đã tồn tại!");
					return "redirect:/quanly/QLHopDong";
				}
			} else {
				redirectAttributes.addFlashAttribute("errorMessage", "Sinh viên này chưa có thông tin. Vui lòng tạo thông tin sinh viên trước!");
				return "redirect:/quanly/QLHopDong";
			}
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "Sinh viên này đang còn hợp đồng. Không thể tạo hợp đồng mới!");
			return "redirect:/quanly/QLHopDong";
		}
		
	}
	

	
	@RequestMapping(value = "QLHopDong/{maHopDong}/delete", method = RequestMethod.GET)
	public String deleteHopDong(ModelMap modelMap, @PathVariable("maHopDong") String maHD, RedirectAttributes redirectAttributes) {
		HopDong kiemTraHopDong = hopDongDAO.GetHopDongByMaHD(maHD);
		LocalDate ngayHetHan = LocalDate.parse(kiemTraHopDong.getNgayHetHan());
        LocalDate ngayHienTai = LocalDate.now();
        if(ngayHetHan.isBefore(ngayHienTai)) {
            hopDongDAO.deleteHopDong(maHD);
            redirectAttributes.addFlashAttribute("successMessage", "Đã xoá hợp đồng " + maHD + " thành công!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Không thể xoá hợp đồng vì hợp đồng vẫn còn hạn!");
        }
		return "redirect:/quanly/QLHopDong";
	}
	
//	---------------------------------------------------------------------------
//	----------------------------Hợp đồng hết hạn-------------------------------
	@RequestMapping(value = "/QLHopDong/HopDongHetHan", method = RequestMethod.GET)
	public String QLHopDongHetHan(ModelMap modelMap) {
		List<HopDong> resultHopDong = hopDongDAO.GetDataHopDong();
		modelMap.addAttribute("ListHD", resultHopDong);
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<HopDong> listHopDongHetHan = new ArrayList<HopDong>();
		for(HopDong hopDong : resultHopDong) {
			LocalDate ngayHetHan = LocalDate.parse(hopDong.getNgayHetHan(), formatter);
			if(currentDate.isAfter(ngayHetHan)) {
				listHopDongHetHan.add(hopDong);
			}
		}
		modelMap.addAttribute("ListHDHetHan", listHopDongHetHan);
		String maxMaHD = hopDongDAO.getMaxMaHD();
		String nextMaHD = String.format("HD%03d", Integer.parseInt(maxMaHD.substring(2)) + 1);
		modelMap.addAttribute("nextMaHD", nextMaHD);
		List<String> allMaPhongList = phongService.getAllMaPhong();
		modelMap.addAttribute("ListPhong", allMaPhongList);
		List<SinhVienDetailsDTO> resultSinhVienCoViPham = sinhVienDAO.GetDataSinhVienCoViPham();
		modelMap.addAttribute("ListSV_CoViPham", resultSinhVienCoViPham);
		return "QuanLy/HopDong/HopDongHetHan";
	}
	
}
