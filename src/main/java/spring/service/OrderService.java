package spring.service;


import dao.OrderRepository;
import org.springframework.stereotype.Service;
import spring.dto.OrderDto;
import spring.mapper.CreateOrderMapper;
import spring.mapper.OrderMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {


    private final CreateOrderMapper createOrderMapper;
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    public OrderService(CreateOrderMapper createOrderMapper, OrderMapper orderMapper,
    OrderRepository orderRepository){
        this.createOrderMapper = createOrderMapper;
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
    }

    public List<OrderDto> findByCustomerId(Long customerId) {
        return orderRepository.findAll().stream()
                .filter(i -> i.getCustomer().getUserId() == customerId)
                .map(orderMapper::mapFrom).collect(Collectors.toList());
    }
}

