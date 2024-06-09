package com.quan_ly_ktx.Entity.HopDong;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HOPDONG")
public class HopDong {
	@Id
	@Column(name = "MAHD")
	private String maHD;
	@Column(name = "NGAYTAO")
	private String ngayTao;
	@Column(name = "NGAYHETHAN")
	private String ngayHetHan;
	@Column(name = "SOTIEN")
	private long soTien;
	@Column(name = "NAMHOC")
	private String namHoc;
	@Column(name = "HOCKY")
	private String hocKy;
	@Column(name = "MAPHONG")
	private String maPhong;
	@Column(name = "MASV")
	private String maSV;
<<<<<<< HEAD
	@Column(name = "MAQL")
	private String maQL;
=======
	@Column(name = "NGAYSUADOI")
	private String ngaySuaDoi;
	@Column(name = "NGUOISUADOICUOI")
	private String nguoiSuaDoiCuoi;
>>>>>>> 17e1134b9aa9bdc953f04b1a95e57e6ab57c8d50
	
	public HopDong() {
		super();
	}
	
<<<<<<< HEAD
	public HopDong(String maHD, String ngayTao, String ngayHetHan , long soTien, String namHoc, String hocKy, long tienNo, String maPhong, String maSV, String maQL){
=======
	public HopDong(String maHD, String ngayTao, String ngayHetHan , long soTien, String namHoc, String hocKy, long tienNo, String maPhong, String maSV, String ngaySuaDoi, String nguoiSuaDoiCuoi){
>>>>>>> 17e1134b9aa9bdc953f04b1a95e57e6ab57c8d50
		this.maHD = maHD;
		this.ngayTao = ngayTao;
		this.ngayHetHan = ngayHetHan;
		this.soTien = soTien;
		this.namHoc = namHoc;
		this.hocKy = hocKy;
		this.maPhong = maPhong;
		this.maSV = maSV;
<<<<<<< HEAD
		this.maQL = maQL;
=======
		this.ngaySuaDoi = ngaySuaDoi;
		this.nguoiSuaDoiCuoi = nguoiSuaDoiCuoi;
>>>>>>> 17e1134b9aa9bdc953f04b1a95e57e6ab57c8d50
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public String getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(String ngayTao) {
		this.ngayTao = ngayTao;
	}
	
	public String getNgayHetHan() {
		return ngayHetHan;
	}

	public void setNgayHetHan(String ngayHetHan) {
		this.ngayHetHan = ngayHetHan;
	}

	public long getSoTien() {
		return soTien;
	}

	public void setSoTien(long soTien) {
		this.soTien = soTien;
	}

	public String getNamHoc() {
		return namHoc;
	}

	public void setNamHoc(String namHoc) {
		this.namHoc = namHoc;
	}

	public String getHocKy() {
		return hocKy;
	}

	public void setHocKy(String hocKy) {
		this.hocKy = hocKy;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public String getMaSV() {
		return maSV;
	}

	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}
<<<<<<< HEAD

	public String getMaQL() {
		return maQL;
	}

	public void setMaQL(String maQL) {
		this.maQL = maQL;
	}
=======
	public String getNgaySuaDoi() {
		return ngaySuaDoi;
	}

	public void setNgaySuaDoi(String ngaySuaDoi) {
		this.ngaySuaDoi = ngaySuaDoi;
	}

	public String getNguoiSuaDoiCuoi() {
		return nguoiSuaDoiCuoi;
	}

	public void setNguoiSuaDoiCuoi(String nguoiSuaDoiCuoi) {
		this.nguoiSuaDoiCuoi = nguoiSuaDoiCuoi;
	}
	
>>>>>>> 17e1134b9aa9bdc953f04b1a95e57e6ab57c8d50
	
	@Override
	public String toString() {
		return "maHD: " + maHD + ", ngayTao: " + ngayTao + ", NgayHetHan: " + ngayHetHan + ", soTien: " + soTien + ", namHoc: " + namHoc+ ", hocKy: " + hocKy + ", maSV: " + maSV;
	}
}
