package com.quan_ly_ktx.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.quan_ly_ktx.Entity.VATTU.VT_PHONG;

@Repository
public class VT_PhongDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean existsReferencesToVatTu(String maVT) {
        String sql = "SELECT COUNT(*) FROM VT_PHONG WHERE MAVT = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, maVT);
        System.out.println("Kiem tra ton tai VATTU VTPHONG: "+count);
        return count != null && count > 0;
        
    }
    
    public boolean existsReferencesToPhong(String maPhong) {
        String sql = "SELECT COUNT(*) FROM VT_PHONG WHERE MAPHONG = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, maPhong);
        System.out.println("Kiem tra ton tai PHONG VTPHONG: "+count);
        return count != null && count > 0;
        
    }
    
	public List<VT_PHONG> getAllVTPHONG(){
		String sql="SELECT * FROM VT_PHONG";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(VT_PHONG.class));
				
	}


    // Các phương thức khác cho tương tác với bảng VT_PHONG

}
