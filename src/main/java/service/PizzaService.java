package service;

import dao.PizzaRepository;
import dto.CreatePizzaDto;
import dto.PizzaDto;
import entity.Pizza;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mapper.CreatePizzaMapper;
import mapper.PizzaMapper;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;

import java.util.Optional;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PizzaService {

    @Getter
    private static final PizzaService INSTANCE = new PizzaService();
    private  final CreatePizzaMapper createPizzaMapper = CreatePizzaMapper.getInstance();
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final PizzaMapper pizzaMapper = PizzaMapper.getInstance();
    private final PizzaRepository pizzaRepository = new PizzaRepository(HibernateUtil
            .getSessionFromFactory(sessionFactory));

    public PizzaDto create(CreatePizzaDto pizzaDto) {
        var pizzaEntity = createPizzaMapper.mapFrom(pizzaDto);
        return pizzaMapper.mapFrom(pizzaRepository.save(pizzaEntity));

    }

    public Optional<Pizza> find(String name){
        return pizzaRepository.findAll().stream()
                .filter(i -> i.getName().equals(name))
                .findFirst();
    }

}

