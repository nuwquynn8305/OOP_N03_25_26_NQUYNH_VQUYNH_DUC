package com.WebXemPhim.WebXemPhim.DTO;

public class ChoNgoiDTO {
    private int id;

    private String choNgoi;
    private int trangThai;
    private Float price;

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChoNgoi() {
        return choNgoi;
    }

    public void setChoNgoi(String choNgoi) {
        this.choNgoi = choNgoi;
    }
}
