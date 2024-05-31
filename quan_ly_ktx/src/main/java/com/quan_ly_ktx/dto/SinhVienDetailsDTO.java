package com.quan_ly_ktx.dto;

public class SinhVienDetailsDTO {
	private String maSV;
    private String ho;
    private String ten;
    private String ngaySinh;
    private String diaChi;
    private String sdt;
    private String gioiTinh;
    private String lop;
    private int maSinhVienViPham;
    private int maQuyTac;
    private String thoiGianViPham;
    private String tenQuyTac;
    private int mucDoViPham;
    
    public SinhVienDetailsDTO() {
    	super();
    }
    
    public SinhVienDetailsDTO(String maSV, String ho, String ten, String ngayString, String diaChi, String sdt, String gioiTinh, int maSinhVienViPham, int maQuyTac, String thoiGianViPham, String tenQuyTac, int mucDoViPham) {
    	this.maSV = maSV;
    	this.ho = ho;
    	this.ten = ten;
    	this.ngaySinh = ngaySinh;
    	this.diaChi = diaChi;
    	this.sdt = sdt;
    	this.gioiTinh = gioiTinh;
    	this.lop = lop;
    	this.maSinhVienViPham = maSinhVienViPham;
    	this.maQuyTac = maQuyTac;
    	this.maQuyTac = maQuyTac;
    	this.tenQuyTac = tenQuyTac;
    	this.mucDoViPham = mucDoViPham;
    	
    }

	public String getMaSV() {
		return maSV;
	}

	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getLop() {
		return lop;
	}

	public void setLop(String lop) {
		this.lop = lop;
	}

	public int getMaSinhVienViPham() {
		return maSinhVienViPham;
	}

	public void setMaSinhVienViPham(int maSinhVienViPham) {
		this.maSinhVienViPham = maSinhVienViPham;
	}

	public int getMaQuyTac() {
		return maQuyTac;
	}

	public void setMaQuyTac(int maQuyTac) {
		this.maQuyTac = maQuyTac;
	}

	public String getThoiGianViPham() {
		return thoiGianViPham;
	}

	public void setThoiGianViPham(String thoiGianViPham) {
		this.thoiGianViPham = thoiGianViPham;
	}

	public String getTenQuyTac() {
		return tenQuyTac;
	}

	public void setTenQuyTac(String tenQuyTac) {
		this.tenQuyTac = tenQuyTac;
	}

	public int getMucDoViPham() {
		return mucDoViPham;
	}

	public void setMucDoViPham(int mucDoViPham) {
		this.mucDoViPham = mucDoViPham;
	}
    
    @Override
    public String toString() {
    	return "Mã sinh viên: " + maSV + ", Họ: " + ho + ", Tên: " + ten + ", Mã sinh viên vi phạm: " + maSinhVienViPham + ", Mã quy tắc: " + maQuyTac + ", Thời gian vi phạm: " + thoiGianViPham + ", Tên quy tắc: " + tenQuyTac + ", Mức độ vi phạm: " + mucDoViPham;
    }
}
