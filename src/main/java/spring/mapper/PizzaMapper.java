package spring.mapper;

import org.springframework.stereotype.Component;
import spring.dto.PizzaDto;
import spring.database.entity.Pizza;


@Component
public class PizzaMapper implements Mapper<Pizza, PizzaDto> {

    private static final PizzaMapper INSTANCE = new PizzaMapper();

    @Override
    public PizzaDto mapFrom(Pizza object) {
        return PizzaDto.builder()
                .pizzaId(object.getPizzaId().intValue())
                .name(object.getName())
                .cost(object.getCost())
                .build();
    }

    public static PizzaMapper getInstance() {
        return INSTANCE;
    }
}
