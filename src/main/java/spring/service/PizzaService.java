package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import spring.repository.PizzaRepository;
import org.springframework.stereotype.Service;
import spring.entity.Pizza;
import spring.mapper.CreatePizzaMapper;
import spring.mapper.PizzaMapper;

import java.util.Optional;


@Service
public class PizzaService {


    private  final CreatePizzaMapper createPizzaMapper;
    private final PizzaMapper pizzaMapper;
    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(CreatePizzaMapper createPizzaMapper, PizzaMapper pizzaMapper,
                        PizzaRepository pizzaRepository){
        this.createPizzaMapper = createPizzaMapper;
        this.pizzaMapper = pizzaMapper;
        this.pizzaRepository = pizzaRepository;
    }

    public Optional<Pizza> find(String name){
        return pizzaRepository.findAll().stream()
                .filter(i -> i.getName().equals(name))
                .findFirst();
    }

}

