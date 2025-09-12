package com.WebXemPhim.WebXemPhim.Repository;

import com.WebXemPhim.WebXemPhim.Entity.MaVe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface MaVeRepo extends JpaRepository<MaVe, Integer> {
   @Query(value = "select * from ma_ve where id_suat_chieu = ?1 and trangthai = 1", nativeQuery = true)
    MaVe getMaVe(int idSuatChieu);
   Optional<MaVe> findById(int idMaVe);

}
