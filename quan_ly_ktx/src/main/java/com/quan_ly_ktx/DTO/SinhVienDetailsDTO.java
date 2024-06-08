package com.quan_ly_ktx.DTO;

public class SinhVienDetailsDTO {
	private String maSV;
    private String ho;
    private String ten;
    private String ngaySinh;
    private String diaChi;
    private String sdt;
    private String gioiTinh;
    private String lop;
    private String maPhong;
    private int maSinhVienViPham;
    private String maQuyTac;
    private String thoiGianViPham;
    private String tenQuyTac;
    private int mucDoViPham;
    private String nguoiLapBienBan;
    
    public SinhVienDetailsDTO() {
    	super();
    }
    
    public SinhVienDetailsDTO( String maSV, String ho, String ten, String ngaySinh, String diaChi, String sdt, String gioiTinh, String lop, String maPhong, int maSinhVienViPham, String maQuyTac, String thoiGianViPham, String tenQuyTac, int mucDoViPham, String nguoiLapBienBan) {
    	this.maSV = maSV;
    	this.ho = ho;
    	this.ten = ten;
    	this.ngaySinh = ngaySinh;
    	this.diaChi = diaChi;
    	this.sdt = sdt;
    	this.gioiTinh = gioiTinh;
    	this.lop = lop;
    	this.maPhong = maPhong;
    	this.maSinhVienViPham = maSinhVienViPham;
    	this.maQuyTac = maQuyTac;
    	this.thoiGianViPham = thoiGianViPham;
    	this.tenQuyTac = tenQuyTac;
    	this.mucDoViPham = mucDoViPham;
    	this.nguoiLapBienBan = nguoiLapBienBan;
    	
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
	
	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public int getMaSVVP() {
		return maSinhVienViPham;
	}

	public void setMaSVVP(int maSinhVienViPham) {
		this.maSinhVienViPham = maSinhVienViPham;
	}

	public String getMaQuyTac() {
		return maQuyTac;
	}

	public void setMaQuyTac(String maQuyTac) {
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
    
    public String getNguoiLapBienBan() {
		return nguoiLapBienBan;
	}

	public void setNguoiLapBienBan(String nguoiLapBienBan) {
		this.nguoiLapBienBan = nguoiLapBienBan;
	}

	@Override
    public String toString() {
    	return "Mã sinh viên: " + maSV + ", Họ: " + ho + ", Tên: " + ten + ", Ngày sinh: " + ngaySinh + ", Mã sinh viên vi phạm: " + maSinhVienViPham + ", Mã quy tắc: " + maQuyTac + ", Thời gian vi phạm: " + thoiGianViPham + ", Tên quy tắc: " + tenQuyTac + ", Mức độ vi phạm: " + mucDoViPham + ", Người lập biên bản: " + nguoiLapBienBan;
    }
}
