package mapper;

import dto.OrderDto;
import entity.Order;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
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
