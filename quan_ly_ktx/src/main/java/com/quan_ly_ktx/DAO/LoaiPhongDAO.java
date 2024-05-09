package com.quan_ly_ktx.DAO;

import com.quan_ly_ktx.Entity.PHONG.LoaiPhong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoaiPhongDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<LoaiPhong> getAllLoaiPhong() {
        String sql = "SELECT * FROM LOAIPHONG";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(LoaiPhong.class));
    }

    public LoaiPhong getLoaiPhongById(String maLoaiPhong) {
        String sql = "SELECT * FROM LOAIPHONG WHERE MALOAIPHONG = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{maLoaiPhong}, new BeanPropertyRowMapper<>(LoaiPhong.class));
    }

    public void addLoaiPhong(LoaiPhong loaiPhong) {
        String sql = "INSERT INTO LOAIPHONG (MALOAIPHONG, DICHVU, MUCDIENHOTRO_KWh, GIALOAIPHONG) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, loaiPhong.getMaLoaiPhong(), loaiPhong.getDichVu(), loaiPhong.getMucDienHoTroKWh(), loaiPhong.getGiaLoaiPhong());
    }

    public void updateLoaiPhong(LoaiPhong loaiPhong) {
        String sql = "UPDATE LOAIPHONG SET DICHVU = ?, MUCDIENHOTRO_KWh = ?, GIALOAIPHONG = ? WHERE MALOAIPHONG = ?";
        jdbcTemplate.update(sql, loaiPhong.getDichVu(), loaiPhong.getMucDienHoTroKWh(), loaiPhong.getGiaLoaiPhong(), loaiPhong.getMaLoaiPhong());
    }

    public void deleteLoaiPhong(String maLoaiPhong) {
        String sql = "DELETE FROM LOAIPHONG WHERE MALOAIPHONG = ?";
        jdbcTemplate.update(sql, maLoaiPhong);
    }
    
}
