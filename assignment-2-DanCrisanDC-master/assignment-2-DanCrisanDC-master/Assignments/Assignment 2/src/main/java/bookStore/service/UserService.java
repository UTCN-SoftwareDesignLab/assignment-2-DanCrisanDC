package bookStore.service;

import bookStore.dto.UserDto;
import bookStore.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    boolean delete(int id);

    void update(UserDto userDto);

    void create(UserDto userDto);

    List<User> getAll();

    User findByUsernameAndPassword(String username, String password);
}
