package com.quan_ly_ktx.Entity.VATTU;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class VT_PHONGMapper implements RowMapper<VT_PHONG> {

    @Override
    public VT_PHONG mapRow(ResultSet rs, int rowNum) throws SQLException {
        VT_PHONG vtPhong = new VT_PHONG();
        vtPhong.setMaVTPhong(rs.getString("MAVTPHONG"));
        vtPhong.setMaVT(rs.getString("MAVT"));
        vtPhong.setMaPhong(rs.getString("MAPHONG"));
        vtPhong.setNgayCap(rs.getString("NGAYCAP"));
        vtPhong.setNgaySuaDoi(rs.getString("NGAYSUADOI"));
        vtPhong.setSoLuong(rs.getInt("SOLUONG"));
        vtPhong.setTinhTrang(rs.getString("TINHTRANG"));
        vtPhong.setTienNo(rs.getBoolean("TIENNO"));
        return vtPhong;
    }
}
