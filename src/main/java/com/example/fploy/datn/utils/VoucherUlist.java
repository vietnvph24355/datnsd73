package com.example.fploy.datn.utils;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.sql.Date;

@Component
@Primary
public class VoucherUlist {

//    public String setTrangThaiVoucher(Date ngayBatDau, Date ngayKetThuc) {
////        // Kiểm tra xem voucher đã bị hủy chưa
////        LocalDateTime currentDate = LocalDateTime.now();
////        Date date = Date.valueOf(currentDate.toLocalDate());
////
////        if (date.isBefore(ngayBatDau)) {
////            return "UPCOMING";
////        } else if (currentDate.isEqual(ngayBatDau) || (currentDate.isAfter(ngayBatDau) && currentDate.isBefore(ngayKetThuc))) {
////            return "ONGOING";
////        } else if (currentDate.isBefore(ngayKetThuc)) {
////            return "ENDING_SOON";
////        } else if (currentDate.isEqual(ngayKetThuc) || currentDate.isAfter(ngayKetThuc)) {
////            return"EXPIRED";
////        } else {
////            return "OUT_OF_STOCK";
////        }
//    }
}
