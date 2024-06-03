package com.quan_ly_ktx.DAO;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.quan_ly_ktx.Entity.VATTU.VatTu;
import com.quan_ly_ktx.Entity.VATTU.VatTuMapper;

@Repository
public class VatTuDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addVatTu(VatTu vatTu) {


        // Lấy ngày giờ hiện tại
        Timestamp ngayTao = new Timestamp(System.currentTimeMillis());

        String sqlVatTu = "INSERT INTO VATTU (MAVT, TENVT, GIATIEN, NGAYTAO) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sqlVatTu, vatTu.getMaVT(), vatTu.getTenVT(), vatTu.getGiaTien(), ngayTao);

        // Thêm vào bảng QUANLY_VATTU
        String sqlQLVT = "INSERT INTO QUANLY_VATTU (MAQL, MAVT) VALUES (?, ?)";
    }


    // CẬP NHẬT DANH SÁCH QUẢN LÝ VẬT TƯ
    public void updateQuanLyVatTu() {

        // Xóa toàn bộ dữ liệu cũ trong bảng QUANLY_VATTU
        String sqlDeleteAll = "DELETE FROM QUANLY_VATTU";
        jdbcTemplate.update(sqlDeleteAll);

    }

    // LẤY DANH SÁCH TẤT CẢ VẬT TƯ
    public List<VatTu> getAllVatTu() {
        String sql = "SELECT * FROM VATTU";
        updateQuanLyVatTu();
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(VatTu.class));
    }

    // LẤY DANH SÁCH MÃ VẬT TƯ
    public List<String> getAllMaVT() {
        String sql = "SELECT MAVT FROM VATTU";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    // LẤY VẬT TƯ BANG MÃ VẬT TƯ
    public VatTu getVatTuById(String maVT) {
        String sql = "SELECT * FROM VATTU WHERE MAVT = ?";
        List<VatTu> result = jdbcTemplate.query(sql, new Object[]{maVT}, new BeanPropertyRowMapper<>(VatTu.class));
        return result.isEmpty() ? null : result.get(0);
    }

    // CẬP NHẬT THÔNG TIN VẬT TƯ
    public void updateVatTu(VatTu vatTu) {
        Timestamp ngaySuaDoi = new Timestamp(System.currentTimeMillis());
        String sql = "UPDATE VATTU SET TENVT = ?, GIATIEN = ?, NGAYSUADOI = ?, NGUOISUADOICUOI = ? WHERE MAVT = ?";
        jdbcTemplate.update(sql, vatTu.getTenVT(), vatTu.getGiaTien(), ngaySuaDoi, vatTu.getNguoiSuaDoiCuoi(), vatTu.getMaVT());
    }

    // XÓA VẬT TƯ
    public void deleteVatTu(String maVT) {
        String sqlQLVT = "DELETE FROM QUANLY_VATTU WHERE MAVT = ?";
        jdbcTemplate.update(sqlQLVT, maVT);

        String sql = "DELETE FROM VATTU WHERE MAVT = ?";
        jdbcTemplate.update(sql, maVT);
    }

    // KIỂM TRA SỰ TỒN TẠI CỦA MÃ VẬT TƯ
    public boolean existsByMaVT(String maVT) {
        String sql = "SELECT COUNT(*) FROM VATTU WHERE MAVT = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, maVT);
        return count != null && count > 0;
    }

    // SẮP XẾP VẬT TƯ THEO CỘT
    public List<VatTu> sortVatTuByColumn(String column, String sortDirection) {
        // Xác định danh sách các cột và hướng sắp xếp hợp lệ
        List<String> validColumns = Arrays.asList("MAVT", "TENVT", "GIATIEN","NGAYCAP");
        List<String> validDirections = Arrays.asList("asc", "desc");

        // Kiểm tra tính hợp lệ của đầu vào
        if (!validColumns.contains(column) || !validDirections.contains(sortDirection)) {
            throw new IllegalArgumentException("Invalid column name or sort direction");
        }

        String sql = "SELECT * FROM VATTU ORDER BY " + column + " " + sortDirection;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(VatTu.class));
    }
}
