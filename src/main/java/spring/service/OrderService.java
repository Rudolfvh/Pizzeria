package spring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import spring.database.repository.OrderRepository;
import org.springframework.stereotype.Service;
import spring.dto.CreateCustomerDto;
import spring.dto.CreateOrderDto;
import spring.dto.CustomerDto;
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

    @Autowired
    public OrderService(CreateOrderMapper createOrderMapper, OrderMapper orderMapper,
    OrderRepository orderRepository){
        this.createOrderMapper = createOrderMapper;
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
    }

    public OrderDto create(CreateOrderDto orderDto) {
        var orderEntity = createOrderMapper.mapFrom(orderDto);
        return orderMapper.mapFrom(orderRepository.save(orderEntity));
    }
    public List<OrderDto> findByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId.intValue()).stream()
                .map(orderMapper::mapFrom).collect(Collectors.toList());
    }
}

