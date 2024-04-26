package com.quan_ly_ktx.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class VT_PhongDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean existsReferencesToVatTu(String maVT) {
        String sql = "SELECT COUNT(*) FROM VT_PHONG WHERE MAVT = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, maVT);
        System.out.println("Kiem tra ton tai VTPHONG: "+count);
        return count != null && count > 0;
        
    }

    // Các phương thức khác cho tương tác với bảng VT_PHONG

}
