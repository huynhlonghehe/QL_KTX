package com.quan_ly_ktx.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

import com.quan_ly_ktx.DAO.HopDongDAO;
import com.quan_ly_ktx.DAO.SinhVienQuyTac_QuyTac_DAO;
import com.quan_ly_ktx.DAO.SinhVienDAO;
import com.quan_ly_ktx.DAO.TaiKhoanDAO;
import com.quan_ly_ktx.Entity.HopDong.HopDong;
import com.quan_ly_ktx.Entity.PHONG.Phong;
import com.quan_ly_ktx.Entity.SinhVien.QuyTac;
import com.quan_ly_ktx.Entity.SinhVien.SinhVien;
import com.quan_ly_ktx.Entity.SinhVien.SinhVien_QuyTac;
import com.quan_ly_ktx.Entity.TaiKhoan.TaiKhoan;
import com.quan_ly_ktx.service.PHONG.PhongService;
import com.quan_ly_ktx.DTO.SinhVienDetailsDTO;
import com.quan_ly_ktx.DTO.MapperSinhVienDetailsDTO;

@Controller
@RequestMapping("/quanly")
public class QuanLySinhVienController {
	@Autowired
	SinhVienDAO sinhVienDAO;
	@Autowired
	TaiKhoanDAO taiKhoanDAO;
	@Autowired
	HopDongDAO hopDongDAO;
	@Autowired
	SinhVienQuyTac_QuyTac_DAO sinhVienQuyTac_quyTac_Dao;
	@Autowired
    PhongService phongService;
	
	
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
		List<String> listQuanLySinhVienList = taiKhoanDAO.getListQuanLy();
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
		HopDong test = hopDongDAO.GetHopDongByMaSV(maSinhVien);
		if(hopDongDAO.GetHopDongByMaSV(maSinhVien) == null) {
			sinhVienDAO.deleteSinhVien(maSinhVien);
			redirectAttributes.addFlashAttribute("successMessage", "Đã xoá sinh viên " + maSinhVien + " thành công!");
		}else {
			redirectAttributes.addFlashAttribute("errorMessage", "Sinh viên " + maSinhVien + " đang có hợp đồng còn hạn!");
		}
		return "redirect:/quanly/QLSinhVien";
	}
	
	@RequestMapping(value = "QLSinhVien/createSV", method = RequestMethod.POST)
	public String createSinhVien(ModelMap modelMap, @ModelAttribute("newSinhVien") SinhVien newSinhVien, RedirectAttributes redirectAttributes) {
		
		if(sinhVienDAO.getSinhVienByMaSV(newSinhVien.getMaSV()) != null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Sinh viên này đã tồn tại!");
			return "redirect:/quanly/QLSinhVien";
		}else {
			sinhVienDAO.createSinhVien(newSinhVien);
			redirectAttributes.addFlashAttribute("successMessage", "Đã thêm sinh viên " + newSinhVien.getMaSV() + " thành công!");
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
            @RequestParam(name = "dieuKienO", required = false) String dieuKien,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String direction,
            ModelMap modelMap) {
		
		if(maSV.equals("") && ho.equals("") && ten.equals("") && gioiTinh.equals(" ") && ngaySinh.equals("")&& diaChi.equals("") && lop.equals("") && dieuKien.equals(" ")) {
			return "redirect:/quanly/QLSinhVien";
		}else {
				List<SinhVien> resultList  = sinhVienDAO.searchSinhVien(maSV.trim(), ho.trim(), ten.trim(), gioiTinh.trim(), ngaySinh.trim(), diaChi.trim(), lop.trim(), dieuKien, sort, direction);
//				modelMap.addAttribute("ListSV", resultList);
				/* Bốn câu lệnh dưới gọi lại từ hàm QLSinhVien() */
				List<String> listQuanLySinhVienList = taiKhoanDAO.getListQuanLy();
				modelMap.addAttribute("ListQuanLySinhVien", listQuanLySinhVienList);
				List<SinhVienDetailsDTO> resultSinhVienCoViPham = sinhVienDAO.GetDataSinhVienCoViPham();
				List<SinhVien> sinhVienDuDieuKienList = new ArrayList<SinhVien>();
				List<SinhVien> sinhVienKhongDuDieuKienList = new ArrayList<SinhVien>();
				for (SinhVien sv : resultList) {
					int xetDieuKien = 0;
		            for (SinhVienDetailsDTO svDTO : resultSinhVienCoViPham) {
		            	if (sv.getMaSV().equals(svDTO.getMaSV())){
		            		if(svDTO.getMucDoViPham() == 1) {
		            			xetDieuKien += 1;
		            		}
		            		else if(svDTO.getMucDoViPham() == 2) {
		            			xetDieuKien += 2;
		            		}
		            		else if(svDTO.getMucDoViPham() == 3) {
		            			xetDieuKien += 4;
		            		}
		            	}
		            }
		            if(xetDieuKien > 3) {
		            	sinhVienKhongDuDieuKienList.add(sv);
		            }else {
		            	sinhVienDuDieuKienList.add(sv);
		            }
		        }
				if(dieuKien.equals("duDieuKien")) {
					resultList = sinhVienDuDieuKienList;
				}else if(dieuKien.equals("khongDuDieuKien")){
					System.out.println("Có vô đây nha");
					resultList = sinhVienKhongDuDieuKienList;
				}
				modelMap.addAttribute("ListSV", resultList);
				modelMap.addAttribute("ListSV_CoViPham", resultSinhVienCoViPham);
				modelMap.addAttribute("maSV", maSV);
				modelMap.addAttribute("ho", ho);
				modelMap.addAttribute("ten", ten);
				modelMap.addAttribute("gioiTinh", gioiTinh);
				modelMap.addAttribute("ngaySinh", ngaySinh);
				modelMap.addAttribute("diaChi", diaChi);
				modelMap.addAttribute("lop", lop);
				modelMap.addAttribute("dieuKien", dieuKien);
		}
		return "QuanLy/SinhVien/QuanLySinhVien";
	}
	
	/*----------------------------------------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------------------------------------*/
	/* Phần này cho mục sinh viên vi phạm */
	@RequestMapping(value = "QLSinhVien/SinhVienViPham", method = RequestMethod.GET)
	public String QLSinhVienViPham(ModelMap modelMap,  @RequestParam(required = false) String sort,
            @RequestParam(required = false) String direction) {
		List<QuyTac> quyTac = sinhVienQuyTac_quyTac_Dao.getAllQuyTac();
		modelMap.addAttribute("List_QuyTac", quyTac);
		List<String> listQuanLySinhVienList = taiKhoanDAO.getListQuanLy();
		modelMap.addAttribute("ListQuanLySinhVien", listQuanLySinhVienList);
		String maxMaQuyTac = sinhVienQuyTac_quyTac_Dao.getMaxMaQuyTac();
		String nextMaQuyTac= String.format("QT%03d", Integer.parseInt(maxMaQuyTac.substring(2)) + 1);
		modelMap.addAttribute("nextMaQuyTac", nextMaQuyTac);
		List<String> list_Phong = phongService.getAllMaPhong();
		modelMap.addAttribute("List_Phong", list_Phong);
		if(sort == null && direction == null) {
			List<SinhVienDetailsDTO> resultSinhVienCoViPham = sinhVienDAO.GetDataSinhVienCoViPham();
			modelMap.addAttribute("ListSV_ViPhamNoiQuy", resultSinhVienCoViPham);
		}else {
			List<SinhVienDetailsDTO> resultSinhVienCoViPham = sinhVienDAO.sortSinhVienViPhamByColumn(sort, direction);
			modelMap.addAttribute("ListSV_ViPhamNoiQuy", resultSinhVienCoViPham);
		}
			
		return "QuanLy/SinhVien/QuanLySinhVienViPham";
	}
	
	@RequestMapping(value = "QLSinhVien/createReport", method = RequestMethod.POST)
	public String createReport(ModelMap modelMap, @ModelAttribute("newReport") SinhVien_QuyTac newReport, RedirectAttributes redirectAttributes) {
		if(sinhVienDAO.getSinhVienByMaSV(newReport.getMaSV()) != null) {
			HopDong hopDongCuaSV = hopDongDAO.GetHopDongByMaSV(newReport.getMaSV());
			if(hopDongCuaSV != null) {
				String ngayHetHanHopDongCuaSV = hopDongCuaSV.getNgayHetHan();
				LocalDate currentDate = LocalDate.now();
//				Chuyển ngayHetHanHopDongCuaSV sang localdate
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate ngayHetHan = LocalDate.parse(ngayHetHanHopDongCuaSV, formatter);
//				Kết thúc chuyển ngayHetHanHopDongCuaSV sang localdate
//				Chuyển thời gian vi phạm theo đúng định dạng datetime trong sql
				DateTimeFormatter formatter_ThoiGianViPham = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
			    LocalDateTime thoiGianViPham = LocalDateTime.parse(newReport.getThoiGianViPham(), formatter_ThoiGianViPham);
			    Timestamp thoiGianViPhamSQL = Timestamp.valueOf(thoiGianViPham);
			    //System.out.println("Thời gian vi phạm: " + thoiGianViPhamSQL);
//			    Kết thúc chuyển thời gian vi phạm theo đúng định dạng datetime trong sql
				boolean isExpired = currentDate.isBefore(ngayHetHan);
				if(isExpired) {
					int tongDiem = sinhVienQuyTac_quyTac_Dao.soLoiCuaSinhVien(newReport.getMaSV());
					if(tongDiem < 4) {
						sinhVienQuyTac_quyTac_Dao.createReport(newReport, thoiGianViPhamSQL);
						redirectAttributes.addFlashAttribute("succesMessage", "Biên bản mới được tạo thành công!");
						return "redirect:/quanly/QLSinhVien/SinhVienViPham";
					}else {
						redirectAttributes.addFlashAttribute("errorMessage", "Sinh viên " + newReport.getMaSV() + " đã quá số lần vi phạm!");
						return "redirect:/quanly/QLSinhVien/SinhVienViPham";
					}
				}else {
					redirectAttributes.addFlashAttribute("errorMessage", "Sinh viên " + newReport.getMaSV() + " không còn ở ktx vì hết hợp đồng!");
					return "redirect:/quanly/QLSinhVien/SinhVienViPham";
				}
			}else {
				redirectAttributes.addFlashAttribute("errorMessage", "Sinh viên " + newReport.getMaSV() + " chưa có hợp đồng ở KTX!");
				return "redirect:/quanly/QLSinhVien/SinhVienViPham";
			}
		}else {
			redirectAttributes.addFlashAttribute("errorMessage", "Sinh viên " + newReport.getMaSV() + " không ở KTX!");
			return "redirect:/quanly/QLSinhVien/SinhVienViPham";
		}
	}
	
	@RequestMapping(value = "QLSinhVien/searchSinhVienViPham", method = RequestMethod.GET)
	public String searchSinhVienViPham(@RequestParam(name = "maSV", required = false) String maSV,
            @RequestParam(name = "ho", required = false) String ho,
            @RequestParam(name = "ten", required = false) String ten,
            @RequestParam(name = "maPhong", required = false) String maPhong,
            @RequestParam(name = "noiDungViPham", required = false) String noiDungViPham,
            @RequestParam(name = "mucDoViPham", required = false) String mucDoVP,
            @RequestParam(name = "nguoiLapBienBan", required = false) String nguoiLapBienBan,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String direction,
            ModelMap modelMap) {
		int mucDoViPham = 0;
		if(!mucDoVP.equals("")) {
			mucDoViPham = Integer.parseInt(mucDoVP);
		}
		List<QuyTac> quyTac = sinhVienQuyTac_quyTac_Dao.getAllQuyTac();
		modelMap.addAttribute("List_QuyTac", quyTac);
		List<String> listQuanLySinhVienList = taiKhoanDAO.getListQuanLy();
		modelMap.addAttribute("ListQuanLySinhVien", listQuanLySinhVienList);
		List<String> list_Phong = phongService.getAllMaPhong();
		modelMap.addAttribute("List_Phong", list_Phong);
		if(maSV.equals("") && ho.equals("") && ten.equals("") && maPhong.equals("") && noiDungViPham.equals("") && ( mucDoViPham == 0) && nguoiLapBienBan.equals("")) {
			return "redirect:/quanly/QLSinhVien/SinhVienViPham";
		}else {
			List<SinhVienDetailsDTO> resultSearchSinhVienViPham = sinhVienDAO.searchSinhVienViPham(maSV.trim(), ho.trim(), ten.trim(), maPhong.trim(), noiDungViPham.trim(), mucDoViPham, nguoiLapBienBan.trim(), sort, direction);
			modelMap.addAttribute("ListSV_ViPhamNoiQuy", resultSearchSinhVienViPham);
			modelMap.addAttribute("maSV", maSV.trim());
			modelMap.addAttribute("ho", ho.trim());
			modelMap.addAttribute("ten", ten.trim());
			modelMap.addAttribute("maPhong_backEnd", maPhong.trim());
			modelMap.addAttribute("noiDungViPham", noiDungViPham.trim());
			modelMap.addAttribute("mucDoViPham", mucDoVP.trim());
			modelMap.addAttribute("nguoiLapBienBan", nguoiLapBienBan.trim());
		}
		return "QuanLy/SinhVien/QuanLySinhVienViPham";
	}
	
	@RequestMapping(value = "QLSinhVien/SinhVienViPham/editDetailReport")
	public String editDetailReportForm(@ModelAttribute("editDetailReport") SinhVienDetailsDTO editDetailReport, RedirectAttributes redirectAttributes) {
		int maSVVP = editDetailReport.getMaSVVP();
		String maSV = editDetailReport.getMaSV();
		String maQuyTac = editDetailReport.getMaQuyTac();
		DateTimeFormatter formatter_ThoiGianViPham = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
	    LocalDateTime thoiGianViPham = LocalDateTime.parse(editDetailReport.getThoiGianViPham(), formatter_ThoiGianViPham);
	    Timestamp thoiGianViPhamSQL = Timestamp.valueOf(thoiGianViPham);
		sinhVienDAO.updateSinhVienViPham(maSVVP, maQuyTac, thoiGianViPhamSQL);
		redirectAttributes.addFlashAttribute("successMessage", "Chỉnh sửa thông tin vi phạm thành công!");
		return "redirect:/quanly/QLSinhVien/SinhVienViPham";
	}
	
	@RequestMapping(value = "QLSinhVien/{maSVVP}/deleteDetailReport", method = RequestMethod.GET)
	public String deleteSinhVienViPham(@PathVariable("maSVVP") int maSVVP, RedirectAttributes redirectAttributes) {
		sinhVienDAO.deleteSinhVienViPham(maSVVP);
		redirectAttributes.addFlashAttribute("successMessage", "Xoá thông tin sinh viên vi phạm thành công!");
		return "redirect:/quanly/QLSinhVien/SinhVienViPham";
	}
	/*----------------------------------------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------------------------------------*/
	/*----------------------------------------------------------------------------------------------------------------------*/
//	Phần này cho quy tắc
	@RequestMapping(value = "QLSinhVien/SinhVienViPham/createRule", method = RequestMethod.POST)
	public String createRule(@ModelAttribute("newRule") QuyTac newQuyTac, RedirectAttributes redirectAttributes) {
		String maQuyTac = newQuyTac.getMaQuyTac();
		if(sinhVienQuyTac_quyTac_Dao.getRuleByMaQuyTac(maQuyTac) == null) {
			sinhVienQuyTac_quyTac_Dao.createRule(newQuyTac);
			redirectAttributes.addFlashAttribute("successMessage", "Thêm quy tắc mới thành công!");
			return "redirect:/quanly/QLSinhVien/SinhVienViPham";
		}else {
			redirectAttributes.addFlashAttribute("errorMessage", "Mã quy tắc này đã tồn tại!");
			return "redirect:/quanly/QLSinhVien/SinhVienViPham#ListQuyTac";
		}
	}
	
	@RequestMapping(value = "QLSinhVien/SinhVienViPham/editRule", method = RequestMethod.POST)
	public String editRule(@RequestParam(name = "maQuyTac", required = false) String maQuyTac, @RequestParam(name = "tenQuyTac", required = false) String tenQuyTac,
			@RequestParam(name = "mucDoViPham", required = false) String mucDoViPham,
			RedirectAttributes redirectAttributes) {
		sinhVienQuyTac_quyTac_Dao.editRule(maQuyTac, tenQuyTac, mucDoViPham);
		redirectAttributes.addFlashAttribute("successMessage", "Đã sửa!");
		return "redirect:/quanly/QLSinhVien/SinhVienViPham";
	}
	
	@RequestMapping(value = "QLSinhVien/SinhVienViPham/{maQuyTac}/deleteRule", method = RequestMethod.GET)
	public String deleteRule(@PathVariable("maQuyTac") String maQuyTac, RedirectAttributes redirectAttributes) {
		sinhVienQuyTac_quyTac_Dao.deleteRule(maQuyTac);
		redirectAttributes.addFlashAttribute("successMessage", "Xoá quy tắc thành công!");
		return "redirect:/quanly/QLSinhVien/SinhVienViPham";
	}
	
}
