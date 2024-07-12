package com.example.fploy.datn.service;

import com.example.fploy.datn.entity.Voucher;
import com.example.fploy.datn.model.request.create.VoucherResponseCreate;
import com.example.fploy.datn.model.request.update.VoucherResponseUpdate;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface VoucherService {
    Page<Voucher> getAll(Integer page, Integer pageSize, String sortField, String sortOrder,
                         String searchText, String trangThaiString,
                         Date ngayBatDau, Date ngayKetThuc);
    List<Voucher> getListVoucher(Integer id);
    List<Voucher> getListVoucherSuDung(Integer id);

    VoucherResponseCreate add(VoucherResponseCreate request);

    VoucherResponseUpdate update(Integer id, VoucherResponseUpdate request);

    Voucher delete(Integer id);

    Integer soLanDaSuDung(Integer idVoucher, Date startDate, Date endDate);
    Integer soLanDaSuDungVoucherTaiKhoan(Integer idVoucher, Integer idTaiKhoan);

    Voucher findById(Integer id);

    Voucher findByMa(String ma);

    void updateVoucherStatus();

    void cancelVoucher(Integer id);

}
