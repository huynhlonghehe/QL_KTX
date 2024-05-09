package com.quan_ly_ktx.Entity.SinhVien;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperQuyTac implements RowMapper<QuyTac>{

	@Override
	public QuyTac mapRow(ResultSet rs, int rowNum) throws SQLException {
		QuyTac quyTac = new QuyTac();
		quyTac.setMaQuyTac(rs.getInt("MAQUYTAC"));
		quyTac.setTenQuyTac(rs.getString("TENQUYTAC"));
		quyTac.setMucDoViPham(rs.getInt("MUCDOVIPHAM"));
        return quyTac;
    }
}
