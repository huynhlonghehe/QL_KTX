package com.quan_ly_ktx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.quan_ly_ktx.DAO.VT_PhongDAO;
import com.quan_ly_ktx.Entity.PHONG.Phong;
import com.quan_ly_ktx.Entity.VATTU.VatTu;
import com.quan_ly_ktx.service.VATTU.VatTuService;
import com.quan_ly_ktx.service.VT_PHONG.VT_PHONGService;

@Controller
@RequestMapping("/vattu")
public class VatTuController {
    @Autowired
    VatTuService vatTuService;
    
    @Autowired
    private VT_PHONGService vtPhongService;

/*    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showVatTuList(Model model) {
        List<VatTu> listVatTu = vatTuService.getAllVatTu();
        System.out.println("Danh sách VatTu:");
        for (VatTu vatTu : listVatTu) {
            System.out.println(vatTu); // In ra thông tin của mỗi đối tượng VatTu trong danh sách
        } 	
        model.addAttribute("listVatTu", listVatTu);
        return "VatTu/QuanLyVatTu"; 
    }*/
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showVatTuList(Model model) {
    	
        List<VatTu> listVatTu = vatTuService.getAllVatTu();
        model.addAttribute("listVatTu", listVatTu);
        return "VatTu/QuanLyVatTu"; 
    }

    @RequestMapping(value = "/update-vat-tu", method = RequestMethod.POST)
    public String updateVatTu(@ModelAttribute("vatTu") VatTu vatTu, RedirectAttributes redirectAttributes) {
    	redirectAttributes.addFlashAttribute("successMessage", "Sửa thành công !");
        vatTuService.updateVatTu(vatTu);
        return "redirect:/vattu/list";
    }

    @RequestMapping(value = "/delete-vat-tu/{maVT}", method = RequestMethod.GET)
    public String deleteVatTu(@PathVariable("maVT") String maVT, RedirectAttributes redirectAttributes) {
    	
    	if(vtPhongService.existsReferencesToVatTu(maVT)) {
    		redirectAttributes.addFlashAttribute("errorMessage", "Vật tư đang được sử dụng");
            return "redirect:/vattu/list";
    	}
    	else {
        	redirectAttributes.addFlashAttribute("successMessage", "Xóa thành công !");
            vatTuService.deleteVatTu(maVT);
            return "redirect:/vattu/list";
    	}

    }
     
    //@ResponseBody
    @RequestMapping(value = "/add-vat-tu", method = RequestMethod.POST)
    public String addVatTu(@ModelAttribute("vatTu") VatTu vatTu, RedirectAttributes redirectAttributes) {
	        String maVT = vatTu.getMaKhu() + vatTu.getMaSo();
	        vatTu.setMaVT(maVT);
    		if(vatTuService.existsByMaVT(vatTu.getMaVT())) {
    			redirectAttributes.addFlashAttribute("errorMessage", "Mã vật tư tồn tại !");
    			return "redirect:/vattu/list";
    		}
    		else {
        		redirectAttributes.addFlashAttribute("successMessage", "Thêm thành công !");
                vatTuService.addVatTu(vatTu);
                return "redirect:/vattu/list";
    		}
        
    }

    @Autowired
    private VT_PhongDAO vtPhongDAO;
    
    @ResponseBody
    @RequestMapping(value = "/check-foreign-key/{maVT}", method= RequestMethod.GET)
    public String checkForeignKey(@PathVariable String maVT) {
    	System.out.println("CHECK khoa ngoai: "+vtPhongDAO.existsReferencesToVatTu(maVT));
        if (vtPhongDAO.existsReferencesToVatTu(maVT)) {
            return "ERROR";
        } else {
            return "OK";
        }
    }
    
	@RequestMapping(value = "/list/{column}/sort/{mode}", method = RequestMethod.GET)
	public String sortVatTu(Model model, @PathVariable("column") String column, @PathVariable("mode") String mode) {
	    List<VatTu> sortedVatTu = vatTuService.sortVatTuByColumn(column, mode);
	    System.out.println("Danh sách sau khi sort: " +sortedVatTu);
	    model.addAttribute("listVatTu", sortedVatTu);
	    return "VatTu/QuanLyVatTu";
	}
	
	@RequestMapping(value = "/delete-selected", method = RequestMethod.POST)
    public String deleteSelected(@RequestParam("selectedItems") List<String> selectedItems, RedirectAttributes redirectAttributes) {
        try {
            
            redirectAttributes.addFlashAttribute("successMessage", "Xóa thành công các phòng đã lựa chọn.");
            vatTuService.deleteVatTuByIds(selectedItems);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi xóa các phòng(Phòng có thể đang được sử dụng).");
        }
        return "redirect:/vattu/list";
    }
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public String timKiemTheoBang(Model model,
	                             @RequestParam(name = "maVT", required = false) String maVT,
	                             @RequestParam(name = "tenVT", required = false) String tenVT,
	                             @RequestParam(name = "giaTien", required = false) String giaTien,
	                             @RequestParam(name = "ngayTao", required = false) String ngayTao,
	                             @RequestParam(name = "ngaySuaDoi", required = false) String ngaySuaDoi,
	                             @RequestParam(name = "nguoiSuaDoi", required = false) String nguoiSuaDoi) {

	    model.addAttribute("maVT", maVT);
	    model.addAttribute("tenVT", tenVT);
	    model.addAttribute("giaTien", giaTien);
	    model.addAttribute("ngayTao", ngayTao);
	    model.addAttribute("ngaySuaDoi", ngaySuaDoi);
	    model.addAttribute("nguoiSuaDoi", nguoiSuaDoi);

	    List<VatTu> searchedVatTu = vatTuService.timKiemTheoBang(maVT, tenVT, giaTien, ngayTao, ngaySuaDoi, nguoiSuaDoi);
	    model.addAttribute("listVatTu", searchedVatTu);
	    return "VatTu/QuanLyVatTu";
	}


}
