package dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserDto {
    String name;
    String phone;
    String password;
    String location;
    String role;
}
