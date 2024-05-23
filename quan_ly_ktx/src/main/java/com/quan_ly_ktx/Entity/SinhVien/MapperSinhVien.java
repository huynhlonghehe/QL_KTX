package com.quan_ly_ktx.Entity.SinhVien;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperSinhVien implements RowMapper<SinhVien>{
	@Override
	public SinhVien mapRow(ResultSet rs, int rowNum) throws SQLException {
		SinhVien sinhVien = new SinhVien();
		sinhVien.setMaSV(rs.getString("MASV"));
		sinhVien.setHo(rs.getString("HO"));
		sinhVien.setTen(rs.getString("TEN"));
		sinhVien.setNgaySinh(rs.getString("NGAYSINH"));
		sinhVien.setDiaChi(rs.getString("DIACHI"));
		sinhVien.setSdt(rs.getString("SDT"));
		sinhVien.setGioiTinh(rs.getString("GIOITINH"));
		sinhVien.setLop(rs.getString("LOP"));
		sinhVien.setUsername(rs.getString("USERNAME"));
		sinhVien.setNgayTao(rs.getString("NGAYTAO"));
		sinhVien.setNgaySuaDoi(rs.getString("NGAYSUADOI"));
		sinhVien.setNguoiSuaDoiCuoi(rs.getString("NGUOISUADOICUOI"));
        return sinhVien;
    }
}
