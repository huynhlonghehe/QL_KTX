package com.quan_ly_ktx.DAO;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.quan_ly_ktx.Entity.HoaDonDien.HoaDonDien;
import com.quan_ly_ktx.Entity.HoaDonDien.HoaDonDienMapper;

@Repository
public class HoaDonDienDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<HoaDonDien> getAllHoaDonDien() {
        String sql = "SELECT * FROM HOADONDIEN";
        return jdbcTemplate.query(sql, new HoaDonDienMapper());
    }

    public HoaDonDien getHoaDonDienById(String maHDD) {
        String sql = "SELECT * FROM HOADONDIEN WHERE MAHDD = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{maHDD}, new HoaDonDienMapper());
    }
    //lấy chỉ số sau của hợp đồng gần nhất để gán cho chỉ số trước mới nhất của phòng đó
    public int getChiSoTruocByMaPhong(String maPhong) {
        String sql = "SELECT TOP 1 CHISOSAU FROM HOADONDIEN WHERE MAPHONG = ? ORDER BY NGAYTAO DESC";
        return jdbcTemplate.queryForObject(sql, new Object[]{maPhong}, Integer.class);
    }


    public boolean existsByMaHDD(String maHDD) {
        String sql = "SELECT COUNT(*) FROM HOADONDIEN WHERE MAHDD = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, maHDD);
        return count != null && count > 0;
    }
    
    public void addHoaDonDien(HoaDonDien hoaDonDien) {
    	Timestamp ngayTao = new Timestamp(System.currentTimeMillis());
    	
        String sql = "INSERT INTO HOADONDIEN (MAHDD, CHISOTRUOC, CHISOSAU, HESOTIENDIEN, TIENNO, MAPHONG, NGAYTAO) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, hoaDonDien.getMaHDD(), hoaDonDien.getChiSoTruoc(), hoaDonDien.getChiSoSau(), hoaDonDien.getHeSoTienDien(), hoaDonDien.isTienNo(), hoaDonDien.getMaPhong(), ngayTao);
    }

    public int updateHoaDonDien(HoaDonDien hoaDonDien) {
    	Timestamp ngaySuaDoi = new Timestamp(System.currentTimeMillis());
        String sql = "UPDATE HOADONDIEN SET CHISOTRUOC = ?, CHISOSAU = ?, HESOTIENDIEN = ?, TIENNO = ?, MAPHONG = ?, NGAYTAO = ?, NGAYSUADOI = ?, NGUOISUADOICUOI = ? WHERE MAHDD = ?";
        return jdbcTemplate.update(sql, hoaDonDien.getChiSoTruoc(), hoaDonDien.getChiSoSau(), hoaDonDien.getHeSoTienDien(), hoaDonDien.isTienNo(), hoaDonDien.getMaPhong(), hoaDonDien.getNgayTao(), ngaySuaDoi, hoaDonDien.getNguoiSuaDoiCuoi(), hoaDonDien.getMaHDD());
    }

    public int deleteHoaDonDien(String maHDD) {
        String sql = "DELETE FROM HOADONDIEN WHERE MAHDD = ?";
        return jdbcTemplate.update(sql, maHDD);
    }
    
    public List<HoaDonDien> sortHDDByColumn(String column, String sortDirection) {
        // Xác định danh sách các cột và hướng sắp xếp hợp lệ
        List<String> validColumns = List.of("MAHDD", "CHISOTRUOC", "CHISOSAU", "HESOTIENDIEN", "TIENNO", "MAPHONG", "NGAYTAO", "NGAYSUADOI", "NGUOISUADOICUOI");
        List<String> validDirections = List.of("asc", "desc");

        // Kiểm tra tính hợp lệ của đầu vào
        if (!validColumns.contains(column) || !validDirections.contains(sortDirection)) {
            throw new IllegalArgumentException("Invalid column name or sort direction");
        }

        String sql = "SELECT * FROM HOADONDIEN ORDER BY " + column + " " + sortDirection;
        return jdbcTemplate.query(sql, new HoaDonDienMapper());
    }

    public List<HoaDonDien> timKiemTheoBang(String maHDD, String chiSoTruoc, String chiSoSau, String heSoTienDien, String tienNo, String maPhong, String ngayTao, String ngaySuaDoi, String nguoiSuaDoiCuoi) {
        StringBuilder sql = new StringBuilder("SELECT * FROM HOADONDIEN WHERE 1=1");

        appendConditions(sql, "MAHDD", maHDD);
        appendConditions(sql, "CHISOTRUOC", chiSoTruoc);
        appendConditions(sql, "CHISOSAU", chiSoSau);
        appendConditions(sql, "HESOTIENDIEN", heSoTienDien);
        appendConditions(sql, "TIENNO", tienNo);
        appendConditions(sql, "MAPHONG", maPhong);
        appendConditions(sql, "NGAYTAO", ngayTao);
        appendConditions(sql, "NGAYSUADOI", ngaySuaDoi);
        appendConditions(sql, "NGUOISUADOICUOI", nguoiSuaDoiCuoi);

        return jdbcTemplate.query(sql.toString(), new HoaDonDienMapper());
    }

    private void appendConditions(StringBuilder sql, String attribute, String value) {
        if (value != null && !value.isEmpty()) {
            sql.append(" AND (");
            String[] valuesArr = value.split(",");
            for (int i = 0; i < valuesArr.length; i++) {
                sql.append(attribute).append(" LIKE '%").append(valuesArr[i].trim()).append("%'");
                if (i < valuesArr.length - 1) {
                    sql.append(" OR ");
                }
            }
            sql.append(")");
        }
    }
}
