package com.WebXemPhim.WebXemPhim.Entity;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRole;
    @Column(name = "ten_role")
    private String tenRole;

    public Role() {
    }

    public Role(int idRole, String tenRole) {
        this.idRole = idRole;
        this.tenRole = tenRole;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getTenRole() {
        return tenRole;
    }

    public void setTenRole(String tenRole) {
        this.tenRole = tenRole;
    }
}
