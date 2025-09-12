package com.WebXemPhim.WebXemPhim.Controller;

import com.WebXemPhim.WebXemPhim.Entity.LoaiRap;
import com.WebXemPhim.WebXemPhim.Repository.LoaiRapRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoaiRapController {
    private final LoaiRapRepo loaiRapRepo;
    @Autowired
    public LoaiRapController(LoaiRapRepo loaiRapRepo) {
        this.loaiRapRepo = loaiRapRepo;
    }
    @PostMapping("/newLoaiRap")
    public ResponseEntity<Object> createLoaiRap(@RequestParam("loaiRap") String loairap,
                                                @RequestParam("price") Float price)
    {
        LoaiRap newLoaiRap = new LoaiRap();
        newLoaiRap.setLoai_rap(loairap);
        newLoaiRap.setGiaTien(price);
        loaiRapRepo.save(newLoaiRap);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
    @GetMapping("/getAllLoaiRap")
    public ResponseEntity<Object> getAllLoaiRap(){
        List<LoaiRap> loaiRaps = loaiRapRepo.findAll();
        return new ResponseEntity<>(loaiRaps, HttpStatus.OK);
    }
}
