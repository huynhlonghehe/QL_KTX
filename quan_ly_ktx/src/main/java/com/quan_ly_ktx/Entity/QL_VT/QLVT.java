package com.quan_ly_ktx.Entity.QL_VT;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUANLY_VATTU")
public class QLVT {
    @Id
    @Column(name = "MAQL")
    private String maQL;

    @Column(name = "MAVT")
    private String maVT;

    public QLVT() {
    }

    public QLVT(String maQL, String maVT) {
        this.maQL = maQL;
        this.maVT = maVT;
    }

    public String getMaQL() {
        return maQL;
    }

    public void setMaQL(String maQL) {
        this.maQL = maQL;
    }

    public String getMaVT() {
        return maVT;
    }

    public void setMaVT(String maVT) {
        this.maVT = maVT;
    }
}