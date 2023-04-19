package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    private Integer userId;
    private String personName;
    private String password;
    private String phone;
    private String location;
    private Role role;
}
