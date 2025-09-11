package com.WebXemPhim.WebXemPhim.Repository;

import com.WebXemPhim.WebXemPhim.Entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    @Query(value = "select * from users_roles where id_user = ?1", nativeQuery = true)
    UserRole findUserole(int idUser);
    @Query(value = "select * from users_roles where id_role = 1", nativeQuery = true)
    List<UserRole> findAllKH();
}