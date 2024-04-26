package com.quan_ly_ktx.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quan_ly_ktx.DAO.VT_PhongDAO;

@Service
public class VT_PHONGServiceImpl implements VT_PHONGService {

    @Autowired
    private VT_PhongDAO vtPhongDAO;

    @Override
    public boolean existsReferencesToVatTu(String maVT) {
        return vtPhongDAO.existsReferencesToVatTu(maVT);
    }

    // Các phương thức service khác
}
