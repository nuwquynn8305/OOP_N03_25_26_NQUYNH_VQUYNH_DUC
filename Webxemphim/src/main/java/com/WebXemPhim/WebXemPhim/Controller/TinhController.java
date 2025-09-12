package com.WebXemPhim.WebXemPhim.Controller;

import com.WebXemPhim.WebXemPhim.Entity.Tinh;
import com.WebXemPhim.WebXemPhim.Repository.TinhRepo;
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
public class TinhController {
    private final TinhRepo tinhRepo;
    @Autowired
    public TinhController(TinhRepo tinhRepo) {
        this.tinhRepo = tinhRepo;
    }
    @PostMapping("/newTinh")
    public ResponseEntity<Object> createTinh(@RequestParam("Tinh") String tinh)
    {
        Tinh newTinh = new Tinh();
        newTinh.setTenTinh(tinh);
        tinhRepo.save(newTinh);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
    @GetMapping("/getAllTinh")
    public ResponseEntity<Object> getAlltinh(){
        List<Tinh> tinhs = tinhRepo.findAll();
        return new ResponseEntity<>(tinhs, HttpStatus.OK);
    }
}
