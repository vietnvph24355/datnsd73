package com.example.fploy.datn.utils;

import com.example.fploy.datn.entity.Order;
import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class PDFExporter {

    public String formatLocalDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return localDateTime.format(formatter);
    }

    public String formatLocalDateTimeNgay(LocalDateTime localDateTime) {
        int ngay = localDateTime.getDayOfMonth();
        int thang = localDateTime.getMonthValue();
        int nam = localDateTime.getYear();

        String ngayThangNam = String.format("Ngày %d Tháng %d Năm %d", ngay, thang, nam);

        return ngayThangNam;
    }

    public String formatNumberVietNam(BigDecimal number) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return currencyFormat.format(number);
    }

//    private String getHoaDonTenKhachHang(Order hoaDon) {
//        String ten = "Khách hàng lẻ";
//        if (hoaDon.getUsers() != null && hoaDon.getNote()) {
//            ten = hoaDon.getTaiKhoan().getHoVaTen();
//        } else if (hoaDon.getTaiKhoan() != null && hoaDon.getLoaiHoaDon() == CommonEnum.LoaiHoaDon.ONLINE) {
//            ten = hoaDon.getNguoiNhan();
//        } else if (hoaDon.getLoaiHoaDon() == CommonEnum.LoaiHoaDon.ONLINE){
//            ten = hoaDon.getNguoiNhan();
//        }
//        return ten;
//    }

