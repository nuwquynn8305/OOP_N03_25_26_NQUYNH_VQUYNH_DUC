package com.WebXemPhim.WebXemPhim.Controller;

import com.WebXemPhim.WebXemPhim.DTO.ThongTinVe;
import com.WebXemPhim.WebXemPhim.Entity.LichSuDatVe;
import com.WebXemPhim.WebXemPhim.Entity.MaVe;
import com.WebXemPhim.WebXemPhim.Repository.LichSuDatVerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class LichSuDatVeController {
    @Autowired
    private LichSuDatVerepo lichSuDatVerepo;
    @GetMapping("/lsdv/{IDUser}")
    public ResponseEntity<Object> getLsdv(@PathVariable int IDUser)
    {
        List<LichSuDatVe> datVes = lichSuDatVerepo.findByIdUser(IDUser);
        List<ThongTinVe> thongTinVes = datVes.stream().map(datVe ->{
            ThongTinVe thongTinVe = new ThongTinVe();
            thongTinVe.setTenPhim(datVe.getMaVe().getDatVe().getPhim().getTenPhim());
            thongTinVe.setMaVe(datVe.getMaVe().getMaSoVe());
            thongTinVe.setNgayMua(datVe.getNgayMua());
            thongTinVe.setNgayChieu(datVe.getMaVe().getDatVe().getNgayChieu().getNgayChieu());
            thongTinVe.setTenRap(datVe.getMaVe().getDatVe().getLoaiRap().getLoai_rap());
            thongTinVe.setAnhPhim(datVe.getMaVe().getDatVe().getPhim().getAnhPhim());
            thongTinVe.setDiaDiem(datVe.getMaVe().getDatVe().getDiaDiem().getDia_chi());
            thongTinVe.setTenGhe(datVe.getMaVe().getDatVe().getChoNgoi().getCho_ngoi());
            thongTinVe.setGioChieu(datVe.getMaVe().getDatVe().getGioChieu().getGio_chieu());
            return thongTinVe;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(thongTinVes, HttpStatus.OK);
    }
}
