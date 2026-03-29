package cafe_Management.demo.Service;

import cafe_Management.demo.model.RequestUser;
import org.springframework.http.ResponseEntity;

public interface userService {
    ResponseEntity<?> saveUserDetails(RequestUser requestUser);
}
