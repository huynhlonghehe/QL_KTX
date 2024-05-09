package com.quan_ly_ktx.DTO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperSinhVienDetailsDTO implements RowMapper<SinhVienDetailsDTO> {
    @Override
    public SinhVienDetailsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        SinhVienDetailsDTO dto = new SinhVienDetailsDTO();
        dto.setMaSV(rs.getString("MASV"));
        dto.setHo(rs.getString("HO"));
        dto.setTen(rs.getString("TEN"));
        dto.setNgaySinh(rs.getString("NGAYSINH"));
        dto.setDiaChi(rs.getString("DIACHI"));
        dto.setSdt(rs.getString("SDT"));
        dto.setGioiTinh(rs.getString("GIOITINH"));
        dto.setLop(rs.getString("LOP"));
        dto.setMaSinhVienViPham(rs.getInt("MASVVP"));
        dto.setMaQuyTac(rs.getInt("MAQUYTAC"));
        dto.setThoiGianViPham(rs.getString("THOIGIANVIPHAM"));
        dto.setTenQuyTac(rs.getString("TENQUYTAC"));
        dto.setMucDoViPham(rs.getInt("MUCDOVIPHAM"));
        return dto;
    }
}

