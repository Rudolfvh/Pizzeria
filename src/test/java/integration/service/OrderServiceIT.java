package integration.service;

import conf.TestApplicationRunner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;
import spring.App;
import spring.service.PizzaService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(classes = {TestApplicationRunner.class, App.class})
@Transactional
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class OrderServiceIT {
    private static final String PIZZA_NAME = "Пепперони";
    @Autowired
    private PizzaService pizzaService;

    @Test
    void findByName() {
        var pizzaDto = pizzaService.find(PIZZA_NAME);
        assertTrue(pizzaDto.isPresent());
        pizzaDto.ifPresent(p -> assertEquals(3, p.getPizzaId()));
    }


}
