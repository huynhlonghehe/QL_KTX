package com.quan_ly_ktx.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String addVatTu(@ModelAttribute("phong") Phong phong) {
            String maPhong = phong.getMaKhu() + phong.getMaSo();
            phong.setMaPhong(maPhong);
            System.out.println("Ma phong la: " + maPhong);
            phongService.addPhong(phong);
        // Trả về trang hiện tại (trang quản lý vật tư)
        return "redirect:/phong/list";
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
    public String deletePhong(@PathVariable("maPhong") String maPhong) {
        phongService.deletePhong(maPhong);
        return "redirect:/phong/list";
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

	@RequestMapping(value = "/list/{column}/find/{searchValue}", method = RequestMethod.GET)
	public String timKiemTheoBang(Model model, @PathVariable("column") String column, @PathVariable("searchValue") String searchValue, HttpSession session) {
	    List<OptionSelect> danhSachCacLuaChon = taoDanhSachCacLuaChon();
	    // Lưu biến vào session
	    model.addAttribute("danhSachCacLuaChon", danhSachCacLuaChon);
	    model.addAttribute("tenBangDuocChon", column); // Lưu giá trị của dropdown
	    model.addAttribute("giaTriTimKiem", searchValue);

	    // Lưu giá trị của dropdown vào session
	    session.setAttribute("tenBangDuocChon", column);
	    session.setAttribute("giaTriTimKiem", searchValue);
	    List<Phong> searchedPhong = phongService.timKiemTheoBang(column, searchValue);
	    //System.out.println("Danh sách sau khi sort: " +searchedPhong);   
	    model.addAttribute("listPhong", searchedPhong);
	    return "Phong/QuanLyPhong";
	}


}
