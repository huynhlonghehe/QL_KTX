package com.quan_ly_ktx.Entity.HoaDonDien;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class HoaDonDienMapper implements RowMapper<HoaDonDien> {

    @Override
    public HoaDonDien mapRow(ResultSet rs, int rowNum) throws SQLException {
        HoaDonDien hoaDonDien = new HoaDonDien();
        hoaDonDien.setMaHDD(rs.getString("MAHDD"));
        hoaDonDien.setChiSoTruoc(rs.getString("CHISOTRUOC"));
        hoaDonDien.setChiSoSau(rs.getString("CHISOSAU"));
        hoaDonDien.setHeSoTienDien(rs.getFloat("HESOTIENDIEN"));
        hoaDonDien.setTienNo(rs.getBoolean("TIENNO"));
        hoaDonDien.setMaPhong(rs.getString("MAPHONG"));
        hoaDonDien.setNgayTao(rs.getString("NGAYTAO"));
        hoaDonDien.setNgaySuaDoi(rs.getString("NGAYSUADOI"));
        hoaDonDien.setNguoiSuaDoiCuoi(rs.getString("NGUOISUADOICUOI"));
        return hoaDonDien;
    }
}
