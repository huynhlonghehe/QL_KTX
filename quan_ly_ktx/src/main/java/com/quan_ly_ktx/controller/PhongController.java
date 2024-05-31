package com.quan_ly_ktx.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.quan_ly_ktx.DAO.VT_PhongDAO;
import com.quan_ly_ktx.Entity.PHONG.Phong;
import com.quan_ly_ktx.Entity.PHONG.OptionSelect;
import com.quan_ly_ktx.service.PHONG.PhongService;

@Controller
@RequestMapping("/phong")
public class PhongController {

    @Autowired
    PhongService phongService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showPhongList(Model model) {
	    List<OptionSelect> danhSachCacLuaChon = taoDanhSachCacLuaChon();
        List<Phong> listPhong = phongService.getAllPhongs();
	    model.addAttribute("danhSachCacLuaChon", danhSachCacLuaChon);
        model.addAttribute("listPhong", listPhong);
        return "Phong/QuanLyPhong"; // Thay đổi tên view tương ứng
    }

/*    @RequestMapping(value = "/edit-phong/{maPhong}", method = RequestMethod.GET)
    public String showEditPhongForm(@PathVariable("maPhong") String maPhong, Model model) {
        Phong phong = phongService.getPhongById(maPhong);
        model.addAttribute("phong", phong);
        return "Phong/editPhong";
    }
*/
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
    
    @ResponseBody
    @RequestMapping(value = "/check-foreign-key/{maPhong}", method= RequestMethod.GET)
    public String checkForeignKey(@PathVariable String maPhong) {
    	System.out.println("CHECK khoa ngoai: "+vtPhongDAO.existsReferencesToPhong(maPhong));
        if (vtPhongDAO.existsReferencesToPhong(maPhong)) {
            return "ERROR";
        } else {
            return "OK";
        }
    }
    
    @RequestMapping(value = "/delete-phong/{maPhong}", method = RequestMethod.GET)
    public String deletePhong(@PathVariable("maPhong") String maPhong, RedirectAttributes redirectAttributes) {
        if (vtPhongDAO.existsReferencesToPhong(maPhong)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Phòng đang được sử dụng");
            return "redirect:/phong/list";
        } else {
            phongService.deletePhong(maPhong);
            return "redirect:/phong/list";
        }
    }



    @RequestMapping(value = "/invalidInput/{maPhong}", method = RequestMethod.GET)
    @ResponseBody
    public String checkExistMaPhong(@PathVariable("maPhong") String maPhong, Model model) {
        if(phongService.existsByMaPhong(maPhong)) {
            return "ERROR";
        } else {
            return "OK";
        }
    }
    
	@RequestMapping(value = "/list/{column}/sort/{mode}", method = RequestMethod.GET)
	public String sortPhong(Model model, @PathVariable("column") String column, @PathVariable("mode") String mode) {
	    List<Phong> sortedPhong = phongService.sortPhongByColumn(column, mode);
	    System.out.println("Danh sách sau khi sort: " +sortedPhong);
	    model.addAttribute("listPhong", sortedPhong);
	    return "Phong/QuanLyPhong";
	}
	
	public List<OptionSelect> taoDanhSachCacLuaChon() {
	    List<OptionSelect> danhSachCacLuaChon = new ArrayList<>();
	    danhSachCacLuaChon.add(new OptionSelect("MAPHONG", "Mã phòng"));
	    danhSachCacLuaChon.add(new OptionSelect("TINHTRANG", "Tình trạng"));
	    danhSachCacLuaChon.add(new OptionSelect("SUCCHUA", "Sức chứa"));
	    danhSachCacLuaChon.add(new OptionSelect("KHUKTX", "Khu KTX"));
	    danhSachCacLuaChon.add(new OptionSelect("SOLUONG", "Số lượng sinh viên đang cư trú"));
	    danhSachCacLuaChon.add(new OptionSelect("MALOAIPHONG", "Mã loại phòng"));
	    // Thêm các giá trị cho danh sách các lựa chọn
	    return danhSachCacLuaChon;
	}

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public String timKiemTheoBang(Model model, 
	                              @RequestParam(name = "maPhong", required = false) String maPhong,
	                              @RequestParam(name = "tinhTrang", required = false) String tinhTrang,
	                              @RequestParam(name = "sucChua", required = false) String sucChua,
	                              @RequestParam(name = "khuKTX", required = false) String khuKTX,
	                              @RequestParam(name = "soLuong", required = false) String soLuong,
	                              @RequestParam(name = "maLoaiPhong", required = false) String maLoaiPhong) {
	    List<OptionSelect> danhSachCacLuaChon = taoDanhSachCacLuaChon();
	    model.addAttribute("danhSachCacLuaChon", danhSachCacLuaChon);

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
