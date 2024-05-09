package com.quan_ly_ktx.Entity.PHONG;

import java.sql.Date;

public class Phong{
	private String maSo;
	private String maKhu;
	private String maPhong;
    private String tinhTrang;
    private int sucChua;
    private String khuKTX;
    private int soLuong;
    private String maLoaiPhong;
    private Date ngayTao;
    private Date ngaySuaDoi;
    private String nguoiSuaDoiCuoi;
    private String tenBangDuocChon;
    private String giaTriTimKiem;
    
	public String getMaKhu() {
		return maKhu;
	}
	public void setMaKhu(String maKhu) {
		this.maKhu = maKhu;
	}
	public int getSucChua() {
		return sucChua;
	}
	public void setSucChua(int sucChua) {
		this.sucChua = sucChua;
	}
	public String getTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	public String getMaSo() {
		return maSo;
	}
	public void setMaSo(String maSo) {
		this.maSo = maSo;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	public String getMaLoaiPhong() {
		return maLoaiPhong;
	}
	public void setMaLoaiPhong(String maLoaiPhong) {
		this.maLoaiPhong = maLoaiPhong;
	}
	public Date getNgaySuaDoi() {
		return ngaySuaDoi;
	}
	public void setNgaySuaDoi(Date ngaySuaDoi) {
		this.ngaySuaDoi = ngaySuaDoi;
	}
	public Date getNgayTao() {
		return ngayTao;
	}
	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}
	public String getNguoiSuaDoiCuoi() {
		return nguoiSuaDoiCuoi;
	}
	public void setNguoiSuaDoiCuoi(String nguoiSuaDoiCuoi) {
		this.nguoiSuaDoiCuoi = nguoiSuaDoiCuoi;
	}
	public String getKhuKTX() {
		return khuKTX;
	}
	public void setKhuKTX(String khuKTX) {
		this.khuKTX = khuKTX;
	}
	public String getGiaTriTimKiem() {
		return giaTriTimKiem;
	}
	public void setGiaTriTimKiem(String giaTriTimKiem) {
		this.giaTriTimKiem = giaTriTimKiem;
	}
	public String getTenBangDuocChon() {
		return tenBangDuocChon;
	}
	public void setTenBangDuocChon(String tenBangDuocChon) {
		this.tenBangDuocChon = tenBangDuocChon;
	}
}
