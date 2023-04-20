package service;

import dao.PizzaDao;
import dto.CreatePizzaDto;
import dto.PizzaDto;
import exception.ValidationException;
import mapper.CreatePizzaMapper;
import mapper.PizzaMapper;
import validation.CreatePizzaValidation;

import java.util.Optional;


public class PizzaService {

    private static final PizzaService INSTANCE = new PizzaService();

    private final CreatePizzaValidation createPizzaValidation = CreatePizzaValidation.getInstance();
    private final PizzaDao pizzaDao = PizzaDao.getInstance();
    private final CreatePizzaMapper createPizzaMapper = CreatePizzaMapper.getInstance();
    private final PizzaMapper pizzaMapper = PizzaMapper.getInstance();

    public Integer find(String name) {
        return pizzaDao.findByName(name);
    }
    public Integer create(CreatePizzaDto createPizzaDto) {
        var validationResult = createPizzaValidation.isValid(createPizzaDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var pizzaEntity = createPizzaMapper.mapFrom(createPizzaDto);
        pizzaDao.save(pizzaEntity);
        return pizzaEntity.getPizzaId();
    }

    public static PizzaService getInstance() {
        return INSTANCE;
    }
}