//    private String getHoaDonSdtKhachHang(HoaDon hoaDon) {
//        String ten = "Khách hàng lẻ";
//        if (hoaDon.getTaiKhoan() != null && hoaDon.getLoaiHoaDon() == CommonEnum.LoaiHoaDon.COUNTER) {
//            ten = hoaDon.getTaiKhoan().getSoDienThoai();
//        } else if (hoaDon.getTaiKhoan() != null && hoaDon.getLoaiHoaDon() == CommonEnum.LoaiHoaDon.ONLINE) {
//            ten = hoaDon.getSdtNguoiNhan();
//        } else if (hoaDon.getLoaiHoaDon() == CommonEnum.LoaiHoaDon.ONLINE){
//            ten = hoaDon.getSdtNguoiNhan();
//        }
//        return ten;
//    }
//
//    private String getHoaDonDiaChiKhachHang(HoaDon hoaDon) {
//        String ten = "Trịnh Văn Bô - Nam Từ Liêm - Hà Nội";
//        if (hoaDon.getTaiKhoan() != null && hoaDon.getLoaiHoaDon() == CommonEnum.LoaiHoaDon.COUNTER) {
//            ten = "Trịnh Văn Bô - Nam Từ Liêm - Hà Nội";
//        } else if (hoaDon.getTaiKhoan() != null && hoaDon.getLoaiHoaDon() == CommonEnum.LoaiHoaDon.ONLINE) {
//            ten = hoaDon.getDiaChiNguoiNhan();
//        } else if (hoaDon.getLoaiHoaDon() == CommonEnum.LoaiHoaDon.ONLINE){
//            ten = hoaDon.getDiaChiNguoiNhan();
//        }
//        return ten;
//    }
//
//    private String getTenLoaiHoaDon(HoaDon hoaDon) {
//        String ten = "Tại quầy";
//        if (hoaDon.getLoaiHoaDon().getTen() == CommonEnum.LoaiHoaDon.ONLINE.getTen()) {
//            ten = "Giao hàng";
//        }
//        return ten;
//    }
//
//    private void writeTableHeader(PdfPTable table) {
//        PdfPCell cell = new PdfPCell();
////        cell.setBackgroundColor(Color.BLUE);
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell.setPadding(8);
//
//        Font font = FontFactory.getFont("Times New Roman", "UTF-8", true, 14, Font.BOLD);
////        font.setColor(Color.WHITE);
//
//        cell.setPhrase(new Phrase("STT", font));
//        table.addCell(cell);
//
//        cell.setPhrase(new Phrase("Tên sản phẩm", font));
//        table.addCell(cell);
//
//        cell.setPhrase(new Phrase("Số lượng", font));
//        table.addCell(cell);
//
//        cell.setPhrase(new Phrase("Đơn giá", font));
//        table.addCell(cell);
//
//        cell.setPhrase(new Phrase("Thành tiền", font));
//        table.addCell(cell);
//    }
//
//    private void writeTableData(PdfPTable table, HoaDon hoaDon) {
//
//        int stt = 1;
//        Font font = FontFactory.getFont("Times New Roman", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 12);
//        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
//        BigDecimal tongThanhTien = BigDecimal.ZERO;
//        BigDecimal tienShip = hoaDon.getPhiShip();
//
//        BigDecimal giamGiaVoucher = BigDecimal.ZERO;
//        if (hoaDon.getVoucher() != null) {
//            giamGiaVoucher = hoaDon.getTongTien().add(hoaDon.getPhiShip()).subtract(hoaDon.getTongTienKhiGiam());
//        }
//
//        for (HoaDonChiTiet hdct : hoaDon.getHoaDonChiTietList()) {
////            stt
//            PdfPCell sttCell = new PdfPCell(new Phrase(String.valueOf(stt)));
//            sttCell.setPadding(10f);
//            sttCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(sttCell);
//
////             sản phẩm
//            String tenSanPham = hdct.getChiTietSanPham().getSanPham().getTen();
//            String thuocTinhSanPham = "[" + hdct.getChiTietSanPham().getMauSac().getTen()
//                    + " - " + hdct.getChiTietSanPham().getKichCo().getKichCo()
//                    + " - " + hdct.getChiTietSanPham().getLoaiDe().getTen()
//                    + " - " + hdct.getChiTietSanPham().getDiaHinhSan().getTen()
//                    + "]";
//            Paragraph sanPham = new Paragraph(tenSanPham, font);
//            Paragraph thuocTinh = new Paragraph(thuocTinhSanPham, font);
//            PdfPCell sanPhamCell = new PdfPCell();
//            sanPhamCell.setPadding(10f);
//            sanPhamCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            sanPhamCell.addElement(sanPham);
//            sanPhamCell.addElement(thuocTinh);
//            table.addCell(sanPhamCell);
//
////            số lượng
//            PdfPCell soLuongCell = new PdfPCell(new Phrase(String.valueOf(hdct.getSoLuong())));
//            soLuongCell.setPadding(10f);
//            soLuongCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(soLuongCell);
//
////            đơn giá
//            PdfPCell donGiaCell = new PdfPCell(new Phrase(currencyFormat.format(hdct.getDonGia())));
//            donGiaCell.setPadding(10f);
//            donGiaCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            table.addCell(donGiaCell);
//
////            thành tiền
//            BigDecimal thanhTien = BigDecimal.valueOf(hdct.getSoLuong())
//                    .multiply(hdct.getDonGia());
//            PdfPCell thanhTienCell = new PdfPCell(new Phrase(currencyFormat.format(thanhTien)));
//            thanhTienCell.setPadding(10f);
//            thanhTienCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            table.addCell(thanhTienCell);
//
//            tongThanhTien = tongThanhTien.add(thanhTien);
//            stt++;
//        }
//
////        tổng thành tiền
//        PdfPCell tongTienCell = new PdfPCell(new Phrase("Tổng tiền"));
//        tongTienCell.setColspan(4);
//        tongTienCell.setPadding(10f);
//        tongTienCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//        PdfPCell tongTienValueCell = new PdfPCell(new Phrase(currencyFormat.format(hoaDon.getTongTien()), font));
//        tongTienValueCell.setPadding(10f);
//        tongTienValueCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//        table.addCell(tongTienCell);
//        table.addCell(tongTienValueCell);
//
////        Tiền giảm giá
//        PdfPCell giamGiaCell = new PdfPCell(new Phrase("Giảm giá"));
//        giamGiaCell.setColspan(4);
//        giamGiaCell.setPadding(10f);
//        giamGiaCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//        PdfPCell giamGiaValueCell = new PdfPCell(new Phrase(currencyFormat.format(giamGiaVoucher), font));
//        giamGiaValueCell.setPadding(10f);
//        giamGiaValueCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//        table.addCell(giamGiaCell);
//        table.addCell(giamGiaValueCell);
//
////        tiền ship
//        PdfPCell tienShipCell = new PdfPCell(new Phrase("Tiền ship"));
//        tienShipCell.setColspan(4);
//        tienShipCell.setPadding(10f);
//        tienShipCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//        PdfPCell tienShipValueCell = new PdfPCell(new Phrase(currencyFormat.format(tienShip), font));
//        tienShipValueCell.setPadding(10f);
//        tienShipValueCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//        table.addCell(tienShipCell);
//        table.addCell(tienShipValueCell);
//
////      Tổng thanh toán
//        PdfPCell tongThanhToanCell = new PdfPCell(new Phrase("Tổng thanh toán"));
//        tongThanhToanCell.setColspan(4);
//        tongThanhToanCell.setPadding(10f);
//        tongThanhToanCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//        PdfPCell tongThanhToanValueCell = new PdfPCell(new Phrase(currencyFormat.format(hoaDon.getTongTienKhiGiam())));
//        tongThanhToanValueCell.setPadding(10f);
//        tongThanhToanValueCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//        table.addCell(tongThanhToanCell);
//        table.addCell(tongThanhToanValueCell);
//    }
//
//    public void export(HttpServletResponse response, HoaDon hoaDon) throws DocumentException, IOException {
//        Document document = new Document(PageSize.A4);
//        PdfWriter.getInstance(document, response.getOutputStream());
//
//        BigDecimal soTienDaThanhToan = BigDecimal.ZERO;
//        String trangThaiGiaoDich = "Chưa trạng thái giao dịch";
//        String loaiGiaoDich = "Chưa có loại giao dịch";
//        GiaoDich giaoDich = hoaDon.getGiaoDichList().get(0);
//        if (hoaDon.getGiaoDichList().size() > 1) {
//            giaoDich = hoaDon.getGiaoDichList().get(1);
//        }
//
//        if (giaoDich.getPhuongThucThanhToan().getId() == 1) {
//            loaiGiaoDich = "Tiền mặt";
//            if (giaoDich.getTrangThaiGiaoDich() == CommonEnum.TrangThaiGiaoDich.SUCCESS) {
//                trangThaiGiaoDich = "Đã Thanh Toán / Tiền mặt";
//                soTienDaThanhToan = giaoDich.getSoTienGiaoDich();
//            }
//            if (giaoDich.getTrangThaiGiaoDich() == CommonEnum.TrangThaiGiaoDich.PENDING) {
//                trangThaiGiaoDich = "Chưa Thanh Toán / Tiền mặt";
//            }
//            if (giaoDich.getTrangThaiGiaoDich() == CommonEnum.TrangThaiGiaoDich.FAILED) {
//                trangThaiGiaoDich = "Thanh toán Thất bại / Tiền mặt";
//            }
//        }
//        if (giaoDich.getPhuongThucThanhToan().getId() == 2) {
//            loaiGiaoDich = "VNPay";
//            if (giaoDich.getTrangThaiGiaoDich() == CommonEnum.TrangThaiGiaoDich.SUCCESS) {
//                trangThaiGiaoDich = "Đã Thanh Toán / VNPay";
//                soTienDaThanhToan = giaoDich.getSoTienGiaoDich();
//            }
//            if (giaoDich.getTrangThaiGiaoDich() == CommonEnum.TrangThaiGiaoDich.PENDING) {
//                trangThaiGiaoDich = "Chưa Thanh Toán / VNPay";
//            }
//            if (giaoDich.getTrangThaiGiaoDich() == CommonEnum.TrangThaiGiaoDich.FAILED) {
//                trangThaiGiaoDich = "Thanh toán Thất bại / VNPay";
//            }
//        }
//        if (giaoDich.getPhuongThucThanhToan().getId() == 3) {
//            loaiGiaoDich = "COD";
//            if (giaoDich.getTrangThaiGiaoDich() == CommonEnum.TrangThaiGiaoDich.SUCCESS) {
//                trangThaiGiaoDich = "Đã Thanh Toán / COD";
//                soTienDaThanhToan = giaoDich.getSoTienGiaoDich();
//            }
//            if (giaoDich.getTrangThaiGiaoDich() == CommonEnum.TrangThaiGiaoDich.PENDING) {
//                trangThaiGiaoDich = "Chưa Thanh Toán / COD";
//            }
//            if (giaoDich.getTrangThaiGiaoDich() == CommonEnum.TrangThaiGiaoDich.FAILED) {
//                trangThaiGiaoDich = "Thanh toán Thất bại / COD";
//            }
//        }
//
//        document.open();
//
//        String imagePath = "src/main/java/com/poly/application/images/logo.jpg";
//        Image image = Image.getInstance(imagePath);
//        image.scaleAbsolute(200, 100); // Điều chỉnh kích thước của ảnh
//
//        Font fontTitle = FontFactory.getFont("Times New Roman", "UTF-8", true, 18, 1);
//
//        Font fontContent = FontFactory.getFont("Times New Roman", "UTF-8", true, 14, 1);
//
//        Font fontInfo = FontFactory.getFont("Times New Roman", "UTF-8", true, 14);
//
//        Font fontInfoKhach = FontFactory.getFont("Times New Roman", "UTF-8", true, 11);
//
//        PdfPTable table = new PdfPTable(5);
//        table.setWidthPercentage(100f);
//        table.setWidths(new float[]{1.0f, 5.0f, 2.0f, 2.0f, 2.0f});
//        table.setSpacingBefore(10);
//        writeTableHeader(table);
//        writeTableData(table, hoaDon);
//
////        logo - thông tin liên hệ
//        PdfPTable headerTitleTable = new PdfPTable(2);
//        headerTitleTable.setWidthPercentage(100);
//        headerTitleTable.setWidths(new float[]{1.3f, 2.3f});
//
//        PdfPCell imageCell = new PdfPCell(image);
//        imageCell.setBorder(Rectangle.NO_BORDER);
//        headerTitleTable.addCell(imageCell);
//
//        PdfPCell infoCell = new PdfPCell();
//        infoCell.setBorder(Rectangle.NO_BORDER);
//        infoCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//
//        Paragraph diaChi = new Paragraph("Địa chỉ: Trịnh Văn Bô - Nam Từ Liêm - Hà Nội", fontInfo);
//        Paragraph sdt = new Paragraph("Số điện thoại : 0987654321 - 0971852413", fontInfo);
//        Paragraph email = new Paragraph("Email : beesport.soccer@gmail.com", fontInfo);
//
//        diaChi.setAlignment(Element.ALIGN_RIGHT);
//        sdt.setAlignment(Element.ALIGN_RIGHT);
//        email.setAlignment(Element.ALIGN_RIGHT);
//
//        infoCell.addElement(diaChi);
//        infoCell.addElement(sdt);
//        infoCell.addElement(email);
//
//        headerTitleTable.addCell(infoCell);
////
////        Thông tin người nhận
//        PdfPTable shipInfoTable = new PdfPTable(1);
//        shipInfoTable.setWidthPercentage(100);
//        shipInfoTable.setWidths(new float[]{1f});
//        shipInfoTable.setSpacingBefore(10f);
//
//        Paragraph columnName = new Paragraph("Thông tin khách hàng", fontInfoKhach);
//        PdfPCell columnNameCell = new PdfPCell();
//        columnNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
////        columnNameCell.setBorder(Rectangle.NO_BORDER);
//        columnNameCell.addElement(columnName);
//        columnNameCell.setPadding(10);
//
//        Paragraph khachHang = new Paragraph("Khách hàng: " + getHoaDonTenKhachHang(hoaDon), fontInfoKhach);
//        Paragraph sdtKhachHang = new Paragraph("Điện thoại:    " + getHoaDonSdtKhachHang(hoaDon), fontInfoKhach);
//        Paragraph diaChiKhachHang = new Paragraph("Địa chỉ:         " + getHoaDonDiaChiKhachHang(hoaDon), fontInfoKhach);
//
//        PdfPCell shipInfoCell = new PdfPCell();
////        shipInfoCell.setBorder(Rectangle.NO_BORDER);
//        shipInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//        shipInfoCell.addElement(khachHang);
//        shipInfoCell.addElement(sdtKhachHang);
//        shipInfoCell.addElement(diaChiKhachHang);
//        shipInfoCell.setPadding(10);
//
//        shipInfoTable.addCell(columnNameCell);
//        shipInfoTable.addCell(shipInfoCell);
////
//        //        Thông tin hóa đơn
//        PdfPTable billInfoTable = new PdfPTable(1);
//        billInfoTable.setWidthPercentage(100);
//        billInfoTable.setWidths(new float[]{1f});
//        billInfoTable.setSpacingBefore(10f);
//        billInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER); // Set table border to NO_BORDER
//
//        Paragraph maHoaDon = new Paragraph("Mã hóa đơn: " + hoaDon.getMa(), fontInfoKhach);
//        Paragraph ngayTao = new Paragraph("Ngày tạo:    " + formatLocalDateTime(hoaDon.getNgayTao()), fontInfoKhach);
//        Paragraph loaiHoaDon = new Paragraph("Loại hóa đơn:         " + getTenLoaiHoaDon(hoaDon), fontInfoKhach);
//        Paragraph loaiGiaoDichText = new Paragraph("Loại giao dịch:         " + loaiGiaoDich, fontInfoKhach);
//        Paragraph trangThaiGiaoDichText = new Paragraph("Trạng thái giao dịch:         " + trangThaiGiaoDich, fontInfoKhach);
//        Paragraph soTienDaThanhToanText = new Paragraph("Số tiền đã thanh toán:         " + formatNumberVietNam(soTienDaThanhToan), fontInfoKhach);
//
//        PdfPCell infoHoaDonCell = new PdfPCell();
//        infoHoaDonCell.setBorder(Rectangle.NO_BORDER);
//        infoHoaDonCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//        infoHoaDonCell.addElement(maHoaDon);
//        infoHoaDonCell.addElement(ngayTao);
//        infoHoaDonCell.addElement(loaiHoaDon);
//        infoHoaDonCell.addElement(loaiGiaoDichText);
//        infoHoaDonCell.addElement(trangThaiGiaoDichText);
//        infoHoaDonCell.addElement(soTienDaThanhToanText);
//        infoHoaDonCell.setPadding(10);
//
//        billInfoTable.addCell(infoHoaDonCell);
////
//
//
//        document.add(headerTitleTable);
//
//        Paragraph title = new Paragraph("HÓA ĐƠN BÁN HÀNG", fontTitle);
//        title.setAlignment(Paragraph.ALIGN_CENTER);
//        document.add(title);
//
//        Paragraph ngayThang = new Paragraph(formatLocalDateTimeNgay(hoaDon.getNgayTao()), fontContent);
//        ngayThang.setAlignment(Paragraph.ALIGN_CENTER);
//        document.add(ngayThang);
//
//        document.add((billInfoTable));
//
//        document.add(shipInfoTable);
//
//        document.add(table);
//
//        document.close();
//
//    }
}
