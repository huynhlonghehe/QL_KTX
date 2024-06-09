package com.quan_ly_ktx.Entity.HoaDonDien;

import java.util.Date;

public class HoaDonDien {
    private String maHDD;
    private String chiSoTruoc;
    private String chiSoSau;
    private float heSoTienDien;
    private boolean tienNo;
    private String maPhong;
    private Date ngayTao;
    private Date ngaySuaDoi;
    private String nguoiSuaDoiCuoi;

    // Getters and Setters
    public String getMaHDD() {
        return maHDD;
    }

    public void setMaHDD(String maHDD) {
        this.maHDD = maHDD;
    }

    public String getChiSoTruoc() {
        return chiSoTruoc;
    }

    public void setChiSoTruoc(String chiSoTruoc) {
        this.chiSoTruoc = chiSoTruoc;
    }

    public String getChiSoSau() {
        return chiSoSau;
    }

    public void setChiSoSau(String chiSoSau) {
        this.chiSoSau = chiSoSau;
    }

    public float getHeSoTienDien() {
        return heSoTienDien;
    }

    public void setHeSoTienDien(float heSoTienDien) {
        this.heSoTienDien = heSoTienDien;
    }

    public boolean isTienNo() {
        return tienNo;
    }

    public void setTienNo(boolean tienNo) {
        this.tienNo = tienNo;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgaySuaDoi() {
        return ngaySuaDoi;
    }

    public void setNgaySuaDoi(Date ngaySuaDoi) {
        this.ngaySuaDoi = ngaySuaDoi;
    }

    public String getNguoiSuaDoiCuoi() {
        return nguoiSuaDoiCuoi;
    }

    public void setNguoiSuaDoiCuoi(String nguoiSuaDoiCuoi) {
        this.nguoiSuaDoiCuoi = nguoiSuaDoiCuoi;
    }
}
