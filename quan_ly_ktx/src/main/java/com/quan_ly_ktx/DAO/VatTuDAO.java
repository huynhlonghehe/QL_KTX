package com.quan_ly_ktx.DAO;


import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import com.quan_ly_ktx.Entity.VATTU.VatTu;
import com.quan_ly_ktx.Entity.VATTU.VatTuMapper;

@Repository
public class VatTuDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private List<String> qlvtList;
    
    public void addVatTu(VatTu vatTu) {
        // Lấy danh sách quản lý vật tư
        List<String> maQLList = getQLVTLIST();

        // Lấy ngày giờ hiện tại
        Timestamp ngayTao = new Timestamp(System.currentTimeMillis());

        String sqlVatTu = "INSERT INTO VATTU (MAVT, TENVT, GIATIEN, NGAYTAO) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sqlVatTu, vatTu.getMaVT(), vatTu.getTenVT(), vatTu.getGiaTien(), ngayTao);

        // Thêm vào bảng QUANLY_VATTU
        String sqlQLVT = "INSERT INTO QUANLY_VATTU (MAQL, MAVT) VALUES (?, ?)";
        for (String maQL : maQLList) {
            jdbcTemplate.update(sqlQLVT, maQL, vatTu.getMaVT());
        }
    }


    //LẤY MÃ QUẢN LÝ CHO VẬT TƯ
    public List<String> getDuocQuanLyBoi(String maVT) {
        String sql = "SELECT MAQL FROM QUANLY_VATTU WHERE MAVT = ?";
        return jdbcTemplate.queryForList(sql, new Object[]{maVT}, String.class);
    }
    
    // Biến toàn cục để lưu trữ danh sách mã quản lý vật tư


    public List<String> getQLVTLIST() {
        String sql = "SELECT USERNAME FROM TAIKHOAN WHERE QUYEN = ?";
        qlvtList = jdbcTemplate.queryForList(sql, new Object[]{"Quản lý vật tư"}, String.class);
        return qlvtList;
    }

    // Phương thức getter để truy cập biến toàn cục từ bên ngoài lớp VatTuDAO
    public List<String> getQuanLyVatTuList() {
        return qlvtList;
    }
    
    public void updateQuanLyVatTu() {
        // Lấy danh sách mã quản lý vật tư từ cơ sở dữ liệu
        List<String> qlvtList = getQLVTLIST();

        // Xóa toàn bộ dữ liệu cũ trong bảng QUANLY_VATTU
        String sqlDeleteAll = "DELETE FROM QUANLY_VATTU";
        jdbcTemplate.update(sqlDeleteAll);

        // Thêm lại dữ liệu mới từ danh sách quản lý vật tư vào bảng QUANLY_VATTU
        String sqlInsert = "INSERT INTO QUANLY_VATTU (MAQL, MAVT) VALUES (?, ?)";
        for (String maQL : qlvtList) {
            for (String maVT : getAllMaVT()) { // Lấy danh sách tất cả các mã vật tư từ bảng VATTU
                jdbcTemplate.update(sqlInsert, maQL, maVT);
            }
        }
    }
    
    public List<VatTu> getAllVatTu() {
        String sql = "SELECT * FROM VATTU";
        updateQuanLyVatTu();
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(VatTu.class));
    }
    
    public List<String> getAllMaVT() {
        String sql = "SELECT MAVT FROM VATTU";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    public VatTu getVatTuById(String maVT) {
        String sql = "SELECT * FROM VATTU WHERE MAVT = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{maVT}, new VatTuMapper());
    }
    
    public void updateVatTu(VatTu vatTu) {
    	
    	Timestamp ngaySuaDoi = new Timestamp(System.currentTimeMillis());
        String sql = "UPDATE VATTU SET TENVT = ?, GIATIEN = ?, NGAYSUADOI = ?, NGUOISUADOICUOI = ? WHERE MAVT = ?";
        System.out.println("vat tuuuuuuuuu:" + vatTu.getNguoiSuaDoiCuoi());
        jdbcTemplate.update(sql, vatTu.getTenVT(), vatTu.getGiaTien(),ngaySuaDoi,vatTu.getNguoiSuaDoiCuoi(), vatTu.getMaVT() );
    }

    public void deleteVatTu(String maVT) {
        String sqlQLVT = "DELETE FROM QUANLY_VATTU WHERE MAVT = ?";
        jdbcTemplate.update(sqlQLVT, maVT);
        
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
	    List<String> validColumns = Arrays.asList("MAVT", "TENVT", "GIATIEN");
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