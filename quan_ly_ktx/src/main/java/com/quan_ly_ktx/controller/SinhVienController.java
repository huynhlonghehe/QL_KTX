package com.quan_ly_ktx.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sinhvien")
public class SinhVienController {
	
	@RequestMapping("/index")
    public String index() {
        return "SinhVien/index";
    }
	
	@RequestMapping("/thong-bao")
    public String thongBao() {
        return "SinhVien/thongBao";
    }
	
	@RequestMapping("phong-tro")
    public String phongTro() {
        return "SinhVien/phongTro";
    }
	
	@RequestMapping("/csvc")
    public String CSVC() {
        return "SinhVien/CSVC";
    }
	
	@RequestMapping("/tien-dien")
    public String tienDien() {
        return "SinhVien/tienDien";
    }
	
	@RequestMapping("/gui-yeu-cau")
    public String guiYeucau() {
        return "SinhVien/guiYeuCau";
    }
	
	@RequestMapping("/yeu-cau-da-gui")
    public String yeuCauDaGui() {
        return "SinhVien/yeuCauDaGui";
    }
	
	@RequestMapping("/thong-tin-can-vu")
    public String thongTinCanVu() {
        return "SinhVien/thongTinCanVu";
    }
}
