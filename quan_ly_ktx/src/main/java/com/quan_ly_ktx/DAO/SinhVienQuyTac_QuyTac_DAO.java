package com.quan_ly_ktx.DAO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.quan_ly_ktx.Entity.SinhVien.MapperQuyTac;
import com.quan_ly_ktx.Entity.SinhVien.MapperSinhVien;
import com.quan_ly_ktx.Entity.SinhVien.QuyTac;
import com.quan_ly_ktx.Entity.SinhVien.SinhVien_QuyTac;
@Repository
public class SinhVienQuyTac_QuyTac_DAO {
	@Autowired
	public JdbcTemplate _jdbcTemplate;
	
	public List<QuyTac> getAllQuyTac() {
		String sql = "SELECT * FROM QUYTAC ORDER BY MUCDOVIPHAM ASC ";
		List<QuyTac> quyTac = new ArrayList<QuyTac>();
		try {
			quyTac = _jdbcTemplate.query(sql, new MapperQuyTac());
			
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return quyTac;
	}
	
	public int soLoiCuaSinhVien(String maSV) {
        String sql = "SELECT COUNT(*) FROM SINHVIEN_QUYTAC JOIN QUYTAC ON SINHVIEN_QUYTAC.MAQUYTAC = QUYTAC.MAQUYTAC WHERE QUYTAC.MUCDOVIPHAM = ? AND SINHVIEN_QUYTAC.MASV = ?";
        List<Integer> mucDoViPham = List.of(1, 2, 3);
        int totalScore = 0;

        for (int mucDo : mucDoViPham) {
            Integer count = _jdbcTemplate.queryForObject(sql, new Object[]{mucDo, maSV}, Integer.class);
            totalScore += count * mucDo;  // Tổng điểm vi phạm
        }

        return totalScore;
    }
	
	public void createReport(SinhVien_QuyTac newReport, Timestamp thoiGianViPham) {
		String sql = "INSERT INTO SINHVIEN_QUYTAC(MASV, MAQUYTAC, THOIGIANVIPHAM, NGUOILAPBIENBAN) VALUES(?, ?, ?, ?)";
		String maSV = newReport.getMaSV();
		String maQuyTac = newReport.getMaQuyTac();
		String nguoiLapBienBan= newReport.getNguoiLapBienBan();
		try {
			_jdbcTemplate.update(sql, maSV, maQuyTac, thoiGianViPham, nguoiLapBienBan);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	public QuyTac getRuleByMaQuyTac(String maQuyTac) {
		String sql = "SELECT * FROM QUYTAC WHERE MAQUYTAC = ?";
		List<QuyTac> list = new ArrayList<QuyTac>();
		try {
			list = _jdbcTemplate.query(sql, new Object[] {maQuyTac}, new MapperQuyTac());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		if(list.size() != 0) {
			return list.get(0);
		}else {
			return null;
		}
	}
	
	public String getMaxMaQuyTac() {
	    String sql = "SELECT MAX(MAQUYTAC) FROM QUYTAC";
	    String maxMaQuyTac = _jdbcTemplate.queryForObject(sql, String.class);
	    return maxMaQuyTac;
	}
	
	public void createRule(QuyTac newQuyTac) {
		String sql = "INSERT INTO QUYTAC(MAQUYTAC, TENQUYTAC, MUCDOVIPHAM) VALUES(?, ?, ?)";
		String maQuyTac = newQuyTac.getMaQuyTac();
		String tenQuyTac = newQuyTac.getTenQuyTac();
		int mucDoViPham = newQuyTac.getMucDoViPham();
		try {
			_jdbcTemplate.update(sql, maQuyTac, tenQuyTac, mucDoViPham);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	public void editRule(String maQuyTac, String tenQuyTac, String mucDoViPham) {
		String sql = "UPDATE QUYTAC SET TENQUYTAC = ? , MUCDOVIPHAM = ? WHERE MAQUYTAC = ?";
		try {
			_jdbcTemplate.update(sql, tenQuyTac, mucDoViPham, maQuyTac);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteRule(String maQuyTac) {
		String sql = "DELETE QUYTAC WHERE MAQUYTAC = ?";
		try {
			_jdbcTemplate.update(sql, maQuyTac);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
}
	

