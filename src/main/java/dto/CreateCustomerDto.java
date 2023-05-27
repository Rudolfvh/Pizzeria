package dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Value
@Builder
public class CreateCustomerDto {
    @NotNull
    @NotBlank
    String name;
    @NotEmpty
    String phone;
    @NotNull
    String password;
    String role;
}

