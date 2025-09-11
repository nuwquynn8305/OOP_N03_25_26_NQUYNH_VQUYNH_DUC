package com.WebXemPhim.WebXemPhim.Entity;



import javax.persistence.*;

@Entity
@Table(name="suat_chieu")
public class DatVe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_suat_chieu")
    private Long id_dat_ve;

    @ManyToOne
    @JoinColumn(name = "id_phim")
    private Phim phim;

    @ManyToOne
    @JoinColumn(name = "id_ngay_chieu")
    private NgayChieu ngayChieu;

    @ManyToOne
    @JoinColumn(name = "id_tinh")
    private Tinh tinh;

    @ManyToOne
    @JoinColumn(name = "id_dia_diem")
    private DiaDiem diaDiem;

    @ManyToOne
    @JoinColumn(name = "id_gio_chieu")
    private GioChieu gioChieu;

    @ManyToOne
    @JoinColumn(name = "id_loai_rap")
    private LoaiRap loaiRap;

    @ManyToOne
    @JoinColumn(name = "id_cho_ngoi")
    private ChoNgoi choNgoi;


    // Getters và setters

    public Long getId_dat_ve() {
        return id_dat_ve;
    }

    public void setId_dat_ve(Long id_dat_ve) {
        this.id_dat_ve = id_dat_ve;
    }

    public Phim getPhim() {
        return phim;
    }

    public void setPhim(Phim phim) {
        this.phim = phim;
    }

    public NgayChieu getNgayChieu() {
        return ngayChieu;
    }

    public void setNgayChieu(NgayChieu ngayChieu) {
        this.ngayChieu = ngayChieu;
    }

    public Tinh getTinh() {
        return tinh;
    }

    public void setTinh(Tinh tinh) {
        this.tinh = tinh;
    }

    public DiaDiem getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(DiaDiem diaDiem) {
        this.diaDiem = diaDiem;
    }

    public GioChieu getGioChieu() {
        return gioChieu;
    }

    public void setGioChieu(GioChieu gioChieu) {
        this.gioChieu = gioChieu;
    }

    public LoaiRap getLoaiRap() {
        return loaiRap;
    }

    public void setLoaiRap(LoaiRap loaiRap) {
        this.loaiRap = loaiRap;
    }

    public ChoNgoi getChoNgoi() {
        return choNgoi;
    }

    public void setChoNgoi(ChoNgoi choNgoi) {
        this.choNgoi = choNgoi;
    }

}
