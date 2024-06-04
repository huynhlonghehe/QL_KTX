package com.quan_ly_ktx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/*import com.quan_ly_ktx.Entity.PHONG.Phong;*/
import com.quan_ly_ktx.Entity.VATTU.VT_PHONG;
/*import com.quan_ly_ktx.Entity.VATTU.VatTu;*/
import com.quan_ly_ktx.service.VT_PHONG.VT_PHONGService;
import com.quan_ly_ktx.service.PHONG.PhongService;
import com.quan_ly_ktx.service.VATTU.VatTuService;

@Controller
@RequestMapping("/vtphong")
public class VT_PHONGController {

    @Autowired
    private VT_PHONGService vtPhongService;
    
    @Autowired
    private PhongService phongService;

    @Autowired
    private VatTuService vatTuService;

    @ResponseBody
    @GetMapping("/check-references/{maVT}")
    public boolean checkReferencesToVatTu(@PathVariable("maVT") String maVT) {
        return vtPhongService.existsReferencesToVatTu(maVT);
    }

    @ResponseBody
    @GetMapping("/check-references-phong/{maPhong}")
    public boolean checkReferencesToPhong(@PathVariable("maPhong") String maPhong) {
        return vtPhongService.existsReferencesToPhong(maPhong);
    }

    @GetMapping("/list")
    public String showVTPhongList(Model model) {
        List<VT_PHONG> listVTPhong = vtPhongService.getAllVTPHONG();
        List<String> listPhong = phongService.getAllMaPhong();
        List<String> listVatTu = vatTuService.getAllMaVT();
/*        for (int i = 0; i < listPhong.size(); i++) {
            System.out.println(listPhong.get(i));
        }*/
        model.addAttribute("listVTPhong", listVTPhong);
        model.addAttribute("listPhong", listPhong);
        model.addAttribute("listVatTu", listVatTu);
        return "VT_Phong/VT_PHONG";
    }

/*    @GetMapping("/edit-vt-phong/{maVTPhong}")
    public String showEditVTPhongForm(@PathVariable("maVTPhong") String maVTPhong) {
        VT_PHONG vtPhong = vtPhongService.getVTPhongById(maVTPhong);
        model.addAttribute("vtPhong", vtPhong);
        return "VT_Phong/editVTPhong";
    }*/

    @PostMapping("/update-vt-phong")
    public String updateVTPhong(@ModelAttribute("vtPhong") VT_PHONG vtPhong, RedirectAttributes redirectAttributes) {
    	redirectAttributes.addFlashAttribute("successMessage", "Sửa thành công !");
        vtPhongService.updateVTPhong(vtPhong);
        return "redirect:/vtphong/list";
    }

    @GetMapping("/delete-vt-phong/{maVTPhong}")
    public String deleteVTPhong(@PathVariable("maVTPhong") String maVTPhong, RedirectAttributes redirectAttributes) {
    	redirectAttributes.addFlashAttribute("successMessage", "Sửa thành công !");
        vtPhongService.deleteVTPhong(maVTPhong);
        return "redirect:/vtphong/list";
    }

    @ResponseBody
    @GetMapping("/invalidInput/{maVTPhong}")
    public String checkExistMATV(@PathVariable("maVTPhong") String maVTPhong) {
        return vtPhongService.existsByMaVTPhong(maVTPhong) ? "ERROR" : "OK";
    }

    @PostMapping("/add-vt-phong")
    public String addVTPhong(@ModelAttribute("vtPhong") VT_PHONG vtPhong, RedirectAttributes redirectAttributes) {
        if (vtPhongService.existsByMaPhongAndMaVT(vtPhong.getMaPhong(), vtPhong.getMaVT())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Cặp mã phòng và mã vật tư đã tồn tại!");
            return "redirect:/vtphong/list";
        } else {
            vtPhongService.addVTPhong(vtPhong);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm thành công!");
            return "redirect:/vtphong/list";
        }
    }

    @GetMapping("/list/{column}/sort/{mode}")
    public String sortVTPhong(Model model, @PathVariable("column") String column, @PathVariable("mode") String mode) {
        List<VT_PHONG> sortedVTPhong = vtPhongService.sortVTPhongByColumn(column, mode);
        model.addAttribute("listVTPhong", sortedVTPhong);
        return "VT_Phong/VT_PHONG";
    }
    
    
	@RequestMapping(value = "/delete-selected", method = RequestMethod.POST)
    public String deleteSelected(@RequestParam("selectedItems") List<String> selectedItems, RedirectAttributes redirectAttributes) {
        try {
            vtPhongService.deletePhongByIds(selectedItems);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa thành công các phòng đã lựa chọn.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi xóa các phòng(Phòng có thể đang được sử dụng).");
        }
        return "redirect:/vtphong/list";
    }
    
    @GetMapping("/find")
    public String timKiemTheoBang(Model model, 
                                  @RequestParam(name = "maVTPhong", required = false) String maVTPhong,
                                  @RequestParam(name = "maVT", required = false) String maVT,
                                  @RequestParam(name = "maPhong", required = false) String maPhong,
                                  @RequestParam(name = "soLuong", required = false) String soLuong,
                                  @RequestParam(name = "ngayCap", required = false) String ngayCap,
                                  @RequestParam(name = "ngaySuaDoi", required = false) String ngaySuaDoi,
                                  @RequestParam(name = "tinhTrang", required = false) String tinhTrang) {

        model.addAttribute("maVTPhong", maVTPhong);
        model.addAttribute("maVT", maVT);
        model.addAttribute("maPhong", maPhong);
        model.addAttribute("soLuong", soLuong);
        model.addAttribute("ngayCap", ngayCap);
        model.addAttribute("ngaySuaDoi", ngaySuaDoi);
        model.addAttribute("tinhTrang", tinhTrang);

        List<VT_PHONG> searchedVTPhong = vtPhongService.timKiemTheoBang(maPhong, maVT, ngayCap, ngaySuaDoi, soLuong, tinhTrang);
        for (int i = 0; i < searchedVTPhong.size(); i++) {
            System.out.println(searchedVTPhong.get(i));
        }
        model.addAttribute("listVTPhong", searchedVTPhong);
        return "VT_Phong/VT_PHONG";
    }

}
