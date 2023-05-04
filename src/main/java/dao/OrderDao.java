package dao;

import entity.Orders;
import lombok.Cleanup;
import lombok.Getter;
import utils.SessionManager;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class OrderDao implements Dao<Long, Orders>{

    @Getter
    private static final OrderDao INSTANCE = new OrderDao();
    @Override
    public Optional<Orders> findById(Long id) {
        @Cleanup var session = SessionManager.get();
        session.beginTransaction();
        Orders orders = session.get(Orders.class, id);
        session.getTransaction().commit();
        return orders != null
                ? Optional.of(orders)
                : Optional.empty();
    }

    @Override
    public List<Orders> findAll() {
        @Cleanup var session = SessionManager.get();
        session.beginTransaction();
        Query query = session.createQuery(" FROM " + Orders.class.getSimpleName());
        session.getTransaction().commit();
        return (List<Orders>) query.getResultList();
    }

    public List<Orders> findAllByCustomerId(Long customerId) {
        var orders = findAll();
        orders.removeIf(order -> !order.getCustomerId().equals(customerId));
        return orders;
    }
}
