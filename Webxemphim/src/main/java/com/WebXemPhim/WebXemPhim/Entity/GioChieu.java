package com.WebXemPhim.WebXemPhim.Entity;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "gio_chieu")
public class GioChieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gio_chieu")
    private int id_gio_chieu;

    @Column(name = "gio_chieu")
    private Time gio_chieu;

    // Thêm các trường dữ liệu và getter/setter cần thiết

    public int getId_gio_chieu() {
        return id_gio_chieu;
    }

    public void setId_gio_chieu(int id_gio_chieu) {
        this.id_gio_chieu = id_gio_chieu;
    }

    public Time getGio_chieu() {
        return gio_chieu;
    }

    public void setGio_chieu(Time gio_chieu) {
        this.gio_chieu = gio_chieu;
    }
}