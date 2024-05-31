package com.quan_ly_ktx.Entity.HopDong;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class MapperQuanLy_HopDong implements RowMapper<QuanLy_HopDong>{

	
	public QuanLy_HopDong mapRow(ResultSet rs, int rowNum) throws SQLException {
		QuanLy_HopDong quanLy_HopDong = new QuanLy_HopDong();
		quanLy_HopDong.setMaQL_HD(rs.getInt("MAQL_HD"));
		quanLy_HopDong.setMaQL(rs.getString("MAQL"));
		quanLy_HopDong.setMaHD(rs.getString("MAHD"));
        return quanLy_HopDong;
	}

}
