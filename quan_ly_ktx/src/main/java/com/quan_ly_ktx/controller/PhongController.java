package com.quan_ly_ktx.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.quan_ly_ktx.DAO.PhongDAO;
import com.quan_ly_ktx.DAO.VT_PhongDAO;
import com.quan_ly_ktx.Entity.PHONG.Phong;
import com.quan_ly_ktx.service.PHONG.PhongService;

@Controller
@RequestMapping("/phong")
public class PhongController {

    @Autowired
    PhongService phongService;
    @Autowired
    PhongDAO phongDAO;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showPhongList(Model model) {
        List<Phong> listPhong = phongService.getAllPhongs();
        for (Phong phong : listPhong) {
            int soLuongHopDong = phongService.countHDByMaPhong(phong.getMaPhong());
            phong.setSoLuong(soLuongHopDong);
        }
        model.addAttribute("listPhong", listPhong);
        return "Phong/QuanLyPhong"; // Thay đổi tên view tương ứng
    }

    @RequestMapping(value = "/update-phong", method = RequestMethod.POST)
    public String updatePhong(@ModelAttribute("phong") Phong phong) {
        phongService.updatePhong(phong);
        return "redirect:/phong/list";
    }
    
    @RequestMapping(value = "/add-phong", method = RequestMethod.POST)
    public String addVatTu(@ModelAttribute("phong") Phong phong, RedirectAttributes redirectAttributes) {
            String maPhong = phong.getMaKhu() + phong.getMaSo();
            phong.setMaPhong(maPhong);
            System.out.println("Ma phong la: " + maPhong);
            if (phongService.existsByMaPhong(maPhong)) {
            	redirectAttributes.addFlashAttribute("errorMessage","Mã phòng đã tồn tại !");
            	return "redirect:/phong/list";
            }
            else {
            	phongService.addPhong(phong);
                redirectAttributes.addFlashAttribute("successMessage","Thêm thành công");
                return "redirect:/phong/list";
            }
        // Trả về trang hiện tại (trang quản lý vật tư)
        
    }
    
    @Autowired
    private VT_PhongDAO vtPhongDAO;
    
    
    @RequestMapping(value = "/delete-phong/{maPhong}", method = RequestMethod.GET)
    public String deletePhong(@PathVariable("maPhong") String maPhong, RedirectAttributes redirectAttributes) {
        if (vtPhongDAO.existsReferencesToPhong(maPhong) || phongDAO.checkExistInHDD(maPhong)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Phòng đang được sử dụng");
            return "redirect:/phong/list";
        } else {
        	redirectAttributes.addFlashAttribute("successMessage", "xóa thành công");
            phongService.deletePhong(maPhong);
            return "redirect:/phong/list";
        }
    }
   
	@RequestMapping(value = "/list/{column}/sort/{mode}", method = RequestMethod.GET)
	public String sortPhong(Model model, @PathVariable("column") String column, @PathVariable("mode") String mode) {
	    List<Phong> sortedPhong = phongService.sortPhongByColumn(column, mode);
	    System.out.println("Danh sách sau khi sort: " +sortedPhong);
	    model.addAttribute("listPhong", sortedPhong);
	    return "Phong/QuanLyPhong";
	}
	
	@RequestMapping(value = "/delete-selected", method = RequestMethod.POST)
    public String deleteSelected(@RequestParam("selectedItems") List<String> selectedItems, RedirectAttributes redirectAttributes) {
        try {
            phongService.deletePhongByIds(selectedItems);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa thành công các phòng đã lựa chọn.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi xóa các phòng(Phòng có thể đang được sử dụng).");
        }
        return "redirect:/phong/list";
    }
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public String timKiemTheoBang(Model model, 
	                              @RequestParam(name = "maPhong", required = false) String maPhong,
	                              @RequestParam(name = "tinhTrang", required = false) String tinhTrang,
	                              @RequestParam(name = "sucChua", required = false) String sucChua,
	                              @RequestParam(name = "khuKTX", required = false) String khuKTX,
	                              @RequestParam(name = "soLuong", required = false) String soLuong,
	                              @RequestParam(name = "maLoaiPhong", required = false) String maLoaiPhong) {

	    model.addAttribute("maPhong", maPhong);
	    model.addAttribute("tinhTrang", tinhTrang);
	    model.addAttribute("sucChua", sucChua);
	    model.addAttribute("khuKTX", khuKTX);
	    model.addAttribute("soLuong", soLuong);
	    model.addAttribute("maLoaiPhong", maLoaiPhong);

	    List<Phong> searchedPhong = phongService.timKiemTheoBang(maPhong, tinhTrang, sucChua, khuKTX, soLuong, maLoaiPhong);
	    model.addAttribute("listPhong", searchedPhong);
	    return "Phong/QuanLyPhong";
	}




}
