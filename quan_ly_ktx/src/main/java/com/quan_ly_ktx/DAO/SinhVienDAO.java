package com.quan_ly_ktx.DAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.quan_ly_ktx.Entity.MapperSinhVien;
import com.quan_ly_ktx.Entity.SinhVien;
@Repository
public class SinhVienDAO {
	@Autowired
	public JdbcTemplate _jdbcTemplate;
	public List<SinhVien> GetDataSinhVien(){
		List<SinhVien> list = new ArrayList<SinhVien>();
		String sql = "SELECT * FROM SINHVIEN";
		list = _jdbcTemplate.query(sql, new MapperSinhVien());
		return list;
	}
	
	public SinhVien getSinhVienByMaSV(String maSV) {
	    String sql = "SELECT * FROM SINHVIEN WHERE MASV = ?";
	    List<SinhVien> list = _jdbcTemplate.query(sql, new Object[]{maSV}, new MapperSinhVien());
	    
	    // Nếu danh sách không rỗng và có ít nhất một phần tử, trả về phần tử đầu tiên
	    if (!list.isEmpty()) {
	        return list.get(0);
	    } else {
	        // Nếu không tìm thấy tài khoản, trả về null
	        return null;
	    }
	}
	
	public void updateSinhVien(SinhVien sinhVien) {
		String sql = "UPDATE SINHVIEN SET MASV = ?, HO = ?, TEN = ?, NGAYSINH = ?, DIACHI = ?, SDT = ?, GIOITINH = ?, MAQL = ? where MASV = ?";
		 String maSV = sinhVien.getMaSV();
		 String ho = sinhVien.getHo();
		 ho = ho.trim();
		 String ten = sinhVien.getTen();
		 ten = ten.trim();
		 String ngaySinh = sinhVien.getNgaySinh();
		 String diaChi = sinhVien.getDiaChi();
		 String sdt = sinhVien.getSdt();
		 String gioiTinh = sinhVien.getGioiTinh();
         String maQL = sinhVien.getMaQL();
         System.out.println("Giới tính: " + gioiTinh);
         try {
        	 _jdbcTemplate.update(sql, maSV, ho , ten , ngaySinh , diaChi , sdt , gioiTinh , maQL, maSV);
        	 System.out.println("Update được rồi nhưng vào chưa thì chưa biết");
 	    } catch (DataAccessException e) {
 	       
 	        e.printStackTrace();
 	    }
	} 
	
	public List<SinhVien> sortSinhVienByColumn(String column, String sortDirection) {
	    // Xác định danh sách các cột và hướng sắp xếp hợp lệ
		System.out.println("Tên cột: " + column);
		System.out.println("Mode: " + sortDirection);
	    List<String> validColumns = Arrays.asList("MASV", "TEN", "GIOITINH", "NGAYSINH");
	    List<String> validDirections = Arrays.asList("asc", "desc");

	    // Kiểm tra tính hợp lệ của đầu vào
	    if (!validColumns.contains(column) || !validDirections.contains(sortDirection)) {
	        throw new IllegalArgumentException("Invalid column name or sort direction");
	    }
	    
	    String sql = "SELECT * FROM SINHVIEN ORDER BY " + column + " " + sortDirection;
	    return _jdbcTemplate.query(sql, new MapperSinhVien());
	}

	
}
