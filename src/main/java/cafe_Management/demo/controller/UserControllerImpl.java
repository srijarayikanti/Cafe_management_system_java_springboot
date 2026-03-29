package cafe_Management.demo.controller;

import cafe_Management.demo.Service.userService;
import cafe_Management.demo.model.RequestUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController {

private final userService userService;

    public UserControllerImpl(userService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> saveUserDetails(RequestUser requestUser) {
        try {
            return userService.saveUserDetails(requestUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
