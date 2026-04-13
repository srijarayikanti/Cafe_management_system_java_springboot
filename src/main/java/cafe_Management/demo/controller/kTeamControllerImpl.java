package cafe_Management.demo.controller;

import cafe_Management.demo.Service.kTeamService;
import cafe_Management.demo.enitites.KitchenTeam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class kTeamControllerImpl implements kTeamController{
    private final kTeamService kTeamService;

    public kTeamControllerImpl(kTeamService kTeamService) {
        this.kTeamService = kTeamService;
    }

    @Override
    public ResponseEntity<?> saveKTeamDetails(KitchenTeam request) {
        try {
            return kTeamService.saveKTeamDetails(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<?> fetchKTeamDetails() {
        try {
            return kTeamService.fetchKTeamDetails();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
