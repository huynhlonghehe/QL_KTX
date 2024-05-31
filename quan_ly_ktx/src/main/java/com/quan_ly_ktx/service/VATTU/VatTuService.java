package com.quan_ly_ktx.service.VATTU;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quan_ly_ktx.Entity.VATTU.VatTu;

@Service
public interface VatTuService {
    List<VatTu> getAllVatTu();
    VatTu getVatTuById(String maVT);
    void updateVatTu(VatTu vatTu);
    public void deleteVatTu(String maVT);
    void addVatTu(VatTu vatTu);
    public boolean existsByMaVT(String maVT);
    List<VatTu> sortVatTuByColumn(String column, String sortDirection);
    public List<String> getDuocQuanLyBoi(String maVT);
    public List<String> getQLVTLIST();
}
