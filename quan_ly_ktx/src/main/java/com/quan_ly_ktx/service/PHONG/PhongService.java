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
    public List<Phong> timKiemTheoBang(String column, String searchValue);
}
