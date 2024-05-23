package com.quan_ly_ktx.Entity.SinhVien;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperQuanLy_SinhVien implements RowMapper<QuanLy_SinhVien>{
	@Override
	public QuanLy_SinhVien mapRow(ResultSet rs, int rowNum) throws SQLException {
		QuanLy_SinhVien quanLy_SinhVien = new QuanLy_SinhVien();
		quanLy_SinhVien.setMaQL_SV(rs.getInt("MAQL_SV"));
		quanLy_SinhVien.setMaQL(rs.getString("MAQL"));
		quanLy_SinhVien.setMaSV(rs.getString("MASV"));
        return quanLy_SinhVien;
    }
}
