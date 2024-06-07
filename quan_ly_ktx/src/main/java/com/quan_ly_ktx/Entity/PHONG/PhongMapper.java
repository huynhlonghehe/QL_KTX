package com.quan_ly_ktx.Entity.PHONG;

import com.quan_ly_ktx.Entity.PHONG.Phong;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhongMapper implements org.springframework.jdbc.core.RowMapper<Phong> {

    @Override
    public Phong mapRow(ResultSet resultSet, int i) throws SQLException {
        Phong phong = new Phong();
        phong.setMaPhong(resultSet.getString("MAPHONG"));
        phong.setTinhTrang(resultSet.getString("TINHTRANG"));
        phong.setSucChua(resultSet.getInt("SUCCHUA"));
        phong.setKhuKTX(resultSet.getString("KHUKTX"));
        phong.setSoLuong(resultSet.getInt("SOLUONG"));
        phong.setMaLoaiPhong(resultSet.getString("MALOAIPHONG"));
        phong.setNgayTao(resultSet.getDate("NGAYTAO"));
        phong.setNgaySuaDoi(resultSet.getDate("NGAYSUADOI"));
        phong.setNguoiSuaDoiCuoi(resultSet.getString("NGUOISUADOICUOI"));
        return phong;
    }
}
