# 🎬 WebXemPhim

> Hệ thống đặt vé xem phim trực tuyến được xây dựng bằng **Java (Spring Boot)** — hỗ trợ quản lý phim, suất chiếu, đặt vé, thanh toán demo và phân quyền Admin/User.

---


## 🔖 Mục lục
1. [Giới thiệu](#-giới-thiệu)  
2. [Tính năng chính](#-tính-năng-chính)  
3. [Kiến trúc & Công nghệ](#-kiến-trúc--công-nghệ)  
4. [Cài đặt & Chạy nhanh](#-cài-đặt--chạy-nhanh)  
5. [Cấu trúc thư mục](#-cấu-trúc-thư-mục)  
6. [API chính (tóm tắt)](#-api-chính-tóm-tắt)  
7. [Bảo mật & Best Practices](#-bảo-mật--best-practices)  
8. [UML & Flow](#-uml--flow)  
9. [Demo & Tài khoản thử nghiệm](#-demo--tài-khoản-thử-nghiệm)  
10. [Hướng phát triển](#-hướng-phát-triển)  
11. [Nhóm phát triển](#-nhóm-phát-triển)  
12. [Liên hệ](#-liên-hệ)

---

# 🎯 Giới thiệu
**WebXemPhim** là ứng dụng web cho phép người dùng xem thông tin phim, chọn suất chiếu, chọn ghế và đặt vé trực tuyến. Admin có thể quản lý phim, phòng chiếu, suất chiếu và xem báo cáo doanh thu.

Mục tiêu: xây dựng hệ thống rõ ràng, dễ mở rộng, bảo mật và dễ triển khai.

---

# ✅ Tính năng chính

## Dành cho Người dùng (User)
- Đăng ký / Đăng nhập / Đăng xuất
- Xem danh sách phim: đang chiếu / sắp chiếu
- Xem chi tiết phim: trailer, mô tả, thời lượng, thể loại, đánh giá
- Chọn suất chiếu, chọn ghế (bản đồ ghế)
- Đặt vé và xem lịch sử đặt vé
- Thanh toán demo (mô phỏng gateway)

## Dành cho Quản trị (Admin)
- CRUD Phim, Phòng chiếu, Suất chiếu
- Quản lý người dùng và phân quyền
- Quản lý đặt vé (hủy, in vé)
- Thống kê: doanh thu theo ngày / tháng, suất bán chạy

---

# 🧱 Kiến trúc & Công nghệ

- **Ngôn ngữ:** Java 17  
- **Framework:** Spring Boot (Spring MVC, Spring Data JPA, Spring Security)  
- **Database:** MySQL / MariaDB (cấu hình trong `application.properties`)  
- **ORM:** Hibernate / JPA  
- **Build:** Maven (pom.xml)  
- **Template (tuỳ chọn):** Thymeleaf (hoặc JSP), hoặc tách client (React/Vue)  
- **Authentication:** Session-based hoặc JWT (mô-đun đề xuất Spring Security + BCrypt)  
- **Triển khai:** Docker / Heroku / Railway / AWS

---

# ⚙️ Cài đặt & Chạy nhanh (Local)

### Yêu cầu
- Java 17+
- Maven 3.6+
- MySQL (hoặc Docker)

### 1. Clone repository
```bash
git clone https://github.com/<username>/webxemphim.git
cd webxemphim
2. Tạo database
Đăng nhập MySQL và tạo DB:

CREATE DATABASE webxemphim CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
3. Cấu hình application.properties (hoặc application.yml)
Mở src/main/resources/application.properties và chỉnh:

properties

spring.datasource.url=jdbc:mysql://localhost:3306/webxemphim?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Bangkok
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8080
Gợi ý: môi trường production nên đặt biến môi trường thay vì commit mật khẩu.

4. Chạy ứng dụng

mvn clean install
mvn spring-boot:run
Truy cập: http://localhost:8080
```

## 🧩 Class Diagram

```mermaid
classDiagram
    class User {
        +int id_user
        +string ten_user
        +string avatar
        +string sdt
        +bit gioi_tinh
        +date ngay_sinh
        +string tai_khoan
        +string mat_khau
        +string gmail
    }

    class Role {
        +int id_role
        +string ten_role
    }

    class UsersRoles {
        +int id_users_roles
        +int id_user
        +int id_role
    }

    class Phim {
        +int id_phim
        +string ten_phim
        +string anh_phim
        +string the_loai
        +time thoi_luong
        +datetime khoi_chieu
        +string dao_dien
        +string dien_vien
        +string ngon_ngu
        +string danh_gia
        +string noi_dung
        +int tinh_trang
    }

    class SuatChieu {
        +int id_suat_chieu
        +int id_phim
        +int id_ngay_chieu
        +int id_tinh
        +int id_dia_diem
        +int id_gio_chieu
        +int id_loai_rap
        +int id_cho_ngoi
    }

    class MaVe {
        +int id_ma_ve
        +blob masove
        +int trangthai
        +datetime created_at
        +int id_suat_chieu
    }

    class HoaDon {
        +int id_hoadon
        +int id_user
        +int id_suat_chieu
        +double total_price
        +datetime created_at
        +string trang_thai
    }

    class LichSuDatVe {
        +int id_lsdv
        +int id_ma_ve
        +date ngay_mua
        +int id_user
    }

    %% Relationships
    User "1" -- "many" UsersRoles : có
    Role "1" -- "many" UsersRoles : gán
    User "1" -- "many" HoaDon : tạo
    User "1" -- "many" LichSuDatVe : mua
    Phim "1" -- "many" SuatChieu : có
    SuatChieu "1" -- "many" MaVe : phát
    MaVe "1" -- "many" LichSuDatVe : ghi
```
