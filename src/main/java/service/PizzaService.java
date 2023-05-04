package service;

import dao.PizzaDao;
import dto.CreatePizzaDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mapper.CreatePizzaMapper;


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

    public Long find(String name){
        return pizzaDao.findByName(name).get().getPizzaId();
    }

}
