package validation;

import spring.dto.CreateCustomerDto;
import spring.entity.Role;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateCustomerValidation implements Validator<CreateCustomerDto> {

    private static final CreateCustomerValidation INSTANCE = new CreateCustomerValidation();

    private static  ValidatorResult validationResult = new ValidatorResult();

    @Override
    public ValidatorResult isValid(CreateCustomerDto object) {
        if (Role.find(object.getRole()).isEmpty()) {
             validationResult.add(Error.of("invalid.role", "Role is invalid"));
        }
        return validationResult;
    }

    public static CreateCustomerValidation getInstance() {
        return INSTANCE;
    }
}
