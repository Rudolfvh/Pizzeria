package service;


import dao.OrderRepository;
import dto.CreateOrderDto;
import dto.OrderDto;
import mapper.CreateOrderMapper;
import mapper.OrderMapper;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;

import java.util.List;
import java.util.stream.Collectors;

public class OrderService {

    private static final OrderService INSTANCE = new OrderService();
    private final CreateOrderMapper createOrderMapper = CreateOrderMapper.getInstance();
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final OrderMapper orderMapper = OrderMapper.getInstance();
    private final OrderRepository orderRepository = new OrderRepository(HibernateUtil
            .getSessionFromFactory(sessionFactory));
    public OrderDto create(CreateOrderDto orderDto) {
        var orderEntity = createOrderMapper.mapFrom(orderDto);
        return orderMapper.mapFrom(orderRepository.save(orderEntity));
    }

    public List<OrderDto> findByCustomerId(Long customerId) {
        return orderRepository.findAll().stream()
                .filter(i -> i.getCustomer().getUserId() == customerId)
                .map(orderMapper::mapFrom).collect(Collectors.toList());
    }
    public static OrderService getInstance() {
        return INSTANCE;
    }
}

