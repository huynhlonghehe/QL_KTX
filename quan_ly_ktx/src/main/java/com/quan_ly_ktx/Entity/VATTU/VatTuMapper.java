package com.quan_ly_ktx.Entity.VATTU;

import com.quan_ly_ktx.Entity.VATTU.VatTu;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VatTuMapper implements RowMapper<VatTu> {

    @Override
    public VatTu mapRow(ResultSet rs, int rowNum) throws SQLException {
        VatTu vatTu = new VatTu();
        vatTu.setMaVT(rs.getString("MAVT"));
        vatTu.setTenVT(rs.getString("TENVT"));
        vatTu.setGiaTien(rs.getDouble("GIATIEN"));
        vatTu.setNgayTao(rs.getDate("NGAYTAO"));
        vatTu.setNgaySuaDoi(rs.getDate("NGAYSUADOI"));
        vatTu.setNguoiSuaDoiCuoi(rs.getString("NGUOISUADOICUOI"));
        return vatTu;
    }
}
