package com.WebXemPhim.WebXemPhim.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "hoadon")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hoadon")
    private int IDHoaDn;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private Users users;
    @ManyToOne
    @JoinColumn(name = "id_suat_chieu")
    private DatVe datVe;
    @Column(name = "total_price")
    private Double totalPrice;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "trang_thai")
    private String trangThai;

    public int getIDHoaDn() {
        return IDHoaDn;
    }

    public void setIDHoaDn(int IDHoaDn) {
        this.IDHoaDn = IDHoaDn;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public DatVe getDatVe() {
        return datVe;
    }

    public void setDatVe(DatVe datVe) {
        this.datVe = datVe;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
