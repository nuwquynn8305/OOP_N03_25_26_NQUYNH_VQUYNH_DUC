package com.WebXemPhim.WebXemPhim.DTO;

import java.util.Date;
import java.util.List;

public class DiaDiemAndGioChieu {
    private int idDiaDiem;

    private String diaDiem;
    private  List<GioChieuDTO> listGioChieu;

    public DiaDiemAndGioChieu(int idDiaDiem, String diaDiem, List<GioChieuDTO> listGioChieu) {
        this.idDiaDiem = idDiaDiem;
        this.diaDiem = diaDiem;
        this.listGioChieu = listGioChieu;
    }

    public DiaDiemAndGioChieu() {
    }

    public int getIdDiaDiem() {
        return idDiaDiem;
    }

    public void setIdDiaDiem(int idDiaDiem) {
        this.idDiaDiem = idDiaDiem;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public List<GioChieuDTO> getListGioChieu() {
        return listGioChieu;
    }

    public void setListGioChieu(List<GioChieuDTO> listGioChieu) {
        this.listGioChieu = listGioChieu;
    }
}
