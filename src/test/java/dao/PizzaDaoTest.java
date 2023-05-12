package dao;

import entity.Customer;
import entity.Pizza;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utils.HibernateUtil;
import utils.TestDataImporter;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public class PizzaDaoTest {

    private final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    private final PizzaDao pizzaDao = PizzaDao.getINSTANCE();

    @BeforeAll
    public void initDb() {
        TestDataImporter.importData(sessionFactory);
        System.out.println();
    }

    @AfterAll
    public void finish() {
        sessionFactory.close();
    }

    @Test
    void findAll() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Pizza> results = pizzaDao.findAll();
        assertThat(results).hasSize(6);

        List<String> names = results.stream().map(Pizza::getName).collect(toList());
        assertThat(names).containsExactlyInAnyOrder("Цыплёнок барбекю", "Ранч пицца", "Острая чили",
                "Пепперони","Цыплёнок дорблю","Гавайская");

        session.getTransaction().commit();
    }

    @Test
    void findId(){
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Long results = pizzaDao.findId("Ранч пицца");

        assertThat(results).isEqualTo(2);

        session.getTransaction().commit();
    }
}
