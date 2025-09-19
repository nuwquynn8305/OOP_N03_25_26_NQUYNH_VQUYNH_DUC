package com.WebXemPhim.WebXemPhim.Controller;

import com.WebXemPhim.WebXemPhim.DTO.PhimDTO;
import com.WebXemPhim.WebXemPhim.Entity.Phim;
import com.WebXemPhim.WebXemPhim.Repository.PhimRepository;
import com.WebXemPhim.WebXemPhim.Service.IStorageService;
import com.WebXemPhim.WebXemPhim.Service.Impl.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Objects;
@CrossOrigin
@RestController
public class PhimController {
    @Autowired
    private PhimRepository phimRepository;
    @Autowired
    private IStorageService iStorageService;
    @Autowired
    private CloudinaryService cloudinaryService;
    public PhimController(PhimRepository phimRepository) {
        this.phimRepository = phimRepository;
    }
//    @GetMapping("/phim")
//    public List<Phim> getAllPhim(@RequestParam("idTinh") int id) {
//        return phimRepository.findByTinhAndNgayChieu(id);
//    }
    @GetMapping("/allphim")
    public List<Phim> getAll(){
        return phimRepository.findAll();
    }

    @PostMapping("/upfilm")
    public ResponseEntity<Object> upFilm(@RequestParam("tenPhim") String tenPhim, @RequestParam("anhPhim") MultipartFile file,
                                         @RequestParam("theLoai") String theLoai, @RequestParam("thoiLuong") String thoiLuong,
                                         @RequestParam("khoiChieu") String khoiChieu, @RequestParam("daoDien") String daoDien,
                                         @RequestParam("dienVien") String dienVien, @RequestParam("ngonNgu") String ngonNgu,
                                         @RequestParam("danhGia") String danhGia, @RequestParam("noiDung") String noiDung
                                        ) throws IOException
    {
        try {
            String generatedFileName = cloudinaryService.uploadImage(file);
            Phim newPhim = new Phim();
            newPhim.setTenPhim(tenPhim);
            newPhim.setAnhPhim(generatedFileName);
            newPhim.setTheLoai(theLoai);
            newPhim.setThoiLuong(thoiLuong);
            newPhim.setKhoiChieu(khoiChieu);
            newPhim.setDaoDien(daoDien);
            newPhim.setDienVien(dienVien);
            newPhim.setNgonNgu(ngonNgu);
            newPhim.setDanhGia(danhGia);
            newPhim.setNoiDung(noiDung);
            newPhim.setTinhTrang(1);
            phimRepository.save(newPhim);
            return ResponseEntity.status(HttpStatus.OK).body("upload phim thanh cong");
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi");
        }
    }
    @PutMapping("/editPhim/{IDPhim}")
    public ResponseEntity<Object> editPhim( @RequestParam(value = "tenPhim", required = false) String tenPhim,
                                            @RequestParam(value = "anhPhim", required = false) MultipartFile file,
                                            @RequestParam(value = "theLoai", required = false) String theLoai,
                                            @RequestParam(value = "thoiLuong", required = false) String thoiLuong,
                                            @RequestParam(value = "khoiChieu", required = false) String khoiChieu,
                                            @RequestParam(value = "daoDien", required = false) String daoDien,
                                            @RequestParam(value = "dienVien", required = false) String dienVien,
                                            @RequestParam(value = "ngonNgu", required = false) String ngonNgu,
                                            @RequestParam(value = "danhGia", required = false) String danhGia,
                                            @RequestParam(value = "noiDung", required = false) String noiDung,
                                            @RequestParam(value = "tinhTrang", required = false) Integer tinhTrang,
                                            @PathVariable int IDPhim){
      try{
          Phim editPhim = phimRepository.findByIdPhim(IDPhim);
          if(editPhim != null){
              if(file != null){
                  editPhim.setAnhPhim(cloudinaryService.uploadImage(file));
              }
              if(danhGia != null)
              {
                  editPhim.setDanhGia(danhGia);
              }
              if(tinhTrang != null)
              {
                  editPhim.setTinhTrang(tinhTrang);
              }
              if(tenPhim != null){
                  editPhim.setTenPhim(tenPhim);
              }
              if(theLoai != null){
                  editPhim.setTheLoai(theLoai);
              }
              if(thoiLuong != null){
                  editPhim.setThoiLuong(thoiLuong);
              }
              if(khoiChieu != null){
                  editPhim.setKhoiChieu(khoiChieu);
              }
              if(dienVien != null){
                  editPhim.setDienVien(dienVien);
              }
              if(daoDien != null){
                  editPhim.setDaoDien(daoDien);
              }
              if(ngonNgu != null){
                  editPhim.setNgonNgu(ngonNgu);
              }
              if(noiDung != null){
                  editPhim.setNoiDung(noiDung);
              }
              phimRepository.save(editPhim);
              return new ResponseEntity<>(editPhim, HttpStatus.OK);
          }else{
              return new ResponseEntity<>("Không tìm thấy phim", HttpStatus.NOT_FOUND);
          }
      }catch (IOException e)
      {
          return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
      }
    }
    @DeleteMapping("/deletePhim/{IDPhim}")
    public ResponseEntity<Object> deletePhim(@PathVariable int IDPhim){
        try{
            Phim deletePhim = phimRepository.findByIdPhim(IDPhim);
            if(deletePhim != null){
                phimRepository.delete(deletePhim);
                return new ResponseEntity<>("delete success", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Không tìm thấy phim", HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getPhimByID/{IdPhim}")
    public Phim getFilmByID(@PathVariable int IdPhim){
        return phimRepository.findByIdPhim(IdPhim);
    }
    @GetMapping("/getPhimByName")
    public List<Phim> getPhimByName(@RequestParam("name") String name){
        return phimRepository.finByName(name);
    }
    @GetMapping("/getPhimByTinhTrang/{tinhTrang}")
    public List<Phim> getPhimByTinhTrang(@PathVariable int tinhTrang){
        return phimRepository.findByTinhTrang(tinhTrang);
    }
}
