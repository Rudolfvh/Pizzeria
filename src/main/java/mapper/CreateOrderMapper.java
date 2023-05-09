package mapper;

import dto.CreateOrderDto;
import entity.Orders;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateOrderMapper implements Mapper<CreateOrderDto, Orders> {

    private static final CreateOrderMapper INSTANCE = new CreateOrderMapper();

    @Override
    public Orders mapFrom(CreateOrderDto object) {
        return Orders.builder()
                .customer(object.getCustomer())
                .pizza(object.getPizza())
                .dateGet(object.getDateGet())
                .build();
    }

    public static CreateOrderMapper getInstance() {
        return INSTANCE;
    }

}
