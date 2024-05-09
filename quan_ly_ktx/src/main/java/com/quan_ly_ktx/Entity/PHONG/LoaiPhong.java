package com.quan_ly_ktx.Entity.PHONG;

import java.math.BigDecimal;

public class LoaiPhong {
    private String maLoaiPhong;
    private String dichVu;
    private int mucDienHoTroKWh;
    private BigDecimal giaLoaiPhong;

    public String getMaLoaiPhong() {
        return maLoaiPhong;
    }

    public void setMaLoaiPhong(String maLoaiPhong) {
        this.maLoaiPhong = maLoaiPhong;
    }

    public String getDichVu() {
        return dichVu;
    }

    public void setDichVu(String dichVu) {
        this.dichVu = dichVu;
    }

    public int getMucDienHoTroKWh() {
        return mucDienHoTroKWh;
    }

    public void setMucDienHoTroKWh(int mucDienHoTroKWh) {
        this.mucDienHoTroKWh = mucDienHoTroKWh;
    }

    public BigDecimal getGiaLoaiPhong() {
        return giaLoaiPhong;
    }

    public void setGiaLoaiPhong(BigDecimal giaLoaiPhong) {
        this.giaLoaiPhong = giaLoaiPhong;
    }
}
