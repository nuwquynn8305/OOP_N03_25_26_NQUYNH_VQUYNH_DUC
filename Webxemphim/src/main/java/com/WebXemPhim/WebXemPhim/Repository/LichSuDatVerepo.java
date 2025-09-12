package com.WebXemPhim.WebXemPhim.Repository;

import com.WebXemPhim.WebXemPhim.Entity.LichSuDatVe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LichSuDatVerepo extends JpaRepository<LichSuDatVe, Integer> {
    @Query(value = "select * from lich_su_dat_ve where id_user = ?1",nativeQuery = true)
    List<LichSuDatVe> findByIdUser(int IDUser);
}
