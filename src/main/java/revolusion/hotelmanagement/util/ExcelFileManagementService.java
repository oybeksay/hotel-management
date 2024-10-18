package revolusion.hotelmanagement.util;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import revolusion.hotelmanagement.entity.Guest;
import revolusion.hotelmanagement.entity.Hotel;
import revolusion.hotelmanagement.entity.Order;
import revolusion.hotelmanagement.repository.GuestRepository;
import revolusion.hotelmanagement.repository.HotelRepository;
import revolusion.hotelmanagement.repository.OrderRepository;

import java.io.IOException;
import java.util.List;

@Component
public class ExcelFileManagementService {

    private final GuestRepository guestRepository;
    private final HotelRepository hotelRepository;
    private final OrderRepository orderRepository;

    public ExcelFileManagementService(GuestRepository guestRepository, HotelRepository hotelRepository, OrderRepository orderRepository) {
        this.guestRepository = guestRepository;
        this.hotelRepository = hotelRepository;
        this.orderRepository = orderRepository;
    }

    public void generateHotelExcelFile(HttpServletResponse response) throws IOException {
        List<Hotel> hotels = hotelRepository.findAll();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Hotel Info");
        XSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("ID hotel");
        row.createCell(1).setCellValue("Hotel Name");
        row.createCell(2).setCellValue("Address");
        row.createCell(3).setCellValue("Description");
        row.createCell(4).setCellValue("Rating");
        row.createCell(5).setCellValue("Total Rooms");

        int dataRowIndex = 1;

        for (Hotel hotel : hotels) {
            XSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(hotel.getId());
            dataRow.createCell(1).setCellValue(hotel.getName());
            dataRow.createCell(2).setCellValue(hotel.getAddress());
            dataRow.createCell(3).setCellValue(hotel.getDescription());
            dataRow.createCell(4).setCellValue(hotel.getStarRating());
            dataRow.createCell(5).setCellValue(hotel.getTotalRooms());

            dataRowIndex++;
        }

        for (int i = 0; i < 6; i++) {
            sheet.autoSizeColumn(i);
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
    }


    public void generateGuestExcelFile(HttpServletResponse response) throws IOException {
        List<Guest> guests = guestRepository.findAll();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Guest Info");
        XSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("ID guest");
        row.createCell(1).setCellValue("Guest Name");
        row.createCell(2).setCellValue("Address");
        row.createCell(3).setCellValue("Phone Number");
        row.createCell(4).setCellValue("Email");

        int dataRowIndex = 1;

        for (Guest guest : guests) {
            XSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(guest.getId());
            dataRow.createCell(1).setCellValue(guest.getName());
            dataRow.createCell(2).setCellValue(guest.getAddress());
            dataRow.createCell(3).setCellValue(guest.getPhone());
            dataRow.createCell(4).setCellValue(guest.getEmail());

            dataRowIndex++;
        }

        for (int i = 0; i < 5; i++) {
            sheet.autoSizeColumn(i);
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
    }


    public void generateOrderExcelFile(HttpServletResponse response) throws IOException {
        List<Order> orders = orderRepository.findAll();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Order Info");
        XSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("ID order");
        row.createCell(1).setCellValue("Check-in");
        row.createCell(2).setCellValue("Check-out");
        row.createCell(3).setCellValue("Guest ID");
        row.createCell(4).setCellValue("Room ID");
        row.createCell(5).setCellValue("Total Price");

        int dataRowIndex = 1;

        for (Order order : orders) {
            XSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(order.getId());
            dataRow.createCell(1).setCellValue(order.getStartDate().toString());
            dataRow.createCell(2).setCellValue(order.getEndDate().toString());
            dataRow.createCell(3).setCellValue(order.getGuest().getId());
            dataRow.createCell(4).setCellValue(order.getRoom().getId());
            dataRow.createCell(5).setCellValue(order.getTotalAmount());

            dataRowIndex++;
        }

        for (int i = 0; i < 6; i++) {
            sheet.autoSizeColumn(i);
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
    }
}
