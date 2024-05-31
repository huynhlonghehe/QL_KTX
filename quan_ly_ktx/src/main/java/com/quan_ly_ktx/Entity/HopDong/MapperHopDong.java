package com.quan_ly_ktx.Entity.HopDong;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class MapperHopDong implements RowMapper<HopDong>{
	@Override
	public HopDong mapRow(ResultSet rs, int rowNum) throws SQLException {
		HopDong hopDong = new HopDong();
		hopDong.setMaHD(rs.getString("MAHD"));
		hopDong.setNgayTao(rs.getString("NGAYTAO"));
		hopDong.setNgayHetHan(rs.getString("NGAYHETHAN"));
		hopDong.setSoTien(rs.getLong("SOTIEN"));
		hopDong.setNamHoc(rs.getString("NAMHOC"));
		hopDong.setHocKy(rs.getString("HOCKY"));
		hopDong.setMaPhong(rs.getString("MAPHONG"));
		hopDong.setMaSV(rs.getString("MASV"));
		hopDong.setNgaySuaDoi(rs.getString("NGAYSUADOI"));
		hopDong.setNguoiSuaDoiCuoi(rs.getString("NGUOISUADOICUOI"));
        return hopDong;
    }
}
