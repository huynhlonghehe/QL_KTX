package com.quan_ly_ktx.service.PHONG;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.quan_ly_ktx.DAO.PhongDAO;
import com.quan_ly_ktx.Entity.PHONG.Phong;

@Service
public class PhongServiceImpl implements PhongService {

    @Autowired
    private PhongDAO phongDAO;

    @Override
    public List<Phong> getAllPhongs() {
        return phongDAO.getAllPhongs();
    }
    
    
    @Override
    public List<String> getAllMaPhong() {
        return phongDAO.getAllMaPhong();
    }
    
    @Override
    public Phong getPhongById(String maPhong) {
        return phongDAO.getPhongById(maPhong);
    }

    @Override
    public void updatePhong(Phong phong) {
        phongDAO.updatePhong(phong);
    }

    @Override
    public void deletePhong(String maPhong) {
        phongDAO.deletePhong(maPhong);
    }

    @Override
    public boolean existsByMaPhong(String maPhong) {
        return phongDAO.existsByMaPhong(maPhong);
    }
    @Override
    public void addPhong(Phong phong) {
    	phongDAO.addPhong(phong);
    }
    
    @Override
    public List<Phong> sortPhongByColumn(String column, String sortDirection){
    	return phongDAO.sortPhongByColumn(column, sortDirection);
    }
    
    
    @Override
    public List<Phong> timKiemTheoBang(String maPhong, String tinhTrang, String sucChua, String khuKTX, String soLuong, String maLoaiPhong) {
        return phongDAO.timKiemTheoBang(maPhong, tinhTrang, sucChua, khuKTX, soLuong, maLoaiPhong);
    }
    
    @Override
    public int countHDByMaPhong(String maPhong) {
		return phongDAO.countHDbyMaPhong(maPhong);
	}
    
    @Override
    public void deletePhongByIds(List<String> ids) {
        for (String id : ids) {
            phongDAO.deletePhong(id);
        }
    }
}
