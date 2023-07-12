package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import spring.database.repository.PizzaRepository;
import org.springframework.stereotype.Service;
import spring.database.entity.Pizza;
import spring.dto.CreatePizzaDto;
import spring.dto.PizzaDto;
import spring.mapper.CreatePizzaMapper;
import spring.mapper.PizzaMapper;

import java.util.List;
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

    public PizzaDto create(CreatePizzaDto pizzaDto){
        var pizzaEntity = createPizzaMapper.mapFrom(pizzaDto);
        return pizzaMapper.mapFrom(pizzaRepository.save(pizzaEntity));
    }
    public List<Pizza> findAll(){
        return pizzaRepository.findAll();
    }

    public Optional<Pizza> find(String name){
        return pizzaRepository.findAll().stream()
                .filter(i -> i.getName().equals(name))
                .findFirst();
    }

}

