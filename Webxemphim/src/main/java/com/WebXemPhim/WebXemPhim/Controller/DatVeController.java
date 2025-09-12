package com.WebXemPhim.WebXemPhim.Controller;

import com.WebXemPhim.WebXemPhim.DTO.*;
import com.WebXemPhim.WebXemPhim.Entity.*;
import com.WebXemPhim.WebXemPhim.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
@CrossOrigin
@RestController
public class DatVeController {

    @Autowired
    private DatVeRepository datVeRepository;
    @Autowired
    private PhimRepository phimRepository;
    @Autowired
    private NgayChieuRepo ngayChieuRepo;
    @Autowired
    private TinhRepo tinhRepo;
    @Autowired
    private DiaDiemRepo diaDiemRepo;
    @Autowired
    private GioChieuRepo gioChieuRepo;
    @Autowired
    private LoaiRapRepo loaiRapRepo;
    @Autowired
    private ChoNgoiRepo choNgoiRepo;
    @GetMapping("/datve")
    public List<DatVe> getById(@RequestParam("id") int id)
    {
        return datVeRepository.findByIdTinh(id);
    }

    @GetMapping("/getTinhPhim")
    public ResponseEntity<List<DiaDiemByTinhAndPhim>> getDiaDiem(@RequestParam("idNgayChieu") int idNgayChieu, @RequestParam("idPhim") int idPhim){

        List<DatVe> allVe = datVeRepository.getDiaDiemByTinhAndPhim(idNgayChieu, idPhim);

        Set<String> uniqueDiaDiems = new HashSet<>();

        List<DiaDiemByTinhAndPhim> allDiaDiem = allVe.stream().filter(datVe -> uniqueDiaDiems.add(datVe.getDiaDiem().getDia_chi()))
                .map(diaDiem -> {
                    DiaDiemByTinhAndPhim diaDiemDTO = new DiaDiemByTinhAndPhim();
                    diaDiemDTO.setDiaDiem(diaDiem.getTinh().getTenTinh());
                    diaDiemDTO.setId(diaDiem.getTinh().getId());
                    return diaDiemDTO;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(allDiaDiem, HttpStatus.OK);
    }
    @GetMapping("/getNgayPhim/{idPhim}")
    public ResponseEntity<List<NgayByIdPhim>> getNgay(@PathVariable int idPhim){
        List<DatVe> allNgay = datVeRepository.findNgayByIdPhim(idPhim);
        Set<Date> filterNgayTrung = new HashSet<>();
        List<NgayByIdPhim> ngays = allNgay.stream()
                .filter(ngay -> filterNgayTrung.add(ngay.getNgayChieu().getNgayChieu()))
                .map(ngay ->{
            NgayByIdPhim ngayByIdPhim = new NgayByIdPhim();
            ngayByIdPhim.setThoiGian(ngay.getNgayChieu().getNgayChieu());
            ngayByIdPhim.setId(ngay.getNgayChieu().getId());
            return ngayByIdPhim;
        })
         .distinct()
         .collect(Collectors.toList());
        return new ResponseEntity<>(ngays, HttpStatus.OK);
    }


    @GetMapping("/getRapPhim")
    public ResponseEntity<List<RapDTO>> getRap(@RequestParam("idPhim") int idPhim, @RequestParam("idNgayChieu") int idNgayChieu,
                                               @RequestParam("idTinh") int idTinh)
    {
        List<DatVe> listIdRap = datVeRepository.getLoaiRap(idPhim, idNgayChieu, idTinh);
        Set<String> filterRapTrung = new HashSet<>();
        List<RapDTO> listTenRap = listIdRap.stream()
                .filter(rap -> filterRapTrung.add(rap.getLoaiRap().getLoai_rap()))
                .map(
                IDrap ->{
                    RapDTO rapDTO = new RapDTO();
                    rapDTO.setTenRap(IDrap.getLoaiRap().getLoai_rap());
                    rapDTO.setId(IDrap.getLoaiRap().getId_loai_rap());
                    return rapDTO;
                }
        )
         .distinct()
         .collect(Collectors.toList());
        return new ResponseEntity<>(listTenRap, HttpStatus.OK);
    }

    @GetMapping("/getDiaDiaAndGioChieu")
    public ResponseEntity<List<DiaDiemAndGioChieu>> getAll(
            @RequestParam("idPhim") int idPhim,
            @RequestParam("idNgayChieu") int idNgayChieu,
            @RequestParam("idTinh") int idTinh,
            @RequestParam("idRap") int idRap) {

        List<DatVe> result = datVeRepository.getDiaDiemAndGioChieu(idPhim, idNgayChieu, idTinh, idRap);

        // Sử dụng LinkedHashMap để duy trì thứ tự của các cặp key-value theo thứ tự đầu tiên xuất hiện
        Map<DiaDiem, List<GioChieuDTO>> groupedByDiaDiem = new LinkedHashMap<>();

        for (DatVe datVe : result) {
            DiaDiem diaDiem = datVe.getDiaDiem();
            GioChieuDTO gioChieuDTO = new GioChieuDTO(datVe.getGioChieu().getId_gio_chieu(), datVe.getGioChieu().getGio_chieu());


            // Nếu diaDiem đã có trong Map, thêm gioChieuDTO vào danh sách tương ứng
            // Nếu không, tạo mới một danh sách và thêm vào Map
            groupedByDiaDiem.computeIfAbsent(diaDiem, k -> new ArrayList<>()).add(gioChieuDTO);
        }

        // Tạo danh sách kết quả từ Map
        List<DiaDiemAndGioChieu> list = groupedByDiaDiem.entrySet().stream().map(entry -> {
                    DiaDiem diaDiem = entry.getKey();
                    List<GioChieuDTO> gioChieuList = entry.getValue()
                            .stream()
                            .distinct() // Sử dụng distinct với equals và hashCode đã được override trong GioChieuDTO
                            .collect(Collectors.toList());

                    DiaDiemAndGioChieu diaDiemAndGioChieu = new DiaDiemAndGioChieu();
                    diaDiemAndGioChieu.setIdDiaDiem(diaDiem.getId_dia_diem());
                    diaDiemAndGioChieu.setDiaDiem(diaDiem.getDia_chi());
                    diaDiemAndGioChieu.setListGioChieu(gioChieuList);

                    return diaDiemAndGioChieu;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping("/getChoNgoi")
    public ResponseEntity<List<ChoNgoiDTO>> getChoNgoi(@RequestParam("idPhim") int idPhim,
                                                       @RequestParam("idNgayChieu") int idNgayChieu,
                                                       @RequestParam("idTinh") int idTinh,
                                                       @RequestParam("idRap") int idRap,
                                                       @RequestParam("idDiaDiem") int idDiaDiem,
                                                       @RequestParam("idGioChieu") int idGioChieu){
        List<DatVe> IDChoNgoi = datVeRepository.getChoNgoi(idPhim, idNgayChieu, idTinh, idRap, idDiaDiem, idGioChieu);
        Set<String> filterRapTrung = new HashSet<>();
        List<ChoNgoiDTO> choNgoiDTOS = IDChoNgoi.stream().filter(cn -> filterRapTrung.add(cn.getChoNgoi().getCho_ngoi())).map(
                choNgoi ->{
                    ChoNgoiDTO choNgoiDTO = new ChoNgoiDTO();
                    choNgoiDTO.setId(choNgoi.getChoNgoi().getId_cho_ngoi());
                    choNgoiDTO.setChoNgoi(choNgoi.getChoNgoi().getCho_ngoi());
                    choNgoiDTO.setTrangThai(choNgoi.getChoNgoi().getTrangThai());
                    choNgoiDTO.setPrice(choNgoi.getLoaiRap().getGiaTien());
                    return choNgoiDTO;
                }
        ).collect(Collectors.toList());
        return new ResponseEntity<>(choNgoiDTOS, HttpStatus.OK);
    }
    @PostMapping("/IDDatCho")
    public ResponseEntity<List<DatChoDTO>> getIDDatCho(@RequestParam("idPhim") int idPhim,
                                                       @RequestParam("idNgayChieu") int idNgayChieu,
                                                       @RequestParam("idTinh") int idTinh,
                                                       @RequestParam("idRap") int idRap,
                                                       @RequestParam("idDiaDiem") int idDiaDiem,
                                                       @RequestParam("idGioChieu") int idGioChieu,
                                                       @RequestParam("idChoNgoi") int idChoNgoi)
    {
        List<Integer> id = datVeRepository.getIDDatCho(idPhim, idNgayChieu, idTinh, idRap, idDiaDiem, idGioChieu, idChoNgoi);
        Set<Integer> filterID = new HashSet<>();
        List<DatChoDTO> datChoDTOS = id.stream().filter(dc -> filterID.add(dc))
                .map(datChos -> {
                    DatChoDTO choDTO = new DatChoDTO();
                    choDTO.setIDDatCho(datChos);
                    return choDTO;
                }).collect(Collectors.toList());
        return new ResponseEntity<>(datChoDTOS, HttpStatus.OK);
    }

    @PostMapping("/newListSuatChieu")
    public ResponseEntity<Object> create(@RequestBody NewSuatChieu newSuatChieu) {
        try {
            // Check if entities with the provided IDs exist
            Phim phim = phimRepository.findById(newSuatChieu.getIdPhim())
                    .orElseThrow(() -> new EntityNotFoundException("Phim not found with id: " + newSuatChieu.getIdPhim()));

            NgayChieu ngayChieu = ngayChieuRepo.findById(newSuatChieu.getIdNgayChieu())
                    .orElseThrow(() -> new EntityNotFoundException("NgayChieu not found with id: " + newSuatChieu.getIdNgayChieu()));

            Tinh tinh = tinhRepo.findById(newSuatChieu.getIdTinh())
                    .orElseThrow(() -> new EntityNotFoundException("Tinh not found with id: " + newSuatChieu.getIdTinh()));

            DiaDiem diaDiem = diaDiemRepo.findById(newSuatChieu.getIdDiaDiem())
                    .orElseThrow(() -> new EntityNotFoundException("DiaDiem not found with id: " + newSuatChieu.getIdDiaDiem()));

            GioChieu gioChieu = gioChieuRepo.findById(newSuatChieu.getIdGioChieu())
                    .orElseThrow(() -> new EntityNotFoundException("GioChieu not found with id: " + newSuatChieu.getIdGioChieu()));

            LoaiRap loaiRap = loaiRapRepo.findById(newSuatChieu.getIdLoaiRap())
                    .orElseThrow(() -> new EntityNotFoundException("LoaiRap not found with id: " + newSuatChieu.getIdLoaiRap()));

            List<DatVe> datVes = datVeRepository.getChoNgoi(newSuatChieu.getIdPhim(), newSuatChieu.getIdNgayChieu(), newSuatChieu.getIdTinh(),
                    newSuatChieu.getIdLoaiRap(), newSuatChieu.getIdDiaDiem(), newSuatChieu.getIdGioChieu());
            if(!datVes.isEmpty()){
                return new ResponseEntity<>("Đã có suất chiếu này roi", HttpStatus.BAD_REQUEST);
            }

            List<ChoNgoi> listIDChoNgoi = new ArrayList<>();
            char row = 'A';
            for (int i = 1; i <= 5; i++) {
                for (int j = 1; j <= 10; j++) {
                    String seatName = row + String.valueOf(j);
                    ChoNgoi choNgoi = new ChoNgoi();
                    choNgoi.setCho_ngoi(seatName);
                    choNgoi.setTrangThai(0);
                    choNgoiRepo.save(choNgoi);
                    listIDChoNgoi.add(choNgoi);
                }
                row++;
            }



            // Create DatVe entities for each ChoNgoi
            for (ChoNgoi choNgoi : listIDChoNgoi) {
                DatVe newSC = new DatVe();
                newSC.setPhim(phim);
                newSC.setNgayChieu(ngayChieu);
                newSC.setTinh(tinh);
                newSC.setDiaDiem(diaDiem);
                newSC.setGioChieu(gioChieu);
                newSC.setLoaiRap(loaiRap);
                newSC.setChoNgoi(choNgoi);
                datVeRepository.save(newSC);
            }
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Entity not found: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
