package mapper;

import dto.CreateOrderDto;
import entity.Order;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
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
