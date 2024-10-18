package revolusion.hotelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import revolusion.hotelmanagement.entity.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByGuest_Id(Integer id);

    @Query("SELECT o FROM Order o WHERE o.room.id = :roomId " +
            "AND (:startDate BETWEEN o.startDate AND o.endDate " +
            "OR :endDate BETWEEN o.startDate AND o.endDate " +
            "OR (o.startDate BETWEEN :startDate AND :endDate))")
    List<Order> findConflictingOrders(@Param("roomId") Integer roomId,
                                      @Param("startDate") LocalDate startDate,
                                      @Param("endDate") LocalDate endDate);
}