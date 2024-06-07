package com.quan_ly_ktx.DAO;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.quan_ly_ktx.Entity.VATTU.VatTu;
import com.quan_ly_ktx.Entity.VATTU.VatTuMapper;

@Repository
public class VatTuDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addVatTu(VatTu vatTu) {
        Timestamp ngayTao = new Timestamp(System.currentTimeMillis());

        String sqlVatTu = "INSERT INTO VATTU (MAVT, TENVT, GIATIEN, NGAYTAO) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sqlVatTu, vatTu.getMaVT(), vatTu.getTenVT(), vatTu.getGiaTien(), ngayTao);
    }

    public void updateQuanLyVatTu() {
        String sqlDeleteAll = "DELETE FROM QUANLY_VATTU";
        jdbcTemplate.update(sqlDeleteAll);
    }

    public List<VatTu> getAllVatTu() {
        String sql = "SELECT * FROM VATTU";
        updateQuanLyVatTu();
        return jdbcTemplate.query(sql, new VatTuMapper());
    }

    public List<String> getAllMaVT() {
        String sql = "SELECT MAVT FROM VATTU";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    public VatTu getVatTuById(String maVT) {
        String sql = "SELECT * FROM VATTU WHERE MAVT = ?";
        List<VatTu> result = jdbcTemplate.query(sql, new Object[]{maVT}, new VatTuMapper());
        return result.isEmpty() ? null : result.get(0);
    }

    public void updateVatTu(VatTu vatTu) {
        Timestamp ngaySuaDoi = new Timestamp(System.currentTimeMillis());
        String sql = "UPDATE VATTU SET TENVT = ?, GIATIEN = ?, NGAYSUADOI = ?, NGUOISUADOICUOI = ? WHERE MAVT = ?";
        jdbcTemplate.update(sql, vatTu.getTenVT(), vatTu.getGiaTien(), ngaySuaDoi, vatTu.getNguoiSuaDoiCuoi(), vatTu.getMaVT());
    }

    public void deleteVatTu(String maVT) {
        String sqlQLVT = "DELETE FROM QUANLY_VATTU WHERE MAVT = ?";
        jdbcTemplate.update(sqlQLVT, maVT);

        String sql = "DELETE FROM VATTU WHERE MAVT = ?";
        jdbcTemplate.update(sql, maVT);
    }

    public boolean existsByMaVT(String maVT) {
        String sql = "SELECT COUNT(*) FROM VATTU WHERE MAVT = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, maVT);
        return count != null && count > 0;
    }

    public List<VatTu> sortVatTuByColumn(String column, String sortDirection) {
        List<String> validColumns = Arrays.asList("MAVT", "TENVT", "GIATIEN", "NGAYTAO");
        List<String> validDirections = Arrays.asList("asc", "desc");

        if (!validColumns.contains(column) || !validDirections.contains(sortDirection)) {
            throw new IllegalArgumentException("Invalid column name or sort direction");
        }

        String sql = "SELECT * FROM VATTU ORDER BY " + column + " " + sortDirection;
        return jdbcTemplate.query(sql, new VatTuMapper());
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

    public List<VatTu> timKiemTheoBang(String maVT, String tenVT, String giaTien, String ngayTao, String ngaySuaDoi, String nguoiSuaDoiCuoi) {
        StringBuilder sql = new StringBuilder("SELECT * FROM VATTU WHERE 1=1");

        appendConditions(sql, "MAVT", maVT);
        appendConditions(sql, "TENVT", tenVT);
        appendConditions(sql, "GIATIEN", giaTien);
        appendConditions(sql, "NGAYTAO", ngayTao);
        appendConditions(sql, "NGAYSUADOI", ngaySuaDoi);
        appendConditions(sql, "NGUOISUADOICUOI", nguoiSuaDoiCuoi);

        return jdbcTemplate.query(sql.toString(), new VatTuMapper());
    }
}
