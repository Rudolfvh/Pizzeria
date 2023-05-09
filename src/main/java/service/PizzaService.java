package service;

import dao.PizzaDao;
import dto.CreatePizzaDto;
import entity.Pizza;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mapper.CreatePizzaMapper;

import java.util.Optional;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PizzaService {

    @Getter
    private static final PizzaService INSTANCE = new PizzaService();
    private  final CreatePizzaMapper createPizzaMapper = CreatePizzaMapper.getInstance();
    private final PizzaDao pizzaDao = PizzaDao.getINSTANCE();


    public Long create(CreatePizzaDto pizzaDto) {
        var pizzaEntity = createPizzaMapper.mapFrom(pizzaDto);
        pizzaDao.save(pizzaEntity);
        return pizzaEntity.getPizzaId();

    }

    public Optional<Pizza> find(String name){
        return pizzaDao.findByName(name).map(i -> Pizza.builder()
                .pizzaId(i.getPizzaId())
                .name(i.getName())
                .cost(i.getCost())
                .build());
    }

}
