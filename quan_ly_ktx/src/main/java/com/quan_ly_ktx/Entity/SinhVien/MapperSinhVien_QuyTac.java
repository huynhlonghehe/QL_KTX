package com.quan_ly_ktx.Entity.SinhVien;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperSinhVien_QuyTac implements RowMapper<SinhVien_QuyTac>{

	@Override
	public SinhVien_QuyTac mapRow(ResultSet rs, int rowNum) throws SQLException {
		SinhVien_QuyTac sinhVien_QuyTac = new SinhVien_QuyTac();
		sinhVien_QuyTac.setMaSVVP(rs.getInt("MASV"));
		sinhVien_QuyTac.setMaSV(rs.getString("MASV"));
		sinhVien_QuyTac.setMaQuyTac(rs.getString("MAQUYTAC"));
		sinhVien_QuyTac.setThoiGianViPham(rs.getString("THOIGIANVIPHAM"));
		sinhVien_QuyTac.setNguoiLapBienBan(rs.getString("NGUOILAPBIENBAN"));
		return sinhVien_QuyTac;
	}
}
