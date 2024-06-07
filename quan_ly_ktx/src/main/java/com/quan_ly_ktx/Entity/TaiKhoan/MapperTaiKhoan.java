package com.quan_ly_ktx.Entity.TaiKhoan;

<<<<<<< HEAD
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
=======

import java.sql.ResultSet;

>>>>>>> 17e1134b9aa9bdc953f04b1a95e57e6ab57c8d50
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
