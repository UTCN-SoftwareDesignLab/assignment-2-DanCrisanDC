package bookStore.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto {

    @Size(min = 6, message = "Username must be at least 6 characters long")
    public String username;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z0-9@$!%*#?&]+$", message = "Password must contain at least 1 letter, 1 number, and 1 special character")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    public String password;
}
