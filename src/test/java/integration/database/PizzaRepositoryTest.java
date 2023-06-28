package integration.database;

import conf.TestApplicationRunner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;
import spring.database.entity.Pizza;
import spring.database.repository.PizzaRepository;
import spring.utils.ApplicationRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {TestApplicationRunner.class, ApplicationRunner.class})
@Transactional
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class PizzaRepositoryTest {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Test
    void findAll() {

        List<Pizza> results = pizzaRepository.findAll();
        assertThat(results).hasSize(6);

        List<String> pizzaName = results.stream().map(Pizza::getName).collect(Collectors.toList());
        assertThat(pizzaName).containsExactlyInAnyOrder("Цыплёнок барбекю",
                "Ранч пицца",
                "Острая чили",
                "Пепперони",
                "Цыплёнок дорблю",
                "Гавайская");
    }

}
