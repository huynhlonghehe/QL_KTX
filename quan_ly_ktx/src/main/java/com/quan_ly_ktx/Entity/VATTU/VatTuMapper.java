package com.quan_ly_ktx.Entity.VATTU;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.quan_ly_ktx.Entity.VatTu;

public class VatTuMapper implements RowMapper<VatTu> {
    @Override
    public VatTu mapRow(ResultSet rs, int rowNum) throws SQLException {
        VatTu vatTu = new VatTu();
        vatTu.setMaVT(rs.getString("MAVT"));
        vatTu.setTenVT(rs.getNString("TENVT")); // Sử dụng getNString để lấy chuỗi Unicode
        vatTu.setGiaTien(rs.getDouble("GIATIEN"));
        return vatTu;
    }
}
