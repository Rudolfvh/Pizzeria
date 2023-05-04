package dao;

import entity.Customer;
import lombok.*;
import utils.SessionManager;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
public class CustomerDao implements Dao<Long, Customer> {

    @Getter
    private static final CustomerDao Instance = new CustomerDao();

    @SneakyThrows
    public Optional<Customer> findByPhoneAndPassword(String phone, String password) {
        return findAll()
                .stream()
                .filter(customer -> (customer.getPhone().equals(phone) && customer.getPassword().equals(password)))
                .findAny();
    }

    public Long findId(String phone, String password){
        return findByPhoneAndPassword(phone,password).get().getUserId();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        @Cleanup var session = SessionManager.get();
        session.beginTransaction();
        Customer customer = session.get(Customer.class, id);
        session.getTransaction().commit();
        return customer != null
                ? Optional.of(customer)
                : Optional.empty();
    }

    @Override
    public List<Customer> findAll() {
        @Cleanup var session = SessionManager.get();
        session.beginTransaction();
        Query query = session.createQuery(" FROM " + Customer.class.getSimpleName());
        session.getTransaction().commit();
        return (List<Customer>) query.getResultList();
    }
}
