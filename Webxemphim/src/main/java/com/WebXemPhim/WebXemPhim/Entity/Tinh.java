package com.WebXemPhim.WebXemPhim.Entity;

import javax.persistence.*;
@Entity
@Table(name="tinh")
public class Tinh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_tinh")
    private int Id;
    @Column(name="tinh")
    private String tenTinh;

    public Tinh() {
    }

    public Tinh(int id, String tenTinh) {
        Id = id;
        this.tenTinh = tenTinh;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenTinh() {
        return tenTinh;
    }

    public void setTenTinh(String tenTinh) {
        this.tenTinh = tenTinh;
    }
}
