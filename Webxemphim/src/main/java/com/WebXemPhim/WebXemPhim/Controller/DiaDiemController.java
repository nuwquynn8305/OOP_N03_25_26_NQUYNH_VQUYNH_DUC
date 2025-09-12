package com.WebXemPhim.WebXemPhim.Controller;

import com.WebXemPhim.WebXemPhim.Entity.DiaDiem;
import com.WebXemPhim.WebXemPhim.Repository.DiaDiemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DiaDiemController {
    private final DiaDiemRepo diaDiemRepo;
    @Autowired
    public DiaDiemController(DiaDiemRepo diaDiemRepo) {
        this.diaDiemRepo = diaDiemRepo;
    }
    @PostMapping("/newDiaDiem")
    public ResponseEntity<Object> createDiaDiem(@RequestParam("diaDiem") String diaDiem)
    {
        DiaDiem newDiaDiem = new DiaDiem();
        newDiaDiem.setDia_chi(diaDiem);
        diaDiemRepo.save(newDiaDiem);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
    @GetMapping("/getAllDiaDiem")
    public ResponseEntity<Object> getAllDiaDiem(){
        List<DiaDiem> diaDiems = diaDiemRepo.findAll();
        return new ResponseEntity<>(diaDiems, HttpStatus.OK);
    }
}
