package bookStore.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class BookDto {

    @Size(min = 1)
    public String name;
    @Pattern(regexp = "^[1-9]+$")
    @Size(min = 5, max = 5, message = "ISBN has inorrect size")
    public String isbn;
}
