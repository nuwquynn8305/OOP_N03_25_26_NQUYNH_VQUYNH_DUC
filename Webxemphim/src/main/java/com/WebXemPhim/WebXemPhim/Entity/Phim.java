package com.WebXemPhim.WebXemPhim.Entity;

import javax.persistence.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="phim")
public class Phim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_phim")
    private int Id;
    @Column(name ="ten_phim")
    private String tenPhim;
    @Column(name= "anh_phim")
    private String anhPhim;
    @Column(name = "the_loai")
    private String theLoai;
    @Column(name = "thoi_luong")
    private String thoiLuong;
    @Column(name= "khoi_chieu")
    private String khoiChieu;
    @Column(name="dao_dien")
    private String daoDien;
    @Column(name ="dien_vien")
    private String dienVien;
    @Column(name="ngon_ngu")
    private String ngonNgu;
    @Column(name="danh_gia")
    private String danhGia;
    @Column(name="noi_dung")
    private String noiDung;

    @Column(name = "tinh_trang")
    private int tinhTrang;
//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "Phim_NgayChieu",
//            joinColumns = @JoinColumn(name = "IdPhim"),
//            inverseJoinColumns = @JoinColumn(name = "IdNgayChieu"))
//    private List<NgayChieu> ngayChieuList;


    public Phim() {
    }

    public Phim(int id, String tenPhim, String anhPhim, String theLoai, String thoiLuong, String khoiChieu, String daoDien, String dienVien, String ngonNgu, String danhGia, String noiDung, int tinhTrang) {
        Id = id;
        this.tenPhim = tenPhim;
        this.anhPhim = anhPhim;
        this.theLoai = theLoai;
        this.thoiLuong = thoiLuong;
        this.khoiChieu = khoiChieu;
        this.daoDien = daoDien;
        this.dienVien = dienVien;
        this.ngonNgu = ngonNgu;
        this.danhGia = danhGia;
        this.noiDung = noiDung;
        this.tinhTrang = tinhTrang;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getAnhPhim() {
        return anhPhim;
    }

    public void setAnhPhim(String anhPhim) {
        this.anhPhim = anhPhim;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(String thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public String getKhoiChieu() {
        return khoiChieu;
    }

    public void setKhoiChieu(String khoiChieu) {
        this.khoiChieu = khoiChieu;
    }

    public String getDaoDien() {
        return daoDien;
    }

    public void setDaoDien(String daoDien) {
        this.daoDien = daoDien;
    }

    public String getDienVien() {
        return dienVien;
    }

    public void setDienVien(String dienVien) {
        this.dienVien = dienVien;
    }

    public String getNgonNgu() {
        return ngonNgu;
    }

    public void setNgonNgu(String ngonNgu) {
        this.ngonNgu = ngonNgu;
    }

    public String getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(String danhGia) {
        this.danhGia = danhGia;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
