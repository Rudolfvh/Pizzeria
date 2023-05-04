package mapper;

import dto.PizzaDto;
import entity.Pizza;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
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
