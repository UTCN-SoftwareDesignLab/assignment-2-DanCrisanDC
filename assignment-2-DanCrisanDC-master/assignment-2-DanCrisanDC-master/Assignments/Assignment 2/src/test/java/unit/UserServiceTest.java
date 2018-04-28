package unit;

import bookStore.dto.UserDto;
import bookStore.model.Role;
import bookStore.model.User;
import bookStore.repository.UserRepository;
import bookStore.service.UserService;
import bookStore.service.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class UserServiceTest {

    private UserService userService;
    @Mock
    private UserRepository userRepository;


    @Before
    public void setup() {
        this.userService = new UserServiceImpl(userRepository);

        User user1 = new User("test00", "Parola00", Role.USER);
        User user2 = new User("test01", "Parola01", Role.USER);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        Mockito.when(userRepository.findAll()).thenReturn(users);

    }

    @Test
    public void findAll(){
        List<User> users = userService.getAll();
        Assert.assertEquals(users.size(), 2);
    }

}