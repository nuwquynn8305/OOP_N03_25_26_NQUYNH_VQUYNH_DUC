package com.WebXemPhim.WebXemPhim.Entity;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ma_ve")
public class MaVe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ma_ve")
    private int idMaVe;
    @Column(name = "masove")
    private byte[] maSoVe;
    @Column(name = "trangthai")
    private int trangThai;
    @Column(name = "created_at")
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name = "id_suat_chieu")
    private DatVe datVe;

    public MaVe() {
    }

    public MaVe(int idMaVe, byte[] maSoVe, int trangThai, Date createdAt, DatVe datVe) {
        this.idMaVe = idMaVe;
        this.maSoVe = maSoVe;
        this.trangThai = trangThai;
        this.createdAt = createdAt;
        this.datVe = datVe;
    }

    public void setMaSoVe(byte[] maSoVe) {
        this.maSoVe = maSoVe;
    }

    public byte[] getMaSoVe() {
        return maSoVe;
    }

    public DatVe getDatVe() {
        return datVe;
    }

    public void setDatVe(DatVe datVe) {
        this.datVe = datVe;
    }

    public int getIdMaVe() {
        return idMaVe;
    }

    public void setIdMaVe(int idMaVe) {
        this.idMaVe = idMaVe;
    }



    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
