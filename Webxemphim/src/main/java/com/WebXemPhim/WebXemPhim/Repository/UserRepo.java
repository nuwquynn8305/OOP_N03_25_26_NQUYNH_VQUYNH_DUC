package com.WebXemPhim.WebXemPhim.Repository;

import com.WebXemPhim.WebXemPhim.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
    Users findById(int idUser);
    @Query(value = "select * from users where ten_user = ?1", nativeQuery = true)
    Users findByUserTen(String userName);
    @Query(value = "select * from users where gmail = ?1", nativeQuery = true)
    Users findByGmail(String gmail);
    @Query(value = "select * from users where tai_khoan = ?1 and mat_khau = ?2", nativeQuery = true)
    Users login(String userName, String passWord);

}
