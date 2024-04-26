package com.quan_ly_ktx.Entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VT_PHONG")
public class VT_PHONG {
    
    @Id
    @Column(name = "MAVT")
    private String maVT;
    
    
    // Các trường dữ liệu khác trong bảng VT_PHONG
    
    // Getter và setter cho các trường dữ liệu
}
