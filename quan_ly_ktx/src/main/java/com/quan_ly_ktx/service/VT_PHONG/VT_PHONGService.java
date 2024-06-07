package com.quan_ly_ktx.service.VT_PHONG;

import com.quan_ly_ktx.Entity.VATTU.VT_PHONG;

import java.util.List;

public interface VT_PHONGService {
    List<VT_PHONG> getAllVTPHONG();
    VT_PHONG getVTPhongById(String maVTPhong);
    void updateVTPhong(VT_PHONG vtPhong);
    void deleteVTPhong(String maVTPhong);
    void addVTPhong(VT_PHONG vtPhong);
    boolean existsByMaVTPhong(String maVTPhong);
    boolean existsReferencesToVatTu(String maVT);
    boolean existsReferencesToPhong(String maPhong);
    boolean existsByMaPhongAndMaVT(String maPhong, String maVT);
    // New methods
    List<VT_PHONG> sortVTPhongByColumn(String column, String mode);
	List<VT_PHONG> timKiemTheoBang(String maPhong, String maVT, String ngayCap, String ngaySuaDoi, String soLuong,String tinhTrang);
	void deletePhongByIds(List<String> ids);
	void xoaTheoBang(String maPhong, String maVT, String ngayCap, String ngaySuaDoi, String soLuong, String tinhTrang);
}
