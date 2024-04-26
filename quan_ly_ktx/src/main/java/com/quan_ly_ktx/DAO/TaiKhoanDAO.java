package com.quan_ly_ktx.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.quan_ly_ktx.Entity.TaiKhoan;
import com.quan_ly_ktx.Entity.MapperTaiKhoan;
@Repository
public class TaiKhoanDAO {
	
	
	@Autowired
	public JdbcTemplate _jdbcTemplate;
	
	public List<TaiKhoan> GetDataTaiKhoan(){
		List<TaiKhoan> list = new ArrayList<TaiKhoan>();
		String sql = "SELECT * FROM TAIKHOAN";
		list = _jdbcTemplate.query(sql, new MapperTaiKhoan());
		return list;
	}
	
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
	
	public TaiKhoan getTaiKhoanByUsername(String username) {
	    String sql = "SELECT * FROM TAIKHOAN WHERE USERNAME = ?";
	    List<TaiKhoan> list = _jdbcTemplate.query(sql, new Object[]{username}, new MapperTaiKhoan());
	    
	    // Nếu danh sách không rỗng và có ít nhất một phần tử, trả về phần tử đầu tiên
	    if (!list.isEmpty()) {
	        return list.get(0);
	    } else {
	        // Nếu không tìm thấy tài khoản, trả về null
	        return null;
	    }
	}
	
	public void updateTaiKhoan(TaiKhoan updateTaiKhoan) {
		String username = updateTaiKhoan.getTenDangNhap();
		String password = updateTaiKhoan.getMatKhau();
		String state = updateTaiKhoan.getTinhTrang();
		String permissions = updateTaiKhoan.getQuyen();
		String sql = "UPDATE TAIKHOAN SET USERNAME=?, PASSWORD=?, TINHTRANG=?, QUYEN=? WHERE USERNAME = ?";
		int state_int = 0;
		/*
		 * System.out.println("username: " + username); System.out.println("password: "
		 * + password); System.out.println("Trạng thái nè: " + state);
		 * System.out.println("permissions: " + permissions);
		 */
		try {
			if(state.equals("1")) {
				
				state_int = 1;
			}
	        _jdbcTemplate.update(sql, username, password, state_int, permissions, username);
	    } catch (DataAccessException e) {
	       
	        e.printStackTrace();
	    }
	}
	
	public void deleteTaiKhoan(TaiKhoan deleteTaiKhoan) {
	    String username = deleteTaiKhoan.getTenDangNhap();
	    String quyen = deleteTaiKhoan.getQuyen();
	    String table = (quyen.equals("Sinh viên")) ? "SINHVIEN" : "QUANLY";
	    
	    String sql1 = "DELETE FROM " + table + " WHERE USERNAME = ?";
	    String sql2 = "DELETE FROM TAIKHOAN WHERE USERNAME = ?";
	    
	    try {
	        int deleteThongTin = _jdbcTemplate.update(sql1, username);
	        System.out.println("Số cột thực hiện được: " + deleteThongTin);
	        if (deleteThongTin > 0) {
	            _jdbcTemplate.update(sql2, username);
	        }
	    } catch (DataAccessException e) {
	        e.printStackTrace();
	    }
	}



}
