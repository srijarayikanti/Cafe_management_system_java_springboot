package cafe_Management.demo.Service;

import cafe_Management.demo.enitites.KitchenTeam;
import org.springframework.http.ResponseEntity;

public interface kTeamService {
    ResponseEntity<?> saveKTeamDetails(KitchenTeam request);

    ResponseEntity<?> fetchKTeamDetails();
}
