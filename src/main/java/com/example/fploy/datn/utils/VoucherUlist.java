package com.example.fploy.datn.utils;

import com.example.fploy.datn.entity.trangThai.TrangThaiVoucher;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Component
@Primary
public class VoucherUlist {

//    public TrangThaiVoucher setTrangThaiVoucher(Date ngayBatDau, Date ngayKetThuc) {
//        // Kiểm tra xem voucher đã bị hủy chưa
//
//        LocalDateTime currentDate = LocalDateTime.now();
//        LocalDate currentDateOnly = currentDate.toLocalDate();
//
//        LocalDate batDau = ngayBatDau.toLocalDate();
//        LocalDate ketThuc = ngayKetThuc.toLocalDate();
//
//        if (currentDateOnly.isBefore(batDau)) {
//            return TrangThaiVoucher.UPCOMING;
//        } else if (currentDateOnly.isEqual(batDau) || (currentDateOnly.isAfter(batDau) && currentDateOnly.isBefore(ketThuc))) {
//            return TrangThaiVoucher.ONGOING;
//        } else if (currentDateOnly.isBefore(ketThuc)) {
//            return TrangThaiVoucher.ENDING_SOON;
//        } else if (currentDateOnly.isEqual(ketThuc) || currentDateOnly.isAfter(ketThuc)) {
//            return TrangThaiVoucher.EXPIRED;
//        } else {
//            return TrangThaiVoucher.OUT_OF_STOCK;
//        }
//    }
}
