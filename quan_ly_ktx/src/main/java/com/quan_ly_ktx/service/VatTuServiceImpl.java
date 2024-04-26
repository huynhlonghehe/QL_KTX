package com.quan_ly_ktx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quan_ly_ktx.DAO.VatTuDAO;
import com.quan_ly_ktx.Entity.VatTu;

@Service
public class VatTuServiceImpl implements VatTuService {
    @Autowired
    private VatTuDAO vatTuDAO;

    @Override
    public void addVatTu(VatTu vatTu) {
        // Kiểm tra sự tồn tại của mã vật tư trước khi thêm
        if (!existsByMaVT(vatTu.getMaVT())) {
            vatTuDAO.addVatTu(vatTu);
        } else {
            // Nếu mã vật tư đã tồn tại, có thể hiển thị thông báo hoặc xử lý khác ở đây
        	
            System.out.println("Mã vật tư đã tồn tại, không thể thêm!");
        }
    }
    @Override
    public List<VatTu> getAllVatTu() {
        return vatTuDAO.getAllVatTu();
    }

    @Override
    public VatTu getVatTuById(String maVT) {
        return vatTuDAO.getVatTuById(maVT);
    }
    
    @Override
    public void updateVatTu(VatTu vatTu) {
        vatTuDAO.updateVatTu(vatTu);
    }
    @Override
    public void deleteVatTu(String maVT) {
    	vatTuDAO.deleteVatTu(maVT);
    }
    @Override
    public boolean existsByMaVT(String maVT) {
        // Gọi phương thức kiểm tra sự tồn tại của mã vật tư từ VatTuDAO
        return vatTuDAO.existsByMaVT(maVT);
    }
    @Override
    public List<VatTu> sortVatTuByColumn(String column, String sortDirection) {
		return vatTuDAO.sortVatTuByColumn(column, sortDirection);
	}
}

