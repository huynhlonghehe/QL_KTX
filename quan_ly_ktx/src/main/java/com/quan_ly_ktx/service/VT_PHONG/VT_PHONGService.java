package com.quan_ly_ktx.service.VT_PHONG;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quan_ly_ktx.Entity.VATTU.VT_PHONG;

@Service
public interface VT_PHONGService {
    
    boolean existsReferencesToVatTu(String maVT);
    List<VT_PHONG> getAllVTPHONG();
    // Các phương thức service khác cho tương tác với bảng VT_PHONG
}
