package dao;

import org.junit.jupiter.api.TestInstance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public class PizzaDaoTest {

//    private final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
//    private final PizzaDao pizzaDao = PizzaDao.getINSTANCE();
//    @BeforeAll
//    public void initDb() {
//        TestDataImporter.importData(sessionFactory);
//        System.out.println();
//    }
//
//    @AfterAll
//    public void finish() {
//        sessionFactory.close();
//    }
//
//    @Test
//    void findAll() {
//        @Cleanup Session session = sessionFactory.openSession();
//        session.beginTransaction();
//
//        List<Pizza> results = pizzaDao.findAll();
//        assertThat(results).hasSize(6);
//
//        List<String> names = results.stream().map(Pizza::getName).collect(toList());
//        assertThat(names).containsExactlyInAnyOrder("Цыплёнок барбекю", "Ранч пицца", "Острая чили",
//                "Пепперони","Цыплёнок дорблю","Гавайская");
//
//        session.getTransaction().commit();
//    }
//
//    @Test
//    void findId(){
//        @Cleanup Session session = sessionFactory.openSession();
//        session.beginTransaction();
//
//        Long results = pizzaDao.findId("Ранч пицца");
//
//        assertThat(results).isEqualTo(2);
//
//        session.getTransaction().commit();
//    }
}
