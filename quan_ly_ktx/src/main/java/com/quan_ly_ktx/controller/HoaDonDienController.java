package com.quan_ly_ktx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.quan_ly_ktx.Entity.HoaDonDien.HoaDonDien;
import com.quan_ly_ktx.service.HOADONDIEN.HoaDonDienService;

@Controller
@RequestMapping("/hoa-don-dien")
public class HoaDonDienController {

    @Autowired
    private HoaDonDienService hoaDonDienService;

    @GetMapping("/list")
    public String showHoaDonDienList(Model model) {
        List<HoaDonDien> listHoaDonDien = hoaDonDienService.getAllHoaDonDien();
        model.addAttribute("listHDD", listHoaDonDien);
        return "HoaDonDien/QuanLyHDD";
    }

    @PostMapping("/update-hoa-don-dien")
    public String updateHoaDonDien(@ModelAttribute("hoaDonDien") HoaDonDien hoaDonDien) {
        hoaDonDienService.updateHoaDonDien(hoaDonDien);
        return "redirect:/hoa-don-dien/list";
    }

    @PostMapping("/add-hoa-don-dien")
    public String addHoaDonDien(@ModelAttribute("hoaDonDien") HoaDonDien hoaDonDien, RedirectAttributes redirectAttributes) {
        hoaDonDienService.addHoaDonDien(hoaDonDien);
        redirectAttributes.addFlashAttribute("successMessage", "Thêm thành công");
        return "redirect:/hoa-don-dien/list";
    }

    @GetMapping("/delete-hoa-don-dien/{maHDD}")
    public String deleteHoaDonDien(@PathVariable String maHDD, RedirectAttributes redirectAttributes) {
        hoaDonDienService.deleteHoaDonDien(maHDD);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa thành công");
        return "redirect:/hoa-don-dien/list";
    }

    @GetMapping("/list/{column}/sort/{mode}")
    public String sortHoaDonDien(Model model, @PathVariable("column") String column, @PathVariable("mode") String mode) {
        List<HoaDonDien> sortedHoaDonDien = hoaDonDienService.sortHDDByColumn(column, mode);
        model.addAttribute("listHoaDonDien", sortedHoaDonDien);
        return "HoaDonDien/QuanLyHoaDonDien";
    }

    @GetMapping("/find")
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
