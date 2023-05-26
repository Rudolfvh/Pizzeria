package dao;

import org.junit.jupiter.api.TestInstance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public class OrderDaoTest {
//    private final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
//
//    private final OrderDao orderDao = OrderDao.getINSTANCE();
//
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
//    void findById(){
//        @Cleanup Session session = sessionFactory.openSession();
//        session.beginTransaction();
//
//        Optional<Orders> results = orderDao.findById(1L);
//
//        assertThat(results.get().getCustomer().getPersonName().equals("Matvey"));
//
//        session.getTransaction().commit();
//    }
//
//    @Test
//    void findAllByCustomerId(){
//        @Cleanup Session session = sessionFactory.openSession();
//        session.beginTransaction();
//
//        List<Orders> results = orderDao.findAllByCustomerId(1L);
//        assertThat(results).hasSize(2);
//
//        List<Long> id = results.stream().map(Orders::getOrderid).collect(toList());
//        assertThat(id).containsExactlyInAnyOrder(1L,4L);
//
//        session.getTransaction().commit();
//    }

}
