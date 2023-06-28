package spring.mapper;


import org.springframework.stereotype.Component;
import spring.dto.CreatePizzaDto;
import spring.database.entity.Pizza;


@Component
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
