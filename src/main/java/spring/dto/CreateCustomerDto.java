package spring.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateCustomerDto {

    String name;
    String phone;
    String password;
    String role;
}

