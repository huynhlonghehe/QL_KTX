package com.quan_ly_ktx.DAO;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import com.quan_ly_ktx.Entity.VatTu;
import com.quan_ly_ktx.Entity.VATTU.VatTuMapper;;

@Repository
public class VatTuDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addVatTu(VatTu vatTu) {
        String sql = "INSERT INTO VATTU (MAVT, TENVT, GIATIEN, MAQL) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, vatTu.getMaVT(), vatTu.getTenVT(), vatTu.getGiaTien(),"QL003");
    }
    
    public List<VatTu> getAllVatTu() {
        String sql = "SELECT * FROM VATTU";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(VatTu.class));
    }

    public VatTu getVatTuById(String maVT) {
        String sql = "SELECT * FROM VATTU WHERE MAVT = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{maVT}, new VatTuMapper());
    }
    
    public void updateVatTu(VatTu vatTu) {
        String sql = "UPDATE VATTU SET TENVT = ?, GIATIEN = ? WHERE MAVT = ?";
        //System.out.println("vat tuuuuuuuuu:" + vatTu.getMaVT());
        jdbcTemplate.update(sql, vatTu.getTenVT(), vatTu.getGiaTien(), vatTu.getMaVT());
    }

    public void deleteVatTu(String maVT) {
        String sql = "DELETE FROM VATTU WHERE MAVT = ?";
        
        jdbcTemplate.update(sql, maVT);
    }
    
    public boolean existsByMaVT(String maVT) {
        String sql = "SELECT COUNT(*) FROM VATTU WHERE MAVT = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, maVT);
        return count != null && count > 0;
    }
    
    public List<VatTu> sortVatTuByColumn(String column, String sortDirection){
	    // Xác định danh sách các cột và hướng sắp xếp hợp lệ
		System.out.println("Tên cột: " + column);
		System.out.println("Mode: " + sortDirection);
	    List<String> validColumns = Arrays.asList("MAVT", "TENVT", "GIATIEN", "MAQL");
	    List<String> validDirections = Arrays.asList("asc", "desc");

	    // Kiểm tra tính hợp lệ của đầu vào
	    if (!validColumns.contains(column) || !validDirections.contains(sortDirection)) {
	        throw new IllegalArgumentException("Invalid column name or sort direction");
	    }
	    
	    String sql = "SELECT * FROM VATTU ORDER BY " + column + " " + sortDirection;
	    System.out.println("sql String  "+ sql);
	   // return jdbcTemplate.query(sql, new VatTuMapper());
	    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(VatTu.class));
    	
    }
}