package com.example.fploy.datn.repository;

import com.example.fploy.datn.entity.Voucher;
import com.example.fploy.datn.entity.trangThai.TrangThaiVoucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
    @Query("SELECT obj FROM Voucher obj WHERE (obj.ma LIKE %:searchText% ) " +
            "AND (:tragThai IS NULL OR obj.status = :trangThai) " +
            "AND (:ngayBatDau IS NULL OR obj.dateBegin >= :ngayBatDau) " +
            "AND (:ngayKetThuc IS NULL OR obj.dateEnd <= :ngayKetThuc)")
    Page<Voucher> findByAll(Pageable pageable, String searchText, TrangThaiVoucher trangThai,
                            Date ngayBatDau, Date ngayKetThuc);

    Optional<Voucher> findByMa(String ma);

    @Query("SELECT v FROM Voucher v WHERE v.status IN ('ONGOING', 'UPCOMING', 'ENDING_SOON') ORDER BY v.updateAt DESC")
    List<Voucher> getListVoucher();

//    @Query("SELECT v \n" +
//            "FROM Voucher v\n" +
//            "WHERE v.id NOT IN (SELECT vct.voucher.id FROM VoucherChiTiet vct WHERE v.trangThai IN ('ONGOING','ENDING_SOON'))\n" +
//            "   OR (:idTaiKhoan IS NOT NULL AND v.id IN (SELECT vct.voucher.id FROM VoucherChiTiet vct WHERE vct.taiKhoan.id = :idTaiKhoan AND vct.soLanSuDung > 0))\n")
//    List<Voucher> getListVoucherOK(@Param("idTaiKhoan") Long id);


    @Query("SELECT v FROM Voucher v WHERE v.status != 'CANCELLED'")
    List<Voucher> danhSachVoucherKhongHuy();

//    @Query("SELECT COUNT(hd) " +
//            "FROM Order hd " +
//            "WHERE hd.voucher.id = :idVoucher " +
//            "AND CAST(hd. AS date) BETWEEN :startDate AND :endDate")
//    Long soLanDaSuDung(@Param("idVoucher") Long idVoucher,
//                       @Param("startDate") Date startDate,
//                       @Param("endDate") Date endDate);


    @Query("SELECT COUNT(hd) " +
            "FROM Order hd " +
            "WHERE hd.voucher.id = :idVoucher " +
            "AND hd.users.id = :idTaiKhoan")
    Long soLanDaSuDungVoucherTaiKhoan(@Param("idVoucher") Long idVoucher,
                                      @Param("idTaiKhoan") Long idTaiKhoan);

}
