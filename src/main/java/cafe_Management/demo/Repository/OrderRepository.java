package cafe_Management.demo.Repository;

import cafe_Management.demo.enitites.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
