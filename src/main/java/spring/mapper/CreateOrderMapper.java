package spring.mapper;

import org.springframework.stereotype.Component;
import spring.dto.CreateOrderDto;
import spring.entity.Order;

@Component
public class CreateOrderMapper implements Mapper<CreateOrderDto, Order> {

    private static final CreateOrderMapper INSTANCE = new CreateOrderMapper();

    @Override
    public Order mapFrom(CreateOrderDto object) {
        return Order.builder()
                .customer(object.getCustomer())
                .pizza(object.getPizza())
                .dateGet(object.getDateGet())
                .build();
    }

    public static CreateOrderMapper getInstance() {
        return INSTANCE;
    }

}
