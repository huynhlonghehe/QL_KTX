package com.quan_ly_ktx.service.HOADONDIEN;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quan_ly_ktx.DAO.HoaDonDienDAO;
import com.quan_ly_ktx.Entity.HoaDonDien.HoaDonDien;

@Service
public class HoaDonDienServiceImpl implements HoaDonDienService {

    @Autowired
    private HoaDonDienDAO hoaDonDienDAO;

    @Override
    public List<HoaDonDien> getAllHoaDonDien() {
        return hoaDonDienDAO.getAllHoaDonDien();
    }

    @Override
    public HoaDonDien getHoaDonDienById(String maHDD) {
        return hoaDonDienDAO.getHoaDonDienById(maHDD);
    }

    @Override
    public void addHoaDonDien(HoaDonDien hoaDonDien) {
        hoaDonDienDAO.addHoaDonDien(hoaDonDien);
    }

    @Override
    public int updateHoaDonDien(HoaDonDien hoaDonDien) {
        return hoaDonDienDAO.updateHoaDonDien(hoaDonDien);
    }

    @Override
    public int deleteHoaDonDien(String maHDD) {
        return hoaDonDienDAO.deleteHoaDonDien(maHDD);
    }

    @Override
    public List<HoaDonDien> sortHDDByColumn(String column, String sortDirection) {
        return hoaDonDienDAO.sortHDDByColumn(column, sortDirection);
    }

    @Override
    public List<HoaDonDien> timKiemTheoBang(String maHDD, String chiSoTruoc, String chiSoSau, String heSoTienDien, String tienNo, String maPhong, String ngayTao, String ngaySuaDoi, String nguoiSuaDoiCuoi) {
        return hoaDonDienDAO.timKiemTheoBang(maHDD, chiSoTruoc, chiSoSau, heSoTienDien, tienNo, maPhong, ngayTao, ngaySuaDoi, nguoiSuaDoiCuoi);
    }
    
    @Override
    public int getChiSoTruocByMaPhong(String maPhong) {
    	return hoaDonDienDAO.getChiSoTruocByMaPhong(maPhong);
    }
    
    @Override
    public boolean existsByMaHDD(String maHDD) {
    	return hoaDonDienDAO.existsByMaHDD(maHDD);
    }
    
}

