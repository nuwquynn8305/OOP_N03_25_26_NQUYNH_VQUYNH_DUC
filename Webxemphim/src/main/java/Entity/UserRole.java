package com.WebXemPhim.WebXemPhim.Entity;

import javax.persistence.*;

@Entity
@Table(name = "users_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_users_roles")
    private int idUserRole;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private Users user;
    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    public int getIdUserRole() {
        return idUserRole;
    }

    public void setIdUserRole(int idUserRole) {
        this.idUserRole = idUserRole;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
