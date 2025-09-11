package com.WebXemPhim.WebXemPhim.Entity;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="ngay_chieu")
public class NgayChieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_ngay_chieu")
    private int Id;
    @Column(name="ngay_chieu")
    private Date ngayChieu;



    public NgayChieu() {
    }

    public NgayChieu(int id, Date ngayChieu) {
        Id = id;
        this.ngayChieu = ngayChieu;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Date getNgayChieu() {
        return ngayChieu;
    }

    public void setNgayChieu(Date ngayChieu) {
        this.ngayChieu = ngayChieu;
    }
}
