package mapper;

import dto.OrderDto;
import entity.Orders;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class OrderMapper implements Mapper<Orders, OrderDto> {

    private static final OrderMapper INSTANCE = new OrderMapper();

    @Override
    public OrderDto mapFrom(Orders object) {
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
