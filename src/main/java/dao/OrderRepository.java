package dao;


import entity.Orders;

import javax.persistence.EntityManager;
import java.util.List;

public class OrderRepository extends RepositoryBase<Long, Orders> {

    public OrderRepository(EntityManager entityManager) {
        super(entityManager, Orders.class);
    }

}
