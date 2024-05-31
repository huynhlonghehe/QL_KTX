package com.quan_ly_ktx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.quan_ly_ktx.Entity.VATTU.VT_PHONG;
import com.quan_ly_ktx.service.VT_PHONG.VT_PHONGService;

@Controller
@RequestMapping("/vtphong")
public class VT_PHONGController {

    @Autowired
    private VT_PHONGService vtPhongService;

    @ResponseBody
    @RequestMapping(value = "/check-references/{maVT}", method = RequestMethod.GET)
    public boolean checkReferencesToVatTu(@PathVariable("maVT") String maVT) {
        return vtPhongService.existsReferencesToVatTu(maVT);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showVTPhongList(Model model) {
        List<VT_PHONG> listVTPhong = vtPhongService.getAllVTPHONG();
        model.addAttribute("listVTPhong", listVTPhong);
        return "VT_Phong/VT_PHONG"; // Ensure you have this view
    }
//
//    @RequestMapping(value = "/edit-vt-phong/{maVT}", method = RequestMethod.GET)
//    public String showEditVTPhongForm(@PathVariable("maVT") String maVT, Model model) {
//        VT_PHONG vtPhong = vtPhongService.getVTPhongById(maVT);
//        model.addAttribute("vtPhong", vtPhong);
//        return "VT_PHONG/editVTPhong"; // Ensure you have this view
//    }
//
//    @RequestMapping(value = "/update-vt-phong", method = RequestMethod.POST)
//    public String updateVTPhong(@ModelAttribute("vtPhong") VT_PHONG vtPhong) {
//        vtPhongService.updateVTPhong(vtPhong);
//        return "redirect:/vtphong/list";
//    }
//
//    @RequestMapping(value = "/delete-vt-phong/{maVT}", method = RequestMethod.GET)
//    public String deleteVTPhong(@PathVariable("maVT") String maVT) {
//        vtPhongService.deleteVTPhong(maVT);
//        return "redirect:/vtphong/list";
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/invalidInput/{maVT}", method = RequestMethod.GET)
//    public String checkExistMATV(@PathVariable("maVT") String maVT) {
//        if(vtPhongService.existsByMaVT(maVT)) {
//            return "ERROR";
//        } else {
//            return "OK";
//        }
//    }
//
//    @RequestMapping(value = "/add-vt-phong", method = RequestMethod.POST)
//    public String addVTPhong(@ModelAttribute("vtPhong") VT_PHONG vtPhong) {
//        vtPhongService.addVTPhong(vtPhong);
//        return "redirect:/vtphong/list";
//    }
//
//    @RequestMapping(value = "/list/{column}/sort/{mode}", method = RequestMethod.GET)
//    public String sortVTPhong(Model model, @PathVariable("column") String column, @PathVariable("mode") String mode) {
//        List<VT_PHONG> sortedVTPhong = vtPhongService.sortVTPhongByColumn(column, mode);
//        model.addAttribute("listVTPhong", sortedVTPhong);
//        return "VT_PHONG/QuanLyVTPhong"; // Ensure you have this view
//    }
}
