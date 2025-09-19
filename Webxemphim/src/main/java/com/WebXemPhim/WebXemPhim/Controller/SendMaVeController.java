package com.WebXemPhim.WebXemPhim.Controller;

import com.WebXemPhim.WebXemPhim.DTO.ThongTinVe;
import com.WebXemPhim.WebXemPhim.Entity.*;
import com.WebXemPhim.WebXemPhim.Repository.*;
import com.WebXemPhim.WebXemPhim.Service.Impl.ChoNgoiImpl;
import com.WebXemPhim.WebXemPhim.Service.Impl.VeImpl;
import com.WebXemPhim.WebXemPhim.Service.QrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.*;

@RestController
public class SendMaVeController {
    @Autowired
    private ChoNgoiRepo choNgoiRepo;
    @Autowired
    private MaVeRepo maVeRepo;
    @Autowired
    private DatVeRepository datVeRepository;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private LichSuDatVerepo lichSuDatVerepo;
    @Autowired
    private ChoNgoiImpl choNgoi;
    @Autowired
    private VeImpl veImpl;
    @Autowired
    private QrCodeService qrCodeService;
    @Autowired
    public JavaMailSender emailSender;
    public SendMaVeController(ChoNgoiRepo choNgoiRepo, MaVeRepo maVeRepo, DatVeRepository datVeRepository, UserRepo userRepo, LichSuDatVerepo lichSuDatVerepo) {
        this.choNgoiRepo = choNgoiRepo;
        this.maVeRepo = maVeRepo;
        this.datVeRepository = datVeRepository;
        this.userRepo = userRepo;
        this.lichSuDatVerepo = lichSuDatVerepo;
    }

    @PostMapping("/sendMaVe")
    public ResponseEntity<Object> sendMaVe(@RequestParam("idUser") int idUser, @RequestParam("idSuatChieu") int idSuatChieu){
        // post vào lịch sưr đặt vé
       MaVe ve = maVeRepo.getMaVe(idSuatChieu);
       if(ve != null)
       {
       Users user = userRepo.findById(idUser);
       if(user != null) {
           Date currentDate = Calendar.getInstance().getTime();
           LichSuDatVe lichSuDatVe = new LichSuDatVe();
           lichSuDatVe.setMaVe(ve);
           lichSuDatVe.setNgayMua(currentDate);
           lichSuDatVe.setUser(user);
           lichSuDatVerepo.save(lichSuDatVe);
           // set trạng thái ghế về 0
           ChoNgoi editTrangThai = choNgoiRepo.findById(ve.getDatVe().getChoNgoi().getId_cho_ngoi());
           editTrangThai.setTrangThai(0);
           choNgoiRepo.save(editTrangThai);
           // set trạng thái vé về 0
           ve.setTrangThai(0);
           maVeRepo.save(ve);
           // gui thong tin ve
           ThongTinVe thongTinVe = new ThongTinVe();
           thongTinVe.setDiaDiem(lichSuDatVe.getMaVe().getDatVe().getDiaDiem().getDia_chi());
           thongTinVe.setGioChieu(lichSuDatVe.getMaVe().getDatVe().getGioChieu().getGio_chieu());
           thongTinVe.setNgayChieu(lichSuDatVe.getMaVe().getDatVe().getNgayChieu().getNgayChieu());
           thongTinVe.setNgayMua(lichSuDatVe.getNgayMua());
           thongTinVe.setTenPhim(lichSuDatVe.getMaVe().getDatVe().getPhim().getTenPhim());
           thongTinVe.setTenGhe(lichSuDatVe.getMaVe().getDatVe().getChoNgoi().getCho_ngoi());
           thongTinVe.setTenRap(lichSuDatVe.getMaVe().getDatVe().getLoaiRap().getLoai_rap());
           thongTinVe.setMaVe(lichSuDatVe.getMaVe().getMaSoVe());
           thongTinVe.setAnhPhim(lichSuDatVe.getMaVe().getDatVe().getPhim().getAnhPhim());
           try {
               // Create a MimeMessage.
               MimeMessage message = emailSender.createMimeMessage();
               MimeMessageHelper helper = new MimeMessageHelper(message, true);

               // Set the recipient, subject, and text.
               helper.setTo(user.getGmail());
               helper.setSubject("Thông tin vé xem phim");
               helper.setText(thongTinVe.toString());

               // Attach the image
               byte[] maVeBytes = ve.getMaSoVe();
               if (maVeBytes != null && maVeBytes.length > 0) {
                   String encodedImage = Base64.getEncoder().encodeToString(maVeBytes);
                   helper.addAttachment("maVe.png", new ByteArrayResource(Base64.getDecoder().decode(encodedImage)));
               }

               // Send Message!
               this.emailSender.send(message);

               return new ResponseEntity<>(thongTinVe, HttpStatus.OK);
           } catch (MessagingException e) {
               e.printStackTrace();
               return new ResponseEntity<>("Lỗi", HttpStatus.OK);
           }

       }
       else{
           return new ResponseEntity<>("User không tồn tại", HttpStatus.NOT_FOUND);
       }
       }
       else{
           return new ResponseEntity<>("Không tìm thấy vé", HttpStatus.NOT_FOUND);
       }

    }

    @PostMapping("/addMaVe")
    public Object addVe(@RequestParam("idSuatChieu") int idSuatChieu)
    {
        String secretKey = "MaVe" + UUID.randomUUID();
        byte[] qrCode = qrCodeService.generateQrCode(secretKey, 500, 500);
        MaVe check = maVeRepo.getMaVe(idSuatChieu);
        if(check == null){
            MaVe newMaVe = new MaVe();
            newMaVe.setMaSoVe(qrCode);
            newMaVe.setTrangThai(1);
            Date currentDate = Calendar.getInstance().getTime();
            newMaVe.setCreatedAt(currentDate);
            DatVe datVe = datVeRepository.getTTSuatChieu(idSuatChieu);
            newMaVe.setDatVe(datVe);
            maVeRepo.save(newMaVe);
            return newMaVe;
        }
        else{
            return "Mã vé với suất chiếu này đã tồn tại";
        }

    }
    @GetMapping("/getMaVe/{maVeId}")
    public ResponseEntity<Map<String, String>> getMaVe(@PathVariable int maVeId) {
        Optional<MaVe> maVeOptional = maVeRepo.findById(maVeId);
        if (maVeOptional.isPresent()) {
            MaVe maVe = maVeOptional.get();
            byte[] qrCode = maVe.getMaSoVe();
            String base64QrCode = Base64.getEncoder().encodeToString(qrCode);

            Map<String, String> response = new HashMap<>();
            response.put("base64QrCode", base64QrCode);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
