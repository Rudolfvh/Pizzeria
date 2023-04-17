package dto;

import entity.Role;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {
    Integer id;
    String name;
    String phone;
    String location;
    Role role;
}
