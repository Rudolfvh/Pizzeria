package dao;

import entity.Delivery;
import lombok.Cleanup;
import lombok.Getter;
import utils.SessionManager;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class DeliveryDao implements Dao<Long, Delivery>{
    @Getter
    private static final DeliveryDao INSTANCE = new DeliveryDao();


    @Override
    public Optional<Delivery> findById(Long id) {
        @Cleanup var session = SessionManager.get();
        session.beginTransaction();
        Delivery delivery = session.get(Delivery.class, id);
        session.getTransaction().commit();
        return delivery != null
                ? Optional.of(delivery)
                : Optional.empty();
    }

    @Override
    public List<Delivery> findAll() {
        @Cleanup var session = SessionManager.get();
        session.beginTransaction();
        Query query = session.createQuery(" FROM " + Delivery.class.getSimpleName());
        session.getTransaction().commit();
        return (List<Delivery>) query.getResultList();
    }

    public List<Delivery> findAllByOrderId(Long orderId) {
        var deliveries = findAll();
        deliveries.removeIf(delivery -> !delivery.getOrderId().equals(orderId));
        return deliveries;
    }
}
