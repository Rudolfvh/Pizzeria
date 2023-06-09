package dto;

import entity.Role;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CustomerDto {
    Integer id;
    String name;
    String password;
    String phone;
    Role role;
}
