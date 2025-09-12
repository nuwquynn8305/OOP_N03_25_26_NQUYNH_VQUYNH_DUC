package com.WebXemPhim.WebXemPhim.Controller;

import com.WebXemPhim.WebXemPhim.DTO.ThongTinVe;
import com.WebXemPhim.WebXemPhim.Entity.DatVe;
import com.WebXemPhim.WebXemPhim.Entity.MaVe;
import com.WebXemPhim.WebXemPhim.Entity.Users;
import com.WebXemPhim.WebXemPhim.Repository.DatVeRepository;
import com.WebXemPhim.WebXemPhim.Repository.MaVeRepo;
import com.WebXemPhim.WebXemPhim.Repository.UserRepo;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Base64;

@RestController
public class MailController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    public JavaMailSender emailSender;
    @Autowired
    private DatVeRepository datVeRepository;
    @Autowired
    private MaVeRepo maVeRepo;

    public MailController(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @GetMapping("/sendVeEmail")
    public String sendVeByEmail(@RequestParam("idUser") int idUser, @RequestParam("idSuatChieu") int idSuatChieu) {
        Users users = userRepo.findById(idUser);
        DatVe thongtinSuatChieu = datVeRepository.getTTSuatChieu(idSuatChieu);
        MaVe ve = maVeRepo.getMaVe(idSuatChieu);
        ThongTinVe thongTinVe = new ThongTinVe();
        thongTinVe.setTenPhim(thongtinSuatChieu.getPhim().getTenPhim());
        thongTinVe.setTenRap(thongtinSuatChieu.getLoaiRap().getLoai_rap());
        thongTinVe.setDiaDiem(thongtinSuatChieu.getDiaDiem().getDia_chi());
        thongTinVe.setGioChieu(thongtinSuatChieu.getGioChieu().getGio_chieu());
        thongTinVe.setTenGhe(thongtinSuatChieu.getChoNgoi().getCho_ngoi());
        thongTinVe.setMaVe(ve.getMaSoVe());

        try {
            // Create a MimeMessage.
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // Set the recipient, subject, and text.
            helper.setTo(users.getGmail());
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

            return "Email Sent!";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error sending email";
        }
    }

}
