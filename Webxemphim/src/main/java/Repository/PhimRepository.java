package com.WebXemPhim.WebXemPhim.Repository;

import com.WebXemPhim.WebXemPhim.Entity.Phim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhimRepository extends JpaRepository<Phim, Integer> {
//    @Query(value = "SELECT * FROM phim WHERE id_phim = ?1", nativeQuery = true)
//    List<Phim> findByTinhAndNgayChieu(int idTinh);
    List<Phim> findAll();
    @Query(value = "SELECT * FROM phim WHERE id_phim = ?1", nativeQuery = true)
    Phim findByIdPhim(int ID);
    @Query(value = "SELECT * FROM phim WHERE ten_phim LIKE %?1%", nativeQuery = true)
    List<Phim> finByName(String name);
    @Query(value = "select * FROM phim where tinh_trang = ?1", nativeQuery = true)
    List<Phim> findByTinhTrang(int tinhTrang);
}