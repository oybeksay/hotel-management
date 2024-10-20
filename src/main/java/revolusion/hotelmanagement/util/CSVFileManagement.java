package revolusion.hotelmanagement.util;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import revolusion.hotelmanagement.entity.Guest;
import revolusion.hotelmanagement.entity.Hotel;
import revolusion.hotelmanagement.entity.Order;
import revolusion.hotelmanagement.repository.GuestRepository;
import revolusion.hotelmanagement.repository.HotelRepository;
import revolusion.hotelmanagement.repository.OrderRepository;
import revolusion.hotelmanagement.service.GuestService;
import revolusion.hotelmanagement.service.HotelService;
import revolusion.hotelmanagement.service.OrderService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CSVFileManagement {

    private final HotelService hotelService;
    private final GuestService guestService;
    private final OrderService orderService;

    public void generateHotelCsvFile(HttpServletResponse response) throws IOException {
        List<Hotel> hotels = hotelService.getAll();

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"hotels.csv\"");

        PrintWriter writer = response.getWriter();

        writer.println("ID Hotel,Hotel Name,Address,Description,Rating,Total Rooms");

        for (Hotel hotel : hotels) {
            writer.printf("%d,%s,%s,%s,%d,%d%n",
                    hotel.getId(),
                    hotel.getName(),
                    hotel.getAddress(),
                    hotel.getDescription(),
                    hotel.getStarRating(),
                    hotel.getTotalRooms());
        }

        writer.flush();
        writer.close();
    }


    public void generateGuestCsvFile(HttpServletResponse response) throws IOException {
        List<Guest> guests = guestService.getAllGuests();

        PrintWriter writer = response.getWriter();

        writer.println("ID Guest,Guest Name,Address,Phone Number,Email");

        for (Guest guest : guests) {
            writer.printf("%d,%s,%s,%s,%s%n",
                    guest.getId(),
                    guest.getName(),
                    guest.getAddress(),
                    guest.getPhone(),
                    guest.getEmail());
        }

        writer.flush();
        writer.close();
    }

    public void generateOrderCsvFile(HttpServletResponse response) throws IOException {
        List<Order> orders = orderService.getAllOrder();

        PrintWriter writer = response.getWriter();

        writer.println("ID Order,Check-in,Check-out,Guest ID,Room ID,Total Price");

        for (Order order : orders) {
            writer.printf("%d,%s,%s,%d,%d,%f%n",
                    order.getId(),
                    order.getStartDate().toString(),
                    order.getEndDate().toString(),
                    order.getGuest().getId(),
                    order.getRoom().getId(),
                    order.getTotalAmount());
        }

        writer.flush();
        writer.close();
    }


}
