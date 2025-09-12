package com.WebXemPhim.WebXemPhim.Controller;

import com.WebXemPhim.WebXemPhim.Entity.ChoNgoi;
import com.WebXemPhim.WebXemPhim.Repository.ChoNgoiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChoNgoiController {
    @Autowired
    private ChoNgoiRepo choNgoiRepo;
    @PostMapping("/newChoNgoi")
    public ResponseEntity<Object> createChoNgoi(@RequestParam("choNgoi") String choNgoi){
        ChoNgoi newChoNgoi = new ChoNgoi();
        newChoNgoi.setCho_ngoi(choNgoi);
        newChoNgoi.setTrangThai(0);
        choNgoiRepo.save(newChoNgoi);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}
