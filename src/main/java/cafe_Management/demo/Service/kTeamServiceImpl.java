package cafe_Management.demo.Service;

import cafe_Management.demo.Repository.kTeamRepository;
import cafe_Management.demo.enitites.KitchenTeam;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class kTeamServiceImpl implements kTeamService {
    private final kTeamRepository kTeamRepository;

    public kTeamServiceImpl(kTeamRepository kTeamRepository) {
        this.kTeamRepository = kTeamRepository;
    }

    @Override
    public ResponseEntity<?> saveKTeamDetails(KitchenTeam request) {
        KitchenTeam kitchenTeam = new KitchenTeam();
        BeanUtils.copyProperties(request, kitchenTeam);
        kTeamRepository.save(kitchenTeam);
        return ResponseEntity.ok(kitchenTeam);
    }

    @Override
    public ResponseEntity<?> fetchKTeamDetails() {
        return ResponseEntity.ok(kTeamRepository.findAll());
    }
}

