package com.quan_ly_ktx.Entity.TaiKhoan;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TAIKHOAN")
public class TaiKhoan {
	@Id
	@Column(name = "USERNAME")
	private String tenDangNhap;
	@Column(name = "PASSWORD")
	private String matKhau;
	@Column(name = "TINHTRANG")
	private String tinhTrang;
	@Column(name = "QUYEN")
	private String quyen;
	public TaiKhoan() {
		super();
	}
	public TaiKhoan(String tenDangNhap, String matKhau, String tinhTrang, String quyen) {
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.tinhTrang = tinhTrang;
		this.quyen = quyen;
	}
	
	public String getTenDangNhap() {
		return tenDangNhap;
	}
	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public String getTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	public String getQuyen() {
		return quyen;
	}
	public void setQuyen(String quyen) {
		this.quyen = quyen;
	}
	@Override
	public String toString() {
	    return "TaiKhoan [ username=" + tenDangNhap + ", password=" + matKhau + ", tinhTrang=" + tinhTrang + ", quyen = " + quyen + " ]";
	}
}
