package spring.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateCustomerDto {

    @Size(min = 2, max = 10, message = "Name must be 2-10 characters long")
    String name;

    @Pattern(regexp = "\\+(375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})",
            message = "Phone doesn't exist")
    String phone;

    @Size(min = 8, max = 16, message = "Name must be 8-16 characters long")
    String password;
    String role;
}

