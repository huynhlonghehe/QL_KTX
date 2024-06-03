package com.quan_ly_ktx.DAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

import com.quan_ly_ktx.Entity.HopDong.HopDong;
import com.quan_ly_ktx.Entity.HopDong.MapperHopDong;


@Repository
public class HopDongDAO {
	@Autowired
	public JdbcTemplate _jdbcTemplate;
	
	public List<HopDong> GetDataHopDong(){
		List<HopDong> list = new ArrayList<HopDong>();
		String sql = "SELECT * FROM HOPDONG";
		list = _jdbcTemplate.query(sql, new MapperHopDong());
		return list;
	}
	
	public HopDong GetHopDongByMaHD(String maHD){
		String sql = "SELECT * FROM HOPDONG WHERE MAHD = ?";
		List<HopDong> list = _jdbcTemplate.query(sql, new Object[]{maHD} ,new MapperHopDong());
		if (!list.isEmpty()) {
	        return list.get(0);
	    } else {
	        // Nếu không tìm thấy tài khoản, trả về null
	        return null;
	    }
	}
	
	public int countNumHopDongMaPhong(String maPhong)
	{
		String sql = "SELECT COUNT(*) AS count FROM HOPDONG WHERE MAPHONG = ?";
		return _jdbcTemplate.queryForObject(sql, Integer.class, maPhong);
		
	}
	
	/* kiểm tra sinh viên đã có hợp đồng chưa, không tính hợp đồng đã hết hạn */
	public boolean checkSVDangConHopDong(String maSV) {
		String sql_checkSV = "SELECT * FROM HOPDONG \r\n"
				+ "WHERE MASV = ? AND NGAYHETHAN > (SELECT CONVERT(date, GETDATE()));";
		List<HopDong> list_checkSV  = _jdbcTemplate.query(sql_checkSV, new Object[]{maSV} ,new MapperHopDong());
		if (!list_checkSV.isEmpty()) {
			/* Nếu còn hợp đồng thì trả về true */
	        return true;
	    } else {
			/* Nếu hợp đồng của Sinh Vien đó vẫn còn hiệu lực thì trả về false */
	        return false;
	    }
	}
	
	public boolean checkMaHDExists(String maHD) {
	    String sql = "SELECT COUNT(*) FROM HOPDONG WHERE MAHD = ?";
	    int count = _jdbcTemplate.queryForObject(sql, new Object[]{maHD}, Integer.class);
	    return count > 0;
	}
	
	public void createHD(HopDong hopDong) {
		String sql_createHD = "INSERT INTO HOPDONG(MAHD, NGAYTAO, NGAYHETHAN, SOTIEN, NAMHOC, HOCKY, MAPHONG, MASV) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		String maHD = hopDong.getMaHD();
		String ngayTao = hopDong.getNgayTao();
		String ngayHetHan = hopDong.getNgayHetHan();
		long soTien = hopDong.getSoTien();
		String namHoc = hopDong.getNamHoc();
		String hocKy = hopDong.getHocKy();
		String maPhong = hopDong.getMaPhong();
		String maSV = hopDong.getMaSV();
		try {
			_jdbcTemplate.update(sql_createHD, maHD, ngayTao, ngayHetHan, soTien, namHoc, hocKy, maPhong, maSV);
		} catch (DataAccessException e) {
 	        e.printStackTrace();
 	    }
	}
	
	public void updateHopDong(HopDong hopDong, String CurMaHD) {
		String sql= "UPDATE HOPDONG SET MAHD = ?, NGAYTAO = ?, NGAYHETHAN = ?, SOTIEN = ?, NAMHOC = ?, HOCKY = ?, MAPHONG = ?, MASV = ?, NGAYSUADOI = ?, NGUOISUADOICUOI = ? WHERE MAHD = ?";
		String maHD = hopDong.getMaHD();
		String ngayTao = hopDong.getNgayTao();
		String ngayHetHan = hopDong.getNgayHetHan();
		long soTien = hopDong.getSoTien();
		String namHoc = hopDong.getNamHoc();
		String hocKy = hopDong.getHocKy();
		String maPhong = hopDong.getMaPhong();
		String maSV = hopDong.getMaSV();
		LocalDateTime now = LocalDateTime.now();
		 // Định dạng ngày giờ nếu cần (ví dụ: yyyy-MM-dd HH:mm:ss)
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String ngaySuaDoi = now.format(formatter);
		String nguoiSuaDoiCuoiString = hopDong.getNguoiSuaDoiCuoi();
		try {
			_jdbcTemplate.update(sql, maHD, ngayTao, ngayHetHan, soTien, namHoc, hocKy, maPhong, maSV, ngaySuaDoi, nguoiSuaDoiCuoiString, CurMaHD);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteHopDong(String maHD) {
		String sql = "DELETE HOPDONG WHERE MAHD = ?";
		try {
			_jdbcTemplate.update(sql, maHD);
		}catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	
}
