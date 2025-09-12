package com.WebXemPhim.WebXemPhim.Controller;

import com.WebXemPhim.WebXemPhim.Entity.NgayChieu;
import com.WebXemPhim.WebXemPhim.Repository.NgayChieuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class NgayChieuController {
    private final NgayChieuRepo ngayChieuRepo;
    @Autowired
    public NgayChieuController(NgayChieuRepo ngayChieuRepo) {
        this.ngayChieuRepo = ngayChieuRepo;
    }
    @PostMapping("/newNgayChieu")
    public ResponseEntity<Object> createNgayChieu(@RequestParam("ngayChieu") Date ngayChieu)
    {
        NgayChieu newNgayChieu = new NgayChieu();
        newNgayChieu.setNgayChieu(ngayChieu);
        ngayChieuRepo.save(newNgayChieu);
        return new ResponseEntity<>("Sucess", HttpStatus.OK);
    }
    @GetMapping("/getAllNgayChieu")
    public ResponseEntity<Object> getAllNgayChieu(){
        List<NgayChieu> ngayChieus = ngayChieuRepo.findAll();
        return new ResponseEntity<>(ngayChieus, HttpStatus.OK);
    }
}
