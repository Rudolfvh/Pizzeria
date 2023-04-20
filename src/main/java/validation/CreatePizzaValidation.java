package validation;

import dto.CreatePizzaDto;
import entity.Role;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreatePizzaValidation implements Validator<CreatePizzaDto> {

    private static final CreatePizzaValidation INSTANCE = new CreatePizzaValidation();

    private static  ValidatorResult validationResult = new ValidatorResult();

    @Override
    public ValidatorResult isValid(CreatePizzaDto object) {
        if (Role.find(object.getName()).isEmpty()) {
            validationResult.add(Error.of("invalid.pizza", "Pizza is invalid"));
        }
        return validationResult;
    }

    public static CreatePizzaValidation getInstance() {
        return INSTANCE;
    }
}
