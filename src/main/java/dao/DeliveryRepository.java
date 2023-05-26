package dao;

import entity.Delivery;

import javax.persistence.EntityManager;

public class DeliveryRepository extends RepositoryBase<Long, Delivery>{
    public DeliveryRepository(EntityManager entityManager) {
        super(entityManager, Delivery.class);
    }
}
