package com.quan_ly_ktx.controller;

<<<<<<< HEAD
=======
import java.time.LocalDate;
>>>>>>> 17e1134b9aa9bdc953f04b1a95e57e6ab57c8d50
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
<<<<<<< HEAD
import com.quan_ly_ktx.Entity.HopDong.HopDong;
=======
import com.quan_ly_ktx.DAO.SinhVienDAO;
import com.quan_ly_ktx.DTO.SinhVienDetailsDTO;
import com.quan_ly_ktx.Entity.HopDong.HopDong;
import com.quan_ly_ktx.Entity.SinhVien.SinhVien;
import com.quan_ly_ktx.service.PHONG.PhongService;
>>>>>>> 17e1134b9aa9bdc953f04b1a95e57e6ab57c8d50

@Controller
@RequestMapping("/quanly")
public class QuanLyHopDongController {
	@Autowired
	HopDongDAO hopDongDAO;
<<<<<<< HEAD
=======
	@Autowired
	SinhVienDAO sinhVienDAO;
	@Autowired
    PhongService phongService;
>>>>>>> 17e1134b9aa9bdc953f04b1a95e57e6ab57c8d50
	
	@RequestMapping(value = "/QLHopDong", method = RequestMethod.GET)
	public String QLSinhVien(ModelMap modelMap) {
		List<HopDong> resultHopDong = hopDongDAO.GetDataHopDong();
		modelMap.addAttribute("ListHD", resultHopDong);
<<<<<<< HEAD
		System.out.println("Danh sách hợp dồng: " + resultHopDong);
=======
		List<String> allMaPhongList = phongService.getAllMaPhong();
		modelMap.addAttribute("ListPhong", allMaPhongList);
		List<SinhVien> sinhVienChuaCoHDList = sinhVienDAO.getSVChuaCoHD();
		modelMap.addAttribute("ListSVChuaCoHD", sinhVienChuaCoHDList);
		int soLuongSVChuaCoHD = sinhVienChuaCoHDList.size();
		modelMap.addAttribute("soLuongSVChuaCoHD", soLuongSVChuaCoHD);
		List<SinhVienDetailsDTO> resultSinhVienCoViPham = sinhVienDAO.GetDataSinhVienCoViPham();
		modelMap.addAttribute("ListSV_CoViPham", resultSinhVienCoViPham);
>>>>>>> 17e1134b9aa9bdc953f04b1a95e57e6ab57c8d50
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
<<<<<<< HEAD
		String checkMaSVCoHopDong = hopDongMoi.getMaSV();
		String checkMaHD = hopDongMoi.getMaHD();
		if(!hopDongDAO.checkSVDangConHopDong(checkMaSVCoHopDong)) {
			if(!hopDongDAO.checkMaHDExists(checkMaHD)) {
				hopDongDAO.createHD(hopDongMoi);
				redirectAttributes.addFlashAttribute("successMessage", "Hợp đồng đã được tạo thành công.");
				return "redirect:/quanly/QLHopDong";
			}else {
				redirectAttributes.addFlashAttribute("errorMessage", "Mã hợp đồng đã tồn tại!");
=======
		String maSVTrongHopDong = hopDongMoi.getMaSV();
		int soLuongSVHienTaiTrongPhong = phongService.getPhongById(hopDongMoi.getMaPhong()).getSoLuong();
		int sucChuaToiDaCuaPhong = phongService.getPhongById(hopDongMoi.getMaPhong()).getSucChua();
		String checkMaHD = hopDongMoi.getMaHD();
		if(!hopDongDAO.checkSVDangConHopDong(maSVTrongHopDong)) {
			if (sinhVienDAO.getSinhVienByMaSV(maSVTrongHopDong) != null) {
				if(!hopDongDAO.checkMaHDExists(checkMaHD)) {
					if(soLuongSVHienTaiTrongPhong != sucChuaToiDaCuaPhong) {
						hopDongDAO.createHD(hopDongMoi);
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
>>>>>>> 17e1134b9aa9bdc953f04b1a95e57e6ab57c8d50
				return "redirect:/quanly/QLHopDong";
			}
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "Sinh viên này đang còn hợp đồng. Không thể tạo hợp đồng mới!");
			return "redirect:/quanly/QLHopDong";
		}
		
	}
<<<<<<< HEAD
=======
	
	@RequestMapping(value = "QLHopDong/{maHopDong}/update", method = RequestMethod.POST)
	public String updateHopDong(@PathVariable("maHopDong") String maHD, ModelMap modelMap, 
			@ModelAttribute("hopDong") HopDong hopDong, RedirectAttributes redirectAttributes) {
		hopDongDAO.updateHopDong(hopDong, maHD);
		redirectAttributes.addFlashAttribute("successMessage", "Sửa thành công!");
		return String.format("redirect:/quanly/QLHopDong/%s/edit", maHD);
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
>>>>>>> 17e1134b9aa9bdc953f04b1a95e57e6ab57c8d50
}
