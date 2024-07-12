package com.example.fploy.datn.service.ipml;

import com.example.fploy.datn.entity.Voucher;
import com.example.fploy.datn.entity.trangThai.TrangThaiVoucher;
import com.example.fploy.datn.exception.BadRequestException;
import com.example.fploy.datn.exception.NotFoundException;
import com.example.fploy.datn.model.request.create.VoucherResponseCreate;
import com.example.fploy.datn.model.request.update.VoucherResponseUpdate;
import com.example.fploy.datn.repository.VoucherRepository;
import com.example.fploy.datn.service.VoucherService;
import com.example.fploy.datn.utils.VoucherUlist;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    private VoucherRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private VoucherUlist voucherUtils;
    @Override
    public Page<Voucher> getAll(Integer page, Integer pageSize, String sortField, String sortOrder, String searchText, String trangThaiString, Date ngayBatDau, Date ngayKetThuc) {
        Sort sort;
        if ("ascend".equals(sortOrder)) {
            sort = Sort.by(sortField).ascending();
        } else if ("descend".equals(sortOrder)) {
            sort = Sort.by(sortField).descending();
        } else {
            sort = Sort.by("createAt").descending();
        }
        TrangThaiVoucher trangThaiVoucher;
        if(trangThaiString == null || trangThaiString.equals("")){
            trangThaiVoucher = null;
        }else {
            trangThaiVoucher = TrangThaiVoucher.valueOf(trangThaiString);
        }
        Pageable pageable = PageRequest.of(page - 1, pageSize, sort);
        Page<Voucher> voucherPage = repository.findByAll(pageable, searchText, trangThaiVoucher, ngayBatDau, ngayKetThuc);
        return voucherPage;
    }

    @Override
    public List<Voucher> getListVoucher(Integer id) {
//        List<Voucher> list = repository.getListVoucherOK(id);
//        return list
//                .stream()
//                .map(mapper::convertEntityToResponse)
//                .collect(Collectors.toList());
            return null;
    }

    @Override
    public List<Voucher> getListVoucherSuDung(Integer id) {
//        List<Voucher> list = repository.getListVoucherSuDung(id);
//        return list
//                .stream()
//                .map(mapper::convertEntityToResponse)
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public VoucherResponseCreate add(VoucherResponseCreate request) {
        if(repository.existsByMa(request.getMa())){
            throw new BadRequestException("Ma da ton tai trong he thong");
        }
        Voucher created = mapper.map(request, Voucher.class);
        created.setMa(request.getMa());

        TrangThaiVoucher status = voucherUtils.setTrangThaiVoucher(
                created.getDateBegin(),
                created.getDateEnd()
        );
        created.setStatus(status);
        Voucher save = this.repository.save(created);

        return mapper.map(save, VoucherResponseCreate.class);
    }

    @Override
    public VoucherResponseUpdate update(Integer id, VoucherResponseUpdate request) {
        Optional<Voucher> optional = repository.findById(id);
        if(optional.isEmpty()){
            throw new BadRequestException("Voucher khong ton tai");
        }

        if(!request.getMa().equals(optional.get().getMa()) && repository.existsByMa(request.getMa())){
            throw new BadRequestException("Ma voucher đã tồn tại trong hệ thống!");
        }
        Voucher detail = optional.get();
        TrangThaiVoucher status = voucherUtils.setTrangThaiVoucher(
                request.getDateBegin(), request.getDateEnd()
        );
        request.setId(detail.getId());
        request.setStatus(status);
        mapper.map(request, detail);
        Voucher voucherUpdate = this.repository.save(detail);
        return mapper.map(voucherUpdate, VoucherResponseUpdate.class);

    }

    @Override
    public Voucher delete(Integer id) {
        Optional<Voucher> optional = this.repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Voucher không tồn tại");
        }
        repository.delete(optional.get());
        return mapper.map(optional.get(), Voucher.class);

    }

    @Override
    public Integer soLanDaSuDung(Integer idVoucher, Date startDate, Date endDate) {
//        return repository.soLanDaSuDung(idVoucher, startDate, endDate);

        return null;
    }

    @Override
    public Integer soLanDaSuDungVoucherTaiKhoan(Integer idVoucher, Integer idTaiKhoan) {
        return repository.soLanDaSuDungVoucherTaiKhoan(idVoucher, idTaiKhoan);

    }

    @Override
    public Voucher findById(Integer id) {
        Optional<Voucher> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Voucher không tồn tại");
        }
        return mapper.map(optional.get(), Voucher.class);
    }

    @Override
    public Voucher findByMa(String ma) {
        Optional<Voucher> optional = repository.findByMa(ma);
        if (optional.isEmpty()) {
            throw new NotFoundException("Voucher không tồn tại");
        }
        return mapper.map(optional.get(), Voucher.class);
    }

    @Override
    public void updateVoucherStatus() {
        List<Voucher> vouchers = repository.danhSachVoucherKhongHuy();
        LocalDateTime now = LocalDateTime.now();


        for (Voucher voucher : vouchers) {
            TrangThaiVoucher oldStatus = voucher.getStatus();
            LocalDateTime dateBegin = voucher.getDateBegin().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime dateEnd = voucher.getDateEnd().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            if (now.isBefore(dateBegin)) {
                voucher.setStatus(TrangThaiVoucher.UPCOMING);
            } else if (now.isEqual(dateBegin) || (now.isAfter(dateBegin) && now.isBefore(dateEnd))) {
                if (now.isAfter(dateEnd.minus(1, ChronoUnit.DAYS))) {
                    voucher.setStatus(TrangThaiVoucher.ENDING_SOON);
                } else {
                    voucher.setStatus(TrangThaiVoucher.ONGOING);
                }
            } else if (now.isAfter(dateEnd)) {
                voucher.setStatus(TrangThaiVoucher.EXPIRED);
            } else {
                // Thêm điều kiện để kiểm tra nếu voucher đã bị hủy
                voucher.setStatus(TrangThaiVoucher.CANCELLED);
            }

            TrangThaiVoucher newStatus = voucher.getStatus();
            if (oldStatus != newStatus) {
                System.out.println("Voucher ID: " + voucher.getId() + " - Status changed from " + oldStatus + " to " + newStatus);
            }
        }

        repository.saveAll(vouchers);

    }

    @Override
    public void cancelVoucher(Integer id) {
        Voucher voucher = repository.findById(id).orElse(null);
        voucher.setStatus(TrangThaiVoucher.CANCELLED);
        repository.save(voucher);

    }
}
