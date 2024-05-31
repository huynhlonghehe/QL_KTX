package com.quan_ly_ktx.Entity.TaiKhoan;


import java.sql.ResultSet;

import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class MapperTaiKhoan implements RowMapper<TaiKhoan> {

    @Override
    public TaiKhoan mapRow(ResultSet rs, int rowNum) throws SQLException {
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setTenDangNhap(rs.getString("USERNAME"));
        taiKhoan.setMatKhau(rs.getString("PASSWORD"));
        if(rs.getString("TINHTRANG").equals("1")) {
        	taiKhoan.setTinhTrang("Còn hoạt động");
        }else {
        	taiKhoan.setTinhTrang("Đã ngưng hoạt động");
        }
        taiKhoan.setQuyen(rs.getString("QUYEN"));
        return taiKhoan;
    }
}
