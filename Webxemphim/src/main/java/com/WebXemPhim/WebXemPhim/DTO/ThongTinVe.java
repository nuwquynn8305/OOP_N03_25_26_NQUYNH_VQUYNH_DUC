package com.WebXemPhim.WebXemPhim.DTO;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

public class ThongTinVe {
    private String tenPhim;
    private String diaDiem;
    private Date ngayMua;
    private Date ngayChieu;
    private Date gioChieu;
    private String tenGhe;
    private String tenRap;
    private byte[] maVe;

    private String anhPhim;

    public String getAnhPhim() {
        return anhPhim;
    }

    public void setAnhPhim(String anhPhim) {
        this.anhPhim = anhPhim;
    }

    public byte[] getMaVe() {
        return maVe;
    }

    public void setMaVe(byte[] maVe) {
        this.maVe = maVe;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public Date getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(Date ngayMua) {
        this.ngayMua = ngayMua;
    }

    public Date getNgayChieu() {
        return ngayChieu;
    }

    public void setNgayChieu(Date ngayChieu) {
        this.ngayChieu = ngayChieu;
    }

    public Date getGioChieu() {
        return gioChieu;
    }

    public void setGioChieu(Date gioChieu) {
        this.gioChieu = gioChieu;
    }

    public String getTenGhe() {
        return tenGhe;
    }

    public void setTenGhe(String tenGhe) {
        this.tenGhe = tenGhe;
    }

    public String getTenRap() {
        return tenRap;
    }

    public void setTenRap(String tenRap) {
        this.tenRap = tenRap;
    }

    @Override
    public String toString() {
        return "THÔNG TIN VÉ:" + '\n' +
                "Tên Phim: " + tenPhim + '\n' +
                "Địa Điểm: " + diaDiem + '\n' +
                "Ngày Chiếu: " + ngayChieu + '\n' +
                "Giờ Chiếu: " + gioChieu + '\n' +
                "Tên Ghế: " + tenGhe + '\n' +
                "Tên Rạp: " + tenRap + '\n' +
                "Ngày Mua: " + ngayMua + '\n' +
                "Mã QR: " +'\n';
    }
}
