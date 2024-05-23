package com.quan_ly_ktx.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.quan_ly_ktx.Entity.SinhVien.MapperQuanLy_SinhVien;
import com.quan_ly_ktx.Entity.SinhVien.MapperSinhVien;
import com.quan_ly_ktx.Entity.SinhVien.QuanLy_SinhVien;
import com.quan_ly_ktx.Entity.SinhVien.SinhVien;
import com.quan_ly_ktx.Entity.TaiKhoan.MapperTaiKhoan;
import com.quan_ly_ktx.Entity.TaiKhoan.TaiKhoan;
import com.quan_ly_ktx.DTO.SinhVienDetailsDTO;
import com.quan_ly_ktx.DTO.MapperSinhVienDetailsDTO;
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
	
	public void updateSinhVien(SinhVien sinhVien, String CurMaSV) {
		String sql = "UPDATE SINHVIEN SET MASV = ?, HO = ?, TEN = ?, NGAYSINH = ?, DIACHI = ?, SDT = ?, GIOITINH = ?, LOP = ?, NGAYSUADOI = ?, NGUOISUADOICUOI = ? where MASV = ?";
		 String maSV = sinhVien.getMaSV();
		 String ho = sinhVien.getHo();
		 ho = ho.trim();
		 String ten = sinhVien.getTen();
		 ten = ten.trim();
		 String ngaySinh = sinhVien.getNgaySinh();
		 String diaChi = sinhVien.getDiaChi();
		 String sdt = sinhVien.getSdt();
		 String gioiTinh = sinhVien.getGioiTinh();
		 String lop= sinhVien.getLop();
		// Lấy ngày giờ hiện tại
		 LocalDateTime now = LocalDateTime.now();

		 // Định dạng ngày giờ nếu cần (ví dụ: yyyy-MM-dd HH:mm:ss)
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		 String ngaySuaDoi = now.format(formatter);
		 String nguoiSuaDoiCuoi = sinhVien.getNguoiSuaDoiCuoi();
         
         try {
        	 _jdbcTemplate.update(sql, maSV, ho , ten , ngaySinh , diaChi , sdt , gioiTinh, lop, ngaySuaDoi, nguoiSuaDoiCuoi , CurMaSV);
 	    } catch (DataAccessException e) {
 	       
 	        e.printStackTrace();
 	    }
	} 
	
	public void deleteSinhVien(String maSV) {
		String sql = "DELETE SINHVIEN WHERE MASV = ?";
		try {
			_jdbcTemplate.update(sql, maSV);
		}catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	public List<SinhVien> sortSinhVienByColumn(String column, String sortDirection) {
	    // Xác định danh sách các cột và hướng sắp xếp hợp lệ
	    List<String> validColumns = Arrays.asList("MASV", "TEN", "GIOITINH", "NGAYSINH");
	    List<String> validDirections = Arrays.asList("asc", "desc");

	    // Kiểm tra tính hợp lệ của đầu vào
	    if (!validColumns.contains(column) || !validDirections.contains(sortDirection)) {
	        throw new IllegalArgumentException("Invalid column name or sort direction");
	    }
	    
	    String sql = "SELECT * FROM SINHVIEN ORDER BY " + column + " " + sortDirection;
	    return _jdbcTemplate.query(sql, new MapperSinhVien());
	}
	
	public void createSinhVien(SinhVien newSinhVien) {
		String sql = "INSERT INTO SINHVIEN(MASV, HO, TEN, NGAYSINH, DIACHI, SDT, GIOITINH, LOP, NGAYTAO) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String maSV = newSinhVien.getMaSV();
		String ho = newSinhVien.getHo();
		String ten = newSinhVien.getTen();
		String ngaySinh = newSinhVien.getNgaySinh();
		String diaChi = newSinhVien.getDiaChi();
		String sdt= newSinhVien.getSdt();
		String gioiTinh= newSinhVien.getGioiTinh();
		String lop= newSinhVien.getLop();
		 // Lấy ngày hiện tại
		LocalDateTime now = LocalDateTime.now();
		
		System.out.print("TOi dc day");

		// Định dạng ngày giờ nếu cần (ví dụ: yyyy-MM-dd HH:mm:ss)
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String ngayTao = now.format(formatter);
	    try {
	    	_jdbcTemplate.update(sql, maSV, ho, ten, ngaySinh, diaChi, sdt, gioiTinh, lop, ngayTao);
	    } catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	public List<SinhVien> searchSinhVien(String maSV, String ho, String ten, String gioiTinh, String ngaySinh, String diaChi, String lop, String sort, String direction) {
		StringBuilder sql = new StringBuilder("SELECT * FROM SINHVIEN WHERE 1 = 1");
        // Sử dụng StringBuilder để xây dựng câu truy vấn
        if (maSV != null && !maSV.isEmpty()) {
            sql.append(" AND MASV LIKE ?");
        }
        if (ho != null && !ho.isEmpty()) {
            sql.append(" AND HO LIKE ?");
        }
        if (ten != null && !ten.isEmpty()) {
            sql.append(" AND TEN LIKE ?");
        }
        if (!gioiTinh.equals("Giới tính") && !gioiTinh.isEmpty()) {
            sql.append(" AND GIOITINH LIKE ?");
        }
        if (ngaySinh != null && !ngaySinh.isEmpty()) {
            sql.append(" AND NGAYSINH LIKE ?");
        }
        if (diaChi != null && !diaChi.isEmpty()) {
            sql.append(" AND DIACHI LIKE ?");
        }
        if (lop != null && !lop.isEmpty()) {
            sql.append(" AND LOP LIKE ?");
        }
        if (sort != null && direction != null) {
            sql.append(" ORDER BY " + sort + " " + direction);
        }
        System.out.println("SQL: " + sql.toString());
        return _jdbcTemplate.query(sql.toString(), new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                int index = 1;
                if (maSV != null && !maSV.isEmpty()) {
                    ps.setString(index++, "%" + maSV + "%");
                }
                if (ho != null && !ho.isEmpty()) {
                    ps.setString(index++, "%" + ho + "%");
                }
                if (ten != null && !ten.isEmpty()) {
                    ps.setString(index++, "%" + ten + "%");
                }
                if (!gioiTinh.equals("Giới tính") && !gioiTinh.isEmpty()) {
                    ps.setString(index++, "%" + gioiTinh + "%");
                }
                if (ngaySinh != null && !ngaySinh.isEmpty()) {
                    ps.setString(index++, "%" + ngaySinh + "%");
                }
                if (diaChi != null && !diaChi.isEmpty()) {
                    ps.setString(index++, "%" + diaChi + "%");
                }
                if (lop != null && !lop.isEmpty()) {
                    ps.setString(index++, "%" + lop + "%");
                }
             
            }
        }, new MapperSinhVien());
    }
	
	/* Lấy danh sách các sinh viên có vi phạm */
public List<SinhVienDetailsDTO> GetDataSinhVienCoViPham(){
		
		List<SinhVienDetailsDTO> listSinhVienViPham = new ArrayList<SinhVienDetailsDTO>();
		String sql2 = "SELECT sv.*, svq.MASVVP, svq.MAQUYTAC, svq.THOIGIANVIPHAM, qt.TENQUYTAC, qt.MUCDOVIPHAM FROM SINHVIEN sv JOIN SINHVIEN_QUYTAC svq ON sv.MASV = svq.MASV JOIN QUYTAC qt ON svq.MAQUYTAC = qt.MAQUYTAC";
		listSinhVienViPham = _jdbcTemplate.query(sql2, new MapperSinhVienDetailsDTO());
		return listSinhVienViPham;
	}
}
