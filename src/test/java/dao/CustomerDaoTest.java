package dao;

import entity.Customer;
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
public class CustomerDaoTest {

    private final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    private final CustomerDao customerDao = CustomerDao.getInstance();

    @Test
    void findAll() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Customer> results = customerDao.findAll();
        assertThat(results).hasSize(4);

        List<String> names = results.stream().map(Customer::getPersonName).collect(toList());
        assertThat(names).containsExactlyInAnyOrder("Matvey", "Maks", "Ihor", "Georgy");

        session.getTransaction().commit();
    }

    @Test
    void findByPhoneAndPassword() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Optional<Customer> results = customerDao.findByPhoneAndPassword("+375333827732",
                "96321");

        assertThat(results.equals("Matvey"));

        session.getTransaction().commit();
    }

    @Test
    void findId(){
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Long results = customerDao.findId("+375333827732",
                "96321");
        assertThat(results).isEqualTo(1);


        session.getTransaction().commit();
    }

    @BeforeAll
    public void initDb() {
        TestDataImporter.importData(sessionFactory);
        System.out.println();
    }

    @AfterAll
    public void finish() {
        sessionFactory.close();
    }
}
