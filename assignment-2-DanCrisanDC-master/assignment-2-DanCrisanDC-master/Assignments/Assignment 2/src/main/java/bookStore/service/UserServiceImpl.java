package bookStore.service;

import bookStore.dto.UserDto;
import bookStore.model.Role;
import bookStore.model.User;
import bookStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean delete(int id) {
        userRepository.deleteById(id);
        if (userRepository.findById(id) == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void update(UserDto userDto) {

        User user = userRepository.findById(userDto.getId()).get();
        user.setUsername(userDto.getUsername());
        user.setPassword(encodePassword(userDto.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void create(UserDto userDto) {

        User user = new User(userDto.getUsername(), encodePassword(userDto.getPassword()), Role.USER);
        userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, encodePassword(password));
    }

    private String encodePassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
