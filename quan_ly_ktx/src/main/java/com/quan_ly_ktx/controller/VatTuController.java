package com.quan_ly_ktx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quan_ly_ktx.DAO.VT_PhongDAO;
import com.quan_ly_ktx.Entity.VATTU.VatTu;
import com.quan_ly_ktx.service.VatTuService;

@Controller
@RequestMapping("/vattu")
public class VatTuController {
    @Autowired
    VatTuService vatTuService;

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
        for (VatTu vatTu : listVatTu) {
            List<String> quanLy = vatTuService.getQLVTLIST();
            vatTu.setDuocQuanLyBoi(quanLy);
            System.out.println(vatTu.getDuocQuanLyBoi());
        }
        model.addAttribute("listVatTu", listVatTu);
        return "VatTu/QuanLyVatTu"; 
    }

/*    @RequestMapping(value = "/edit-vat-tu/{maVT}", method = RequestMethod.GET)
    public String showEditVatTuForm(@PathVariable("maVT") String maVT, Model model) {
        VatTu vatTu = vatTuService.getVatTuById(maVT);
        model.addAttribute("vatTu", vatTu);
        return "VatTu/editVatTu";
    }
*/
    @RequestMapping(value = "/update-vat-tu", method = RequestMethod.POST)
    public String updateVatTu(@ModelAttribute("vatTu") VatTu vatTu) {
        vatTuService.updateVatTu(vatTu);
        return "redirect:/vattu/list";
    }

    @RequestMapping(value = "/delete-vat-tu/{maVT}", method = RequestMethod.GET)
    public String deleteVatTu(@PathVariable("maVT") String maVT) {
        vatTuService.deleteVatTu(maVT);
        return "redirect:/vattu/list";
    }
     
    @ResponseBody
    @RequestMapping(value = "/invalidInput/{maVT}", method = RequestMethod.GET)
    public String checkExistMATV(@PathVariable("maVT") String maVT, Model model) {
        if(vatTuService.existsByMaVT(maVT)) {
            return "ERROR";
        } else {
            return "OK";
        }
    }
    
    //@ResponseBody
    @RequestMapping(value = "/add-vat-tu", method = RequestMethod.POST)
    public String addVatTu(@ModelAttribute("vatTu") VatTu vatTu) {
            String maVT = vatTu.getMaKhu() + vatTu.getMaSo();
            vatTu.setMaVT(maVT);
            vatTuService.addVatTu(vatTu);
        // Trả về trang hiện tại (trang quản lý vật tư)
        return "redirect:/vattu/list";
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

}
