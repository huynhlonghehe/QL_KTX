package com.quan_ly_ktx.service.VT_PHONG;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quan_ly_ktx.DAO.VT_PhongDAO;
import com.quan_ly_ktx.Entity.VATTU.VT_PHONG;

@Service
public class VT_PHONGServiceImpl implements VT_PHONGService {

    @Autowired
    private VT_PhongDAO vtPhongDAO;

    @Override
    public boolean existsReferencesToVatTu(String maVT) {
        return vtPhongDAO.existsReferencesToVatTu(maVT);
    }

	@Override
	public List<VT_PHONG> getAllVTPHONG() {
		// TODO Auto-generated method stub
		return vtPhongDAO.getAllVTPHONG();
	}

    // Các phương thức service khác
}
