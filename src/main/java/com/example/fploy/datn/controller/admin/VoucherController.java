package com.example.fploy.datn.controller.admin;

import com.example.fploy.datn.model.request.create.VoucherResponseCreate;
import com.example.fploy.datn.model.request.update.VoucherResponseUpdate;
import com.example.fploy.datn.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin/api/voucher")
public class VoucherController {
    @Autowired
    private VoucherService service;

    @GetMapping()
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "sortField", required = false) String sortField,
            @RequestParam(name = "sortOrder", defaultValue = "", required = false) String sortOrder,
            @RequestParam(name = "searchText", defaultValue = "") String searchText,
            @RequestParam(name = "trangThai", required = false) String trangThaiString,
            @RequestParam(name = "ngayBatDau", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date ngayBatDau,
            @RequestParam(name = "ngayKetThuc", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date ngayKetThuc

    ) {
        return ResponseEntity.ok(service.getAll(page, pageSize, sortField, sortOrder, searchText,
                trangThaiString, ngayBatDau, ngayKetThuc));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getList(
            @RequestParam(name = "idTaiKhoan", defaultValue = "") Integer id
    ) {
        return ResponseEntity.ok(service.getListVoucher(id));
    }

    @GetMapping("/list-su-dung")
    public ResponseEntity<?> getListSuDung(
            @RequestParam(value = "idTaiKhoan", defaultValue = "") Integer id
    ) {
        return ResponseEntity.ok(service.getListVoucherSuDung(id));
    }

    @GetMapping("/da-su-dung")
    public ResponseEntity<?> getDaSuDung(@RequestParam("idVoucher") Integer idVoucher,
                                         @RequestParam("startDate") Date startDate,
                                         @RequestParam("endDate") Date endDate) {
        return ResponseEntity.ok(service.soLanDaSuDung(idVoucher, startDate, endDate));
    }

    @GetMapping("/da-su-dung-tai-khoan")
    public ResponseEntity<?> getDaSuDungTaiKhoan(@RequestParam("idVoucher") Integer idVoucher,
                                                 @RequestParam("idTaiKhoan") Integer idTaiKhoan) {
        return ResponseEntity.ok(service.soLanDaSuDungVoucherTaiKhoan(idVoucher, idTaiKhoan));
    }

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody VoucherResponseCreate request) {
        return new ResponseEntity<>(service.add(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/maVoucher")
    public ResponseEntity<?> getOneMa(@RequestParam(name = "maVoucher") String ma) {
        return ResponseEntity.ok(service.findByMa(ma));
    }

    @PutMapping("/cancel-voucher/{id}")
    public ResponseEntity<?> cancelVoucher(@PathVariable(name = "id") Integer id) {
        service.cancelVoucher(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Integer id, @RequestBody VoucherResponseUpdate request) {
        return ResponseEntity.ok(service.update(id, request));
    }
}
