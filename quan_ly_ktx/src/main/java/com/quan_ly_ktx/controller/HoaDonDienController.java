package com.quan_ly_ktx.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.quan_ly_ktx.Entity.HoaDonDien.HoaDonDien;
import com.quan_ly_ktx.service.HOADONDIEN.HoaDonDienService;
import com.quan_ly_ktx.service.PHONG.PhongService;

@Controller
@RequestMapping("/hoadondien")
public class HoaDonDienController {

    @Autowired
    private HoaDonDienService hoaDonDienService;
    @Autowired
    private PhongService phongService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showHoaDonDienList(Model model) {
        List<HoaDonDien> listHoaDonDien = hoaDonDienService.getAllHoaDonDien();
        List<String> listPhong = phongService.getAllMaPhong();
        model.addAttribute("listHDD", listHoaDonDien);
        model.addAttribute("listPhong", listPhong);
        return "HoaDonDien/QuanLyHDD";
    }

    @RequestMapping(value = "/update-HDD", method = RequestMethod.POST)
    public String updateHoaDonDien(@ModelAttribute("hoaDonDien") HoaDonDien hoaDonDien) {
    	System.out.println(hoaDonDien.getNgayTao());
        hoaDonDienService.updateHoaDonDien(hoaDonDien);
        return "redirect:/hoadondien/list";
    }
    
    @RequestMapping(value = "/get-chi-so-truoc", method = RequestMethod.GET)
    @ResponseBody
    public String getChiSoTruoc(@RequestParam("maPhong") String maPhong) {
        // Retrieve the previous value based on the maPhong from the database
        // For simplicity, let's assume it returns a dummy value
        int chiSoTruoc = hoaDonDienService.getChiSoTruocByMaPhong(maPhong);
        System.out.println(chiSoTruoc);
        return String.valueOf(chiSoTruoc);
    }

    
    
    @RequestMapping(value = "/add-HDD", method = RequestMethod.POST)
    public String addHoaDonDien(@ModelAttribute("hoaDonDien") HoaDonDien hoaDonDien, RedirectAttributes redirectAttributes) {
        
        if(!hoaDonDienService.existsByMaHDD(hoaDonDien.getMaHDD())) {
            redirectAttributes.addFlashAttribute("successMessage", "Thêm thành công");
            hoaDonDienService.addHoaDonDien(hoaDonDien);
            return "redirect:/hoadondien/list";
        }
        else {
        	redirectAttributes.addFlashAttribute("errorMessage", "Mã hóa đơn tồn tại");
        	return "redirect:/hoadondien/list";
        }

          
    }

    @RequestMapping(value = "/delete-HDD/{maHDD}", method = RequestMethod.GET)
    public String deleteHoaDonDien(@PathVariable String maHDD, RedirectAttributes redirectAttributes) {
        hoaDonDienService.deleteHoaDonDien(maHDD);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa thành công");
        return "redirect:/hoadondien/list";
    }

    @RequestMapping(value = "/list/{column}/sort/{mode}", method = RequestMethod.GET)
    public String sortHoaDonDien(Model model, @PathVariable("column") String column, @PathVariable("mode") String mode) {
        List<HoaDonDien> sortedHoaDonDien = hoaDonDienService.sortHDDByColumn(column, mode);
        model.addAttribute("listHoaDonDien", sortedHoaDonDien);
        return "HoaDonDien/QuanLyHoaDonDien";
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String timKiemTheoBang(Model model,
                                   @RequestParam(name = "maHDD", required = false) String maHDD,
                                   @RequestParam(name = "chiSoTruoc", required = false) String chiSoTruoc,
                                   @RequestParam(name = "chiSoSau", required = false) String chiSoSau,
                                   @RequestParam(name = "heSoTienDien", required = false) String heSoTienDien,
                                   @RequestParam(name = "tienNo", required = false) String tienNo,
                                   @RequestParam(name = "maPhong", required = false) String maPhong,
                                   @RequestParam(name = "ngayTao", required = false) String ngayTao,
                                   @RequestParam(name = "ngaySuaDoi", required = false) String ngaySuaDoi,
                                   @RequestParam(name = "nguoiSuaDoiCuoi", required = false) String nguoiSuaDoiCuoi) {

        model.addAttribute("maHDD", maHDD);
        model.addAttribute("chiSoTruoc", chiSoTruoc);
        model.addAttribute("chiSoSau", chiSoSau);
        model.addAttribute("heSoTienDien", heSoTienDien);
        model.addAttribute("tienNo", tienNo);
        model.addAttribute("maPhong", maPhong);
        model.addAttribute("ngayTao", ngayTao);
        model.addAttribute("ngaySuaDoi", ngaySuaDoi);
        model.addAttribute("nguoiSuaDoiCuoi", nguoiSuaDoiCuoi);

        List<HoaDonDien> searchedHoaDonDien = hoaDonDienService.timKiemTheoBang(maHDD, chiSoTruoc, chiSoSau, heSoTienDien, tienNo, maPhong, ngayTao, ngaySuaDoi, nguoiSuaDoiCuoi);
        model.addAttribute("listHDD", searchedHoaDonDien);
        return "HoaDonDien/QuanLyHoaDonDien";
    }
}
