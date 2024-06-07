package com.quan_ly_ktx.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.quan_ly_ktx.Entity.*;
import com.quan_ly_ktx.Entity.TaiKhoan.MapperTaiKhoan;
import com.quan_ly_ktx.Entity.TaiKhoan.TaiKhoan;

@Repository
public class LoginDAO {
	@Autowired
	public JdbcTemplate _jdbcTemplate;
	
	public List<TaiKhoan> getTaiKhoan(String TK) {
	    List<TaiKhoan> list = new ArrayList<TaiKhoan>();
	    String sql = "SELECT * FROM TAIKHOAN WHERE USERNAME = ?";
	    
	    try {
	        list = _jdbcTemplate.query(sql, new Object[]{TK}, new MapperTaiKhoan());
	    } catch (DataAccessException e) {
	        // Xử lý lỗi nếu cần thiết
	        e.printStackTrace();
	    }
	    
	    return list;
	}
	
	public boolean checkPassword(String TK, String password) {
		String sql = "SELECT * FROM TAIKHOAN WHERE USERNAME = ? AND PASSWORD = ?";
		 List<TaiKhoan> list = new ArrayList<TaiKhoan>();
	    try {
	        list = _jdbcTemplate.query(sql, new Object[]{TK, password}, new MapperTaiKhoan());
	    } catch (DataAccessException e) {
	        // Xử lý lỗi nếu cần thiết
	        e.printStackTrace();
	    }
	    
	    if(list.size() == 0) return false;
	    return true;
	}
	
	public String loginMethod(String TK, String password) {
		
		List<TaiKhoan> list = getTaiKhoan(TK);
		
		if(list.size() == 0) {
			return "Tài khoản không tồn tại, vui lòng kiểm tra lại";
		}else {
			boolean check  = checkPassword(TK, password);
			if(check) {
				return "Success%"+list.get(0).getQuyen();
			}else return "Mật khẩu không chính xác";
		}
		
	}
}
