package com.quan_ly_ktx.DAO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.quan_ly_ktx.Entity.VATTU.VT_PHONG;

@Repository
public class VT_PhongDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean existsReferencesToVatTu(String maVT) {
        String sql = "SELECT COUNT(*) FROM VT_PHONG WHERE MAVT = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, maVT);
        System.out.println("Kiem tra ton tai VATTU VTPHONG: " + count);
        return count != null && count > 0;
    }

    public boolean existsReferencesToPhong(String maPhong) {
        String sql = "SELECT COUNT(*) FROM VT_PHONG WHERE MAPHONG = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, maPhong);
        System.out.println("Kiem tra ton tai PHONG VTPHONG: " + count);
        return count != null && count > 0;
    }
    
    public boolean existsByMaPhongAndMaVT(String maPhong, String maVT) {
        String sql = "SELECT COUNT(*) FROM VT_PHONG WHERE maPhong = ? AND maVT = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{maPhong, maVT}, Integer.class);
        return count != null && count > 0;
    }
    
    public List<VT_PHONG> getAllVTPHONG() {
        String sql = "SELECT * FROM VT_PHONG ORDER BY MAPHONG ASC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(VT_PHONG.class));
    }
    
    public void addVTPhong(VT_PHONG vtPhong) {
        String sql = "INSERT INTO VT_PHONG (MAPHONG, MAVT, NGAYCAP, SOLUONG, TINHTRANG, TIENNO) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, vtPhong.getMaPhong(), vtPhong.getMaVT(), vtPhong.getNgayCap(), vtPhong.getSoLuong(), vtPhong.getTinhTrang(), vtPhong.isTienNo());
    }

    public VT_PHONG getVTPhongById(String maVTPhong) {
        String sql = "SELECT * FROM VT_PHONG WHERE MAVTPHONG = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{maVTPhong}, new BeanPropertyRowMapper<>(VT_PHONG.class));
    }

    public void updateVTPhong(VT_PHONG vtPhong) {
    	Timestamp ngayTao = new Timestamp(System.currentTimeMillis());
        String sql = "UPDATE VT_PHONG SET MAPHONG = ?, MAVT = ?, NGAYCAP = ?, SOLUONG = ?, TINHTRANG = ?, TIENNO = ?, NGAYSUADOI = ? WHERE MAVTPHONG = ?";
        jdbcTemplate.update(sql, vtPhong.getMaPhong(), vtPhong.getMaVT(), vtPhong.getNgayCap(), vtPhong.getSoLuong(), vtPhong.getTinhTrang(), vtPhong.isTienNo(), ngayTao, vtPhong.getMaVTPhong());
    }

    public void deleteVTPhong(String maVTPhong) {
        String sql = "DELETE FROM VT_PHONG WHERE MAVTPHONG = ?";
        jdbcTemplate.update(sql, maVTPhong);
    }

    public boolean existsByMaVTPhong(String maVTPhong) {
        String sql = "SELECT COUNT(*) FROM VT_PHONG WHERE MAVTPHONG = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, maVTPhong);
        return count != null && count > 0;
    }

    public List<VT_PHONG> sortVTPhongByColumn(String column, String sortDirection) {
        List<String> validColumns = Arrays.asList("MAPHONG", "MAVT", "NGAYTHEM", "SOLUONG", "TIENNO");
        List<String> validDirections = Arrays.asList("asc", "desc");

        if (!validColumns.contains(column) || !validDirections.contains(sortDirection)) {
            throw new IllegalArgumentException("Invalid column name or sort direction");
        }

        String sql = "SELECT * FROM VT_PHONG ORDER BY " + column + " " + sortDirection;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(VT_PHONG.class));
    }

    public List<VT_PHONG> timKiemVTPhong(String maPhong, String maVT, String ngayCap, String ngaySuaDoi, String soLuong, String tinhTrang) {
        StringBuilder sql = new StringBuilder("SELECT * FROM VT_PHONG WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (maPhong != null && !maPhong.isEmpty()) {
            sql.append(" AND MAPHONG LIKE ?");
            params.add("%" + maPhong + "%");
        }
        if (maVT != null && !maVT.isEmpty()) {
            sql.append(" AND MAVT LIKE ?");
            params.add("%" + maVT + "%");
        }
        if (ngayCap != null && !ngayCap.isEmpty()) {
            sql.append(" AND NGAYTHEM LIKE ?");
            params.add("%" + ngayCap + "%");
        }
        if (soLuong != null && !soLuong.isEmpty()) {
            sql.append(" AND SOLUONG LIKE ?");
            params.add("%" + soLuong + "%");
        }
        if (tinhTrang != null && !tinhTrang.isEmpty()) {
            sql.append(" AND TINHTRANG LIKE ?");
            params.add("%" + tinhTrang + "%");
        }
        if (ngaySuaDoi != null && !ngaySuaDoi.isEmpty()) {
            sql.append(" AND NGAYSUADOI LIKE ?");
            params.add("%" + ngaySuaDoi + "%");
        }

        System.out.println(sql.toString());
        return jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(VT_PHONG.class));
    }
    
    public void xoaTheoBang(String maPhong, String maVT, String ngayCap, String ngaySuaDoi, String soLuong, String tinhTrang) {
        StringBuilder sql = new StringBuilder("DELETE FROM VT_PHONG WHERE 1=1");
        
        List<Object> params = new ArrayList<>();
        if (maPhong != null && !maPhong.isEmpty()) {
            sql.append(" AND MAPHONG LIKE ?");
            params.add("%" + maPhong + "%");
        }
        if (maVT != null && !maVT.isEmpty()) {
            sql.append(" AND MAVT LIKE ?");
            params.add("%" + maVT + "%");
        }
        if (ngayCap != null && !ngayCap.isEmpty()) {
            sql.append(" AND NGAYTHEM LIKE ?");
            params.add("%" + ngayCap + "%");
        }
        if (soLuong != null && !soLuong.isEmpty()) {
            sql.append(" AND SOLUONG LIKE ?");
            params.add("%" + soLuong + "%");
        }
        if (tinhTrang != null && !tinhTrang.isEmpty()) {
            sql.append(" AND TINHTRANG LIKE ?");
            params.add("%" + tinhTrang + "%");
        }
        if (ngaySuaDoi != null && !ngaySuaDoi.isEmpty()) {
            sql.append(" AND NGAYSUADOI LIKE ?");
            params.add("%" + ngaySuaDoi + "%");
        }
        System.out.println(sql.toString());
        jdbcTemplate.update(sql.toString(), params.toArray());
    }


}
