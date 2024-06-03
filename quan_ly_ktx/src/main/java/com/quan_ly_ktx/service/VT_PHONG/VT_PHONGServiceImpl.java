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
    public boolean existsReferencesToPhong(String maPhong) {
        return vtPhongDAO.existsReferencesToPhong(maPhong);
    }

    @Override
    public List<VT_PHONG> getAllVTPHONG() {
        return vtPhongDAO.getAllVTPHONG();
    }

    @Override
    public void addVTPhong(VT_PHONG vtPhong) {
        vtPhongDAO.addVTPhong(vtPhong);
    }

    @Override
    public VT_PHONG getVTPhongById(String maVTPhong) {
        return vtPhongDAO.getVTPhongById(maVTPhong);
    }

    @Override
    public void updateVTPhong(VT_PHONG vtPhong) {
        vtPhongDAO.updateVTPhong(vtPhong);
    }

    @Override
    public void deleteVTPhong(String maVTPhong) {
        vtPhongDAO.deleteVTPhong(maVTPhong);
    }

    @Override
    public boolean existsByMaVTPhong(String maVTPhong) {
        return vtPhongDAO.existsByMaVTPhong(maVTPhong);
    }

    @Override
    public List<VT_PHONG> sortVTPhongByColumn(String column, String mode) {
        // Implement sorting logic based on column and mode
        return vtPhongDAO.sortVTPhongByColumn(column, mode);
    }

    @Override
    public List<VT_PHONG> timKiemTheoBang(String maPhong, String maVT, String ngayCap, String ngaySuaDoi, String soLuong,String tinhTrang) {
        // Implement searching logic
        return vtPhongDAO.timKiemVTPhong( maPhong, maVT, ngayCap, ngaySuaDoi,  soLuong, tinhTrang);
    }
    @Override
    public void xoaTheoBang(String maPhong, String maVT, String ngayCap, String ngaySuaDoi, String soLuong, String tinhTrang) {
    	vtPhongDAO.xoaTheoBang(maPhong, maVT, ngayCap, ngaySuaDoi, soLuong, tinhTrang);
    }
    
    
}
