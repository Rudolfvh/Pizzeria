package service;


import dao.OrderDao;
import dto.CreateCustomerDto;
import dto.CreateOrderDto;
import dto.OrderDto;
import exception.ValidationException;
import mapper.CreateOrderMapper;
import mapper.OrderMapper;

import java.util.List;
import java.util.stream.Collectors;

public class OrderService {

    private static final OrderService INSTANCE = new OrderService();
    private final OrderDao orderDao = OrderDao.getINSTANCE();
    private final CreateOrderMapper createOrderMapper = CreateOrderMapper.getInstance();

    public Long create(CreateOrderDto orderDto) {
        var orderEntity = createOrderMapper.mapFrom(orderDto);
        orderDao.save(orderEntity);
        return orderEntity.getOrderid();
    }

//    public List<OrderDto> findAllByCustomerId(Long customerId) {
//        return orderDao.findAllByCustomerId(customerId).stream().map(
//                order -> new OrderDto(
//                        order.getCustomerId(),
//                        order.getPizzaId(),
//                        order.getDateGet()
//                )
//        ).collect(Collectors.toList());
//    }
    public static OrderService getInstance() {
        return INSTANCE;
    }
}
