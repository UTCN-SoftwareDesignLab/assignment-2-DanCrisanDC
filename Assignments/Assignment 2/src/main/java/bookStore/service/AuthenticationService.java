package bookStore.service;

import bookStore.dto.UserDto;
import bookStore.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthenticationService {

    boolean create(UserDto userDto);

    List<User> getAll();
}
