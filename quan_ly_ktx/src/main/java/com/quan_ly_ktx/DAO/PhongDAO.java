package com.quan_ly_ktx.DAO;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.quan_ly_ktx.Entity.PHONG.Phong;
import com.quan_ly_ktx.Entity.PHONG.PhongMapper;

@Repository
public class PhongDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    
    public boolean checkExistInHDD(String maPhong) {
    	String sql = "SELECT COUNT(*) FROM HOADONDIEN WHERE MAPHONG = ?";
    	Integer count = jdbcTemplate.queryForObject(sql, Integer.class, maPhong);
    	return count != null && count > 0;
    }
    public void addPhong(Phong phong) {
        Timestamp ngayTao = new Timestamp(System.currentTimeMillis());

        String sqlPhong = "INSERT INTO PHONG (MAPHONG, TINHTRANG, SUCCHUA, KHUKTX, SOLUONG, MALOAIPHONG, NGAYTAO) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sqlPhong, phong.getMaPhong(), phong.getTinhTrang(), phong.getSucChua(), phong.getMaKhu(), 0, phong.getMaLoaiPhong(), ngayTao);
    }

    public List<Phong> getAllPhongs() {
        String sql = "SELECT * FROM PHONG";
        return jdbcTemplate.query(sql, new PhongMapper());
    }

    public List<String> getAllMaPhong() {
        String sql = "SELECT MAPHONG FROM PHONG";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("MAPHONG"));
    }

    public Phong getPhongById(String maPhong) {
        String sql = "SELECT * FROM PHONG WHERE MAPHONG = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{maPhong}, new PhongMapper());
    }

    public int countHDbyMaPhong(String maPhong) {
        String sql = "SELECT COUNT(*) FROM HOPDONG WHERE MAPHONG = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, maPhong);
        return count != null ? count : 0;
    }

    public void updatePhong(Phong phong) {
        String sql = "UPDATE PHONG SET TINHTRANG = ?, SUCCHUA = ?, KHUKTX = ?, MALOAIPHONG = ?, NGAYSUADOI = ?, NGUOISUADOICUOI = ? WHERE MAPHONG = ?";
        Timestamp ngaySuaDoi = new Timestamp(System.currentTimeMillis());
        jdbcTemplate.update(sql, phong.getTinhTrang(), phong.getSucChua(), phong.getKhuKTX(), phong.getMaLoaiPhong(), ngaySuaDoi, phong.getNguoiSuaDoiCuoi(), phong.getMaPhong());
    }

    public void deletePhong(String maPhong) {
        String sql = "DELETE FROM PHONG WHERE MAPHONG = ?";
        jdbcTemplate.update(sql, maPhong);
    }

    public boolean existsByMaPhong(String maPhong) {
        String sql = "SELECT COUNT(*) FROM PHONG WHERE MAPHONG = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, maPhong);
        return count != null && count > 0;
    }

    public List<Phong> sortPhongByColumn(String column, String sortDirection) {
        List<String> validColumns = Arrays.asList("MAPHONG", "TINHTRANG", "SUCCHUA", "KHUKTX", "SOLUONG", "MALOAIPHONG", "NGAYTAO", "NGAYSUADOI", "NGUOISUADOI");
        List<String> validDirections = Arrays.asList("asc", "desc");

        if (!validColumns.contains(column) || !validDirections.contains(sortDirection)) {
            throw new IllegalArgumentException("Invalid column name or sort direction");
        }

        String sql = "SELECT * FROM PHONG ORDER BY " + column + " " + sortDirection;
        return jdbcTemplate.query(sql, new PhongMapper());
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

    public List<Phong> timKiemTheoBang(String maPhong, String tinhTrang, String sucChua, String khuKTX, String soLuong, String maLoaiPhong) {
        StringBuilder sql = new StringBuilder("SELECT * FROM PHONG WHERE 1=1");

        appendConditions(sql, "MAPHONG", maPhong);
        appendConditions(sql, "TINHTRANG", tinhTrang);
        appendConditions(sql, "SUCCHUA", sucChua);
        appendConditions(sql, "KHUKTX", khuKTX);
        appendConditions(sql, "SOLUONG", soLuong);
        appendConditions(sql, "MALOAIPHONG", maLoaiPhong);

        return jdbcTemplate.query(sql.toString(), new PhongMapper());
    }
}
