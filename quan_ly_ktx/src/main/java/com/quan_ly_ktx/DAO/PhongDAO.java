package com.quan_ly_ktx.DAO;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.quan_ly_ktx.Entity.PHONG.Phong;

@Repository
public class PhongDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addPhong(Phong phong) {
        // Lấy danh sách quản lý vật tư
/*        List<String> maQLList = getQLVTLIST();*/

        // Lấy ngày giờ hiện tại
        Timestamp ngayTao = new Timestamp(System.currentTimeMillis());

        String sqlPhong = "INSERT INTO PHONG (MAPHONG, TINHTRANG, SUCCHUA, KHUKTX, SOLUONG, MALOAIPHONG, NGAYTAO) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sqlPhong, phong.getMaPhong(), phong.getTinhTrang(), phong.getSucChua(), phong.getMaKhu(), "0", phong.getMaLoaiPhong(), ngayTao);

        // Thêm vào bảng QUANLY_VATTU
/*        String sqlQLVT = "INSERT INTO QUANLY_VATTU (MAQL, MAVT) VALUES (?, ?)";
        for (String maQL : maQLList) {
            jdbcTemplate.update(sqlQLVT, maQL, phong.getMaVT());
        }*/
    }
    
    public List<Phong> getAllPhongs() {
        String sql = "SELECT * FROM PHONG";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Phong.class));
    }


    public Phong getPhongById(String maPhong) {
        String sql = "SELECT * FROM PHONG WHERE maPhong = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{maPhong}, new BeanPropertyRowMapper<>(Phong.class));
    }


    public void updatePhong(Phong phong) {
        String sql = "UPDATE PHONG SET TINHTRANG = ?, SUCCHUA = ?, KHUKTX = ?, MALOAIPHONG = ?, NGAYSUADOI=?, NGUOISUADOICUOI=? WHERE MAPHONG = ?";
        Timestamp ngayTao = new Timestamp(System.currentTimeMillis());
        jdbcTemplate.update(sql, phong.getTinhTrang(), phong.getSucChua(), phong.getMaKhu(), phong.getMaLoaiPhong(),ngayTao, phong.getNguoiSuaDoiCuoi(), phong.getMaPhong());
    }


    public void deletePhong(String maPhong) {
        String sql = "DELETE FROM PHONG WHERE maPhong = ?";
        jdbcTemplate.update(sql, maPhong);
    }


    public boolean existsByMaPhong(String maPhong) {
        String sql = "SELECT COUNT(*) FROM PHONG WHERE maPhong = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, maPhong);
        return count != null && count > 0;
    }
    
    public List<Phong> sortPhongByColumn(String column, String sortDirection){
	    // Xác định danh sách các cột và hướng sắp xếp hợp lệ
		System.out.println("Tên cột: " + column);
		System.out.println("Mode: " + sortDirection);
	    List<String> validColumns = Arrays.asList("MAPHONG", "TINHTRANG", "SUCCHUA","KHUKTX","SOLUONG","MALOAIPHONG","NGAYTAO","NGAYSUADOI","NGUOISUADOI");
	    List<String> validDirections = Arrays.asList("asc", "desc");

	    // Kiểm tra tính hợp lệ của đầu vào
	    if (!validColumns.contains(column) || !validDirections.contains(sortDirection)) {
	        throw new IllegalArgumentException("Invalid column name or sort direction");
	    }
	    
	    String sql = "SELECT * FROM PHONG ORDER BY " + column + " " + sortDirection;
	    System.out.println("sql String  "+ sql);
	   // return jdbcTemplate.query(sql, new VatTuMapper());
	    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Phong.class));
    	
    }
    
    public List<Phong> timKiemPhong(String maPhong, String tinhTrang, String sucChua, String khuKTX, String soLuong, String maLoaiPhong) {
        // Xây dựng câu lệnh SQL động
        StringBuilder sql = new StringBuilder("SELECT * FROM PHONG WHERE 1=1");

        if (maPhong != null && !maPhong.isEmpty()) {
            sql.append(" AND maPhong LIKE '%").append(maPhong).append("%'");
        }
        if (tinhTrang != null && !tinhTrang.isEmpty()) {
            sql.append(" AND tinhTrang LIKE '%").append(tinhTrang).append("%'");
        }
        if (sucChua != null && !sucChua.isEmpty()) {
            sql.append(" AND sucChua LIKE '%").append(sucChua).append("%'");
        }
        if (khuKTX != null && !khuKTX.isEmpty()) {
            sql.append(" AND khuKTX LIKE '%").append(khuKTX).append("%'");
        }
        if (soLuong != null && !soLuong.isEmpty()) {
            sql.append(" AND soLuong LIKE '%").append(soLuong).append("%'");
        }
        if (maLoaiPhong != null && !maLoaiPhong.isEmpty()) {
            sql.append(" AND maLoaiPhong LIKE '%").append(maLoaiPhong).append("%'");
        }

        return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(Phong.class));
    }
}
