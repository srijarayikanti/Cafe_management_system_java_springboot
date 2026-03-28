package cafe_Management.demo.Repository;

import cafe_Management.demo.enitites.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface loginRepository extends JpaRepository<Login, Integer> {
}
