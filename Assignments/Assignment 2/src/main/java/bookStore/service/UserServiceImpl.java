package bookStore.service;

import bookStore.dto.UserDto;
import bookStore.model.Role;
import bookStore.model.User;
import bookStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private final BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean delete(int id) {
        userRepository.deleteById(id);
        if(userRepository.findById(id) == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void update(UserDto userDto) {

        User user = userRepository.findById(userDto.getId()).get();
        user.setUsername(userDto.getUsername());
        user.setPassword(encoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void create(UserDto userDto) {

        User user = new User(userDto.getUsername(), encoder.encode(userDto.getPassword()), Role.USER);
        userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, encoder.encode(password));
    }
}
