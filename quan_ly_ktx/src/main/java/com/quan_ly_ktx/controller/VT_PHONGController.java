package com.quan_ly_ktx.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quan_ly_ktx.service.VT_PHONGService;

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

    // Các endpoint khác cho xử lý VT_PHONG
}
