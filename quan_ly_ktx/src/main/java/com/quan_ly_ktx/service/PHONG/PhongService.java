package com.quan_ly_ktx.service.PHONG;

import java.util.List;
import com.quan_ly_ktx.Entity.PHONG.Phong;


public interface PhongService {
    List<Phong> getAllPhongs();
    Phong getPhongById(String maPhong);
    void updatePhong(Phong phong);
    void deletePhong(String maPhong);
    boolean existsByMaPhong(String maPhong);
    void addPhong(Phong phong);
    public List<Phong> sortPhongByColumn(String column, String sortDirection);
    List<Phong> timKiemTheoBang(String maPhong, String tinhTrang, String sucChua, String khuKTX, String soLuong, String maLoaiPhong);
}