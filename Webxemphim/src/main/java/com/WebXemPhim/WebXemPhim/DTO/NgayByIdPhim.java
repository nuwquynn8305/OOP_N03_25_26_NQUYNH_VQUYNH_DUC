package com.WebXemPhim.WebXemPhim.DTO;
import java.util.Date;

public class NgayByIdPhim {
    private int id;
    private Date ThoiGian;

    public Date getThoiGian() {
        return ThoiGian;
    }

    public void setThoiGian(Date thoiGian) {
        ThoiGian = thoiGian;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
