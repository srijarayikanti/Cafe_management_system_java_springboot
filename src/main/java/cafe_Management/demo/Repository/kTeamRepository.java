package cafe_Management.demo.Repository;

import cafe_Management.demo.enitites.KitchenTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface kTeamRepository extends JpaRepository<KitchenTeam,Integer> {
}
