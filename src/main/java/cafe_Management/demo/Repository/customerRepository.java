package cafe_Management.demo.Repository;

import cafe_Management.demo.enitites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface customerRepository extends JpaRepository<Customer,Integer> {

}
