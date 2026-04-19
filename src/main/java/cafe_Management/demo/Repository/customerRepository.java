package cafe_Management.demo.Repository;

import cafe_Management.demo.enitites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface customerRepository extends JpaRepository<Customer,Integer> {


    @Query(value = "SELECT * FROM customer WHERE email = :email", nativeQuery = true)
    List<Customer> findByEmail(String email);
}
