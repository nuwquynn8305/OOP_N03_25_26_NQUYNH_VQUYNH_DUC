package com.WebXemPhim.WebXemPhim.DTO;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

public class GioChieuDTO {
    private int id;
    private Time time;

    public GioChieuDTO(int id, Time time) {
        this.id = id;
        this.time = time;
    }

    public GioChieuDTO(Time time) {
        this.time = time;
    }

    public GioChieuDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GioChieuDTO that = (GioChieuDTO) o;
        return Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time);
    }

}
