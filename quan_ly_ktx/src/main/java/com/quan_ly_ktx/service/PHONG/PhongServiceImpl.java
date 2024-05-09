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
    
    
    public List<Phong> timKiemTheoBang(String column, String searchValue){
    	return phongDAO.timKiemTheoBang(column, searchValue);
    }
}
