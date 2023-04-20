package mapper;


import dto.CreatePizzaDto;
import entity.Pizza;
import entity.Role;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreatePizzaMapper implements Mapper<CreatePizzaDto, Pizza> {

    private static final CreatePizzaMapper INSTANCE = new CreatePizzaMapper();

    @Override
    public Pizza mapFrom(CreatePizzaDto object) {
        return Pizza.builder()
                .name(object.getName())
                .cost(object.getCost())
                .build();
    }

    public static CreatePizzaMapper getInstance() {
        return INSTANCE;
    }

}
