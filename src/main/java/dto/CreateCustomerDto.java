package dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Value
@Builder
public class CreateCustomerDto {
    @Valid
    String name;
    @NotNull
    String phone;
    @NotNull
    String password;
    String role;
}

