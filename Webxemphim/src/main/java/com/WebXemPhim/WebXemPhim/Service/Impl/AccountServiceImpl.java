package com.WebXemPhim.WebXemPhim.Service.Impl;

import com.WebXemPhim.WebXemPhim.DTO.AccountDTO;
import com.WebXemPhim.WebXemPhim.DTO.Profile;
import com.WebXemPhim.WebXemPhim.DTO.TTKhachHangDTO;
import com.WebXemPhim.WebXemPhim.Entity.*;
import com.WebXemPhim.WebXemPhim.Repository.*;
import com.WebXemPhim.WebXemPhim.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private final UserRepo userRepo;
    private final UserRoleRepository UserRoleRepository;
    private final RoleRepo roleRepository;
    private final CloudinaryService cloudinaryService;
    private final LichSuDatVerepo lichSuDatVerepo;
    private final HoaDonRepo hoaDonRepo;

    @Autowired
    public AccountServiceImpl(UserRepo userRepo, com.WebXemPhim.WebXemPhim.Repository.UserRoleRepository userRoleRepository, RoleRepo roleRepository, CloudinaryService cloudinaryService, LichSuDatVerepo lichSuDatVerepo, HoaDonRepo hoaDonRepo) {
        this.userRepo = userRepo;
        UserRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
        this.cloudinaryService = cloudinaryService;
        this.lichSuDatVerepo = lichSuDatVerepo;
        this.hoaDonRepo = hoaDonRepo;
    }

    @Override
    public AccountDTO login(String username, String password) {
        Users user = userRepo.login(username, password);
        UserRole userRole = UserRoleRepository.findUserole(user.getIdUser());
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setRole(userRole.getRole().getTenRole());
        accountDTO.setTenUser(user.getTenUser());
        accountDTO.setEmail(user.getGmail());
        accountDTO.setUserID(user.getIdUser());
        return accountDTO;
    }

    @Override
    public ResponseEntity<Object> register(String tenUser, String sdt, int gioiTinh, Date ngaySinh, String taiKhoan, String matKhau, String gmail) {
       try {
           Users newUser = new Users();
           newUser.setTenUser(tenUser);
           newUser.setAvatar(null);
           newUser.setSdt(sdt);
           newUser.setGioiTinh(gioiTinh);
           newUser.setNgaySinh(ngaySinh);
           newUser.setTaiKhoan(taiKhoan);
           newUser.setMatKhau(matKhau);
           newUser.setGmail(gmail);
           userRepo.save(newUser);
           //set role cho user
           Role role = roleRepository.findById(1);
           UserRole userRole = new UserRole();
           userRole.setUser(newUser);
           userRole.setRole(role);
           UserRoleRepository.save(userRole);

           return new ResponseEntity<>(newUser, HttpStatus.OK);

       }catch(Exception e){
           return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
       }
    }



    @Override
    public ResponseEntity<Object> updateUser(String tenUser, MultipartFile avatar, String sdt, Integer gioiTinh, Date ngaySinh, String gmail, int IDUser) {
        try{
            Users editUser = userRepo.findById(IDUser);
            if(editUser != null){
                if(tenUser != null){
                    editUser.setTenUser(tenUser);
                }
                if(avatar != null){
                    editUser.setAvatar(cloudinaryService.uploadImage(avatar));
                }
                if(sdt != null){
                    editUser.setSdt(sdt);
                }
                if(gioiTinh != null){
                    editUser.setGioiTinh(gioiTinh);
                }
                if(ngaySinh != null){
                    editUser.setNgaySinh(ngaySinh);
                }
                if(gmail != null){
                    editUser.setGmail(gmail);
                }
                userRepo.save(editUser);
                return new ResponseEntity<>(editUser, HttpStatus.OK);
            }
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }catch (IOException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> forgetPass(String gmail, String newPass) {
        Users forgot = userRepo.findByGmail(gmail);
        if(forgot != null){
            forgot.setMatKhau(newPass);
            userRepo.save(forgot);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> profile(int IDUser) {
       try{
           Users users = userRepo.findById(IDUser);
           List<LichSuDatVe> lichSuDatVe = lichSuDatVerepo.findByIdUser(IDUser);
           List<HoaDon> hoaDons = hoaDonRepo.findByIdUser(IDUser);
           Profile profile = new Profile();
           profile.setName(users.getTenUser());
           profile.setAvatar(users.getAvatar());
           //
           Double totalPrice = 0.0;
           for (HoaDon hoaDon : hoaDons) {
               totalPrice += hoaDon.getTotalPrice();
           }
           profile.setTotalPrice(totalPrice);
           profile.setTotalTicket(lichSuDatVe.size());
           profile.setGioiTinh(users.getGioiTinh());
           profile.setEmail(users.getGmail());
           profile.setSdt(users.getSdt());
           profile.setNgaySinh(users.getNgaySinh());
           return new ResponseEntity<>(profile, HttpStatus.OK);
       }catch (Exception e)
       {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
       }

    }

    @Override
    public List<TTKhachHangDTO> dskh() {
        List<UserRole> userRoleList = UserRoleRepository.findAllKH();
        List<TTKhachHangDTO> ttKhachHangDTOS = userRoleList.stream().map(ttkh ->{
            TTKhachHangDTO ttKhachHangDTO = new TTKhachHangDTO();
            ttKhachHangDTO.setName(ttkh.getUser().getTenUser());
            ttKhachHangDTO.setAvatar(ttkh.getUser().getAvatar());
            ttKhachHangDTO.setGmail(ttkh.getUser().getGmail());
            ttKhachHangDTO.setSdt(ttkh.getUser().getSdt());
            ttKhachHangDTO.setGioiTinh(ttkh.getUser().getGioiTinh());
            ttKhachHangDTO.setNgaySinh(ttkh.getUser().getNgaySinh());
            return ttKhachHangDTO;
        }).collect(Collectors.toList());
        return ttKhachHangDTOS;
    }
}
