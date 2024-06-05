package com.quan_ly_ktx.service.HOADONDIEN;

import java.util.List;

import com.quan_ly_ktx.Entity.HoaDonDien.HoaDonDien;

public interface HoaDonDienService {

    List<HoaDonDien> getAllHoaDonDien();

    HoaDonDien getHoaDonDienById(String maHDD);

    void addHoaDonDien(HoaDonDien hoaDonDien);

    int updateHoaDonDien(HoaDonDien hoaDonDien);

    int deleteHoaDonDien(String maHDD);

    List<HoaDonDien> sortHDDByColumn(String column, String sortDirection);

    List<HoaDonDien> timKiemTheoBang(String maHDD, String chiSoTruoc, String chiSoSau, String heSoTienDien, String tienNo, String maPhong, String ngayTao, String ngaySuaDoi, String nguoiSuaDoiCuoi);
}
