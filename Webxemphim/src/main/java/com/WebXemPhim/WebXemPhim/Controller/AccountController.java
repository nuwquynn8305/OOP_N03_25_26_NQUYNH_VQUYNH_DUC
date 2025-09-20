package com.WebXemPhim.WebXemPhim.Controller;

import com.WebXemPhim.WebXemPhim.DTO.AccountDTO;
import com.WebXemPhim.WebXemPhim.DTO.Login;
import com.WebXemPhim.WebXemPhim.DTO.TTKhachHangDTO;
import com.WebXemPhim.WebXemPhim.Entity.Users;
import com.WebXemPhim.WebXemPhim.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
public class AccountController {
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Login login){
        AccountDTO users = accountService.login(login.getUsername(), login.getPassword());
        if(users != null){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("tài khoản hoặc mật khẩu không chính xác", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/createAcoount")
    public ResponseEntity<Object> register(
            @RequestParam("tenUser") String tenUser,
            @RequestParam("sdt") String sdt,
            @RequestParam("gioiTinh") int gioiTinh,
            @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam("ngaySinh") Date ngaySinh,
            @RequestParam("taiKhoan") String taiKhoan,
            @RequestParam("matKhau") String matKhau,
            @RequestParam("gmail") String gmail
    ){
        return accountService.register(tenUser, sdt, gioiTinh, ngaySinh, taiKhoan, matKhau, gmail);
    }
    @PutMapping("/updateAccount/{IDUser}")
    public ResponseEntity<Object> updateUser(@RequestParam(value = "tenUser", required = false) String tenUser,
                                             @RequestParam(value = "avatar", required = false) MultipartFile avatar,
                                             @RequestParam(value = "sdt", required = false) String sdt,
                                             @RequestParam(value = "gioiTinh", required = false) Integer gioiTinh,
                                             @RequestParam(value = "ngaySinh", required = false) Date ngaySinh,
                                             @RequestParam(value = "gmail", required = false) String gmail, @PathVariable int IDUser) {
        return accountService.updateUser(tenUser, avatar, sdt, gioiTinh, ngaySinh, gmail, IDUser);
    }
    @PutMapping("/forgotPassword")
    public ResponseEntity<Object> forgotPass(@RequestParam("gmail") String gmail, @RequestParam("newPass") String newPass){
        return accountService.forgetPass(gmail, newPass);
    }
    @GetMapping("/profile/{IDUser}")
    public ResponseEntity<Object> profile(@PathVariable int IDUser){
        return accountService.profile(IDUser);
    }
    @GetMapping("/getAllKhachHang")
    public List<TTKhachHangDTO> getAllTTKh(){
        return accountService.dskh();
    }
}