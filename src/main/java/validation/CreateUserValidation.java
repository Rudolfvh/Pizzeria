package validation;

import dto.CreateUserDto;
import entity.Role;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateUserValidation implements Validator<CreateUserDto> {

    private static final CreateUserValidation INSTANCE = new CreateUserValidation();

    private static  ValidatorResult validationResult = new ValidatorResult();

    @Override
    public ValidatorResult isValid(CreateUserDto object) {
        if (Role.find(object.getRole()).isEmpty()) {
             validationResult.add(Error.of("invalid.role", "Role is invalid"));
        }
        return validationResult;
    }

    public static CreateUserValidation getInstance() {
        return INSTANCE;
    }
}
