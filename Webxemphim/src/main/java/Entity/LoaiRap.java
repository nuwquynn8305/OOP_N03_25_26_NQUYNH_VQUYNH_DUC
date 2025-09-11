package com.WebXemPhim.WebXemPhim.Entity;

import javax.persistence.*;

@Entity
@Table(name = "loai_rap")
public class LoaiRap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_loai_rap")
    private int id_loai_rap;

    @Column(name = "loai_rap")
    private String loai_rap;
    @Column(name="gia_tien")
    private Float giaTien;


    // Thêm các trường dữ liệu và getter/setter cần thiết
    public Float getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(Float giaTien) {
        this.giaTien = giaTien;
    }

    public int getId_loai_rap() {
        return id_loai_rap;
    }

    public void setId_loai_rap(int id_loai_rap) {
        this.id_loai_rap = id_loai_rap;
    }

    public String getLoai_rap() {
        return loai_rap;
    }

    public void setLoai_rap(String loai_rap) {
        this.loai_rap = loai_rap;
    }
}