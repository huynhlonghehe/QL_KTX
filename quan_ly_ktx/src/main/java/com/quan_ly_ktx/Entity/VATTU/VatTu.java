package com.quan_ly_ktx.Entity.VATTU;

import java.sql.Date;
import java.text.SimpleDateFormat;


public class VatTu {
    private String maSo;
    private String maKhu;
    private String maVT;
    private String tenVT;
    private double giaTien;
    private Date ngayTao;
    private Date ngaySuaDoi;
    private String nguoiSuaDoiCuoi;

    public VatTu() {
    }

    public VatTu(String maSo, String maKhu, String maVT, String tenVT, double giaTien, Date ngayTao, Date ngaySuaDoi, String nguoiSuaDoiCuoi) {
        this.maSo = maSo;
        this.maKhu = maKhu;
        this.maVT = maVT;
        this.tenVT = tenVT;
        this.giaTien = giaTien;
        this.ngayTao = ngayTao;
        this.ngaySuaDoi = ngaySuaDoi;
        this.nguoiSuaDoiCuoi = nguoiSuaDoiCuoi;
    }

    public String getMaSo() {
        return maSo;
    }

    public void setMaSo(String maSo) {
        this.maSo = maSo;
    }

    public String getMaKhu() {
        return maKhu;
    }

    public void setMaKhu(String maKhu) {
        this.maKhu = maKhu;
    }

    public String getMaVT() {
        return maVT;
    }

    public void setMaVT(String maVT) {
        this.maVT = maVT;
    }

    public String getTenVT() {
        return tenVT;
    }

    public void setTenVT(String tenVT) {
        this.tenVT = tenVT;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
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


    public String getNgayTaoAsString() {
        if (ngayTao != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.format(ngayTao);
        } else {
            return "";
        }
    }

    public String getNgaySuaDoiAsString() {
        if (ngaySuaDoi != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.format(ngaySuaDoi);
        } else {
            return "";
        }
    }

}
