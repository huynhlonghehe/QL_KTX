package com.quan_ly_ktx.Entity.SinhVien;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUYTAC")
public class QuyTac {
	@Id
	@Column(name = "MAQUYTAC")
	private String maQuyTac;
	@Column(name = "TENQuyTac")
	private String tenQuyTac;
	@Column(name = "MUCDOVIPHAM")
	private int mucDoViPham;
	
	public QuyTac() {
		super();
	}
	
	public QuyTac(String maQuyTac, String tenQuyTac, int mucDoViPham) {
		this.maQuyTac = maQuyTac;
		this.tenQuyTac = tenQuyTac;
		this.mucDoViPham = mucDoViPham;
	}

	
	
	public String getMaQuyTac() {
		return maQuyTac;
	}

	public void setMaQuyTac(String maQuyTac) {
		this.maQuyTac = maQuyTac;
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
		return "Mã quy tắc: " + maQuyTac + ", Tên quy tắc: " + tenQuyTac + ", Mức độ vi phạm: " + mucDoViPham;
	}
	
}
