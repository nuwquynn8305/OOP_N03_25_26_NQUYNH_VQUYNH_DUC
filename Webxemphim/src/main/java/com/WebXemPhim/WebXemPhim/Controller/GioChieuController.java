package com.WebXemPhim.WebXemPhim.Controller;

import com.WebXemPhim.WebXemPhim.Entity.GioChieu;
import com.WebXemPhim.WebXemPhim.Entity.NgayChieu;
import com.WebXemPhim.WebXemPhim.Repository.GioChieuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.util.List;

@RestController
public class GioChieuController {
    private final GioChieuRepo gioChieuRepo;
    @Autowired
    public GioChieuController(GioChieuRepo gioChieuRepo) {
        this.gioChieuRepo = gioChieuRepo;
    }
    @PostMapping("/newGioChieu")
    public ResponseEntity<Object> createGioChieu(@RequestParam("gioChieu") Time giochieu)
    {
        GioChieu newGioChieu = new GioChieu();
        newGioChieu.setGio_chieu(giochieu);
        gioChieuRepo.save(newGioChieu);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
    @GetMapping("/getAllGioChieu")
    public ResponseEntity<Object> getAllGioChieu(){
        List<GioChieu>  gioChieus = gioChieuRepo.findAll();
        return new ResponseEntity<>(gioChieus , HttpStatus.OK);
    }
}
