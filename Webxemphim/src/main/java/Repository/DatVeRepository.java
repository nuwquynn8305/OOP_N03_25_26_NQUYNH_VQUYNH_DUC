package com.WebXemPhim.WebXemPhim.Repository;

import com.WebXemPhim.WebXemPhim.Entity.DatVe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface DatVeRepository extends JpaRepository<DatVe, Integer> {
    @Query(value = "SELECT DISTINCT * FROM suat_chieu WHERE id_tinh = ?1", nativeQuery = true)
    List<DatVe> findByIdTinh(int id);

    @Query(value = "SELECT DISTINCT * FROM suat_chieu WHERE id_ngay_chieu = ?1 AND id_phim = ?2", nativeQuery = true)
    List<DatVe> getDiaDiemByTinhAndPhim(int idNgayChieu, int idPhim);
    @Query(value = "SELECT DISTINCT * FROM suat_chieu WHERE id_phim = ?1", nativeQuery = true)
    List<DatVe> findNgayByIdPhim(int id);

   @Query(value = "select DISTINCT * from suat_chieu where id_phim = ?1 and id_ngay_chieu = ?2 and id_tinh = ?3", nativeQuery = true)
    List<DatVe> getLoaiRap(int idPhim, int idNgayChieu, int idTinh);

   @Query(value = "select DISTINCT * from suat_chieu where id_phim = ?1 and id_ngay_chieu = ?2 and id_tinh = ?3 " +
           "and id_loai_rap = ?4", nativeQuery = true)
    List<DatVe> getDiaDiemAndGioChieu(int idPhim, int idNgayChieu, int idTinh, int idLoaiRap);

    @Query(value = "select * from suat_chieu where id_phim = ?1 and id_ngay_chieu = ?2 and id_tinh = ?3 " +
            "and id_loai_rap = ?4 and id_dia_diem = ?5 and id_gio_chieu = ?6", nativeQuery = true)
    List<DatVe> getChoNgoi(int idPhim, int idNgayChieu, int idTinh, int idLoaiRap, int idDiaDiem, int idGioChieu);
    @Query(value = "select DISTINCT * from suat_chieu where id_phim = ?1 and id_ngay_chieu = ?2 and id_tinh = ?3 " +
            "and id_loai_rap = ?4 AND id_dia_diem = ?5 and id_gio_chieu = ?6 and id_cho_ngoi = ?7", nativeQuery = true)
    List<Integer> getIDDatCho(int idPhim, int idNgayChieu, int idTinh, int idLoaiRap, int idDiaDiem, int idGioChieu, int idChoNgoi);
    @Query(value = "select * from suat_chieu where id_suat_chieu = ?1", nativeQuery = true)
    DatVe getTTSuatChieu(int idSuatChieu);

}
