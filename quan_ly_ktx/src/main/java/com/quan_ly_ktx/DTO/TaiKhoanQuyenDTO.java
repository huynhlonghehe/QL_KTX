package com.quan_ly_ktx.DTO;

public class TaiKhoanQuyenDTO {
    private String tenDangNhap;
    private String matKhau;
    private String tinhTrang;
    private String tenQuyen;
    public TaiKhoanQuyenDTO() {
    	super();
    }
    
    public TaiKhoanQuyenDTO(String tenDangNhap, String matKhau, String tinhTrang, String tenQuyen) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.tinhTrang = tinhTrang;
        this.tenQuyen = tenQuyen;
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
	public String getTenQuyen() {
		return tenQuyen;
	}
	public void setTenQuyen(String tenQuyen) {
		this.tenQuyen = tenQuyen;
	}

    
}

