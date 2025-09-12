package com.WebXemPhim.WebXemPhim.Entity;

import javax.persistence.*;

@Entity
@Table(name = "cho_ngoi")
public class ChoNgoi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cho_ngoi")
    private int id_cho_ngoi;

    @Column(name = "cho_ngoi")
    private String cho_ngoi;
    @Column(name= "trang_thai")
    private int trangThai;

    // Thêm các trường dữ liệu và getter/setter cần thiết

    public int getId_cho_ngoi() {
        return id_cho_ngoi;
    }

    public void setId_cho_ngoi(int id_cho_ngoi) {
        this.id_cho_ngoi = id_cho_ngoi;
    }

    public String getCho_ngoi() {
        return cho_ngoi;
    }

    public void setCho_ngoi(String cho_ngoi) {
        this.cho_ngoi = cho_ngoi;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}