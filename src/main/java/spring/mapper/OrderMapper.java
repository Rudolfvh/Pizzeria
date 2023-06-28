package spring.mapper;

import org.springframework.stereotype.Component;
import spring.dto.OrderDto;
import spring.database.entity.Order;


@Component
public class OrderMapper implements Mapper<Order, OrderDto> {

    private static final OrderMapper INSTANCE = new OrderMapper();

    @Override
    public OrderDto mapFrom(Order object) {
        return OrderDto.builder()
                .customer(object.getCustomer())
                .pizza(object.getPizza())
                .dateGet(object.getDateGet())
                .build();
    }

    public static OrderMapper getInstance() {
        return INSTANCE;
    }
}
