package mapper;

import dto.CustomerDto;
import entity.Customer;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CustomerMapper implements Mapper<Customer, CustomerDto> {

    private static final CustomerMapper INSTANCE = new CustomerMapper();

    @Override
    public CustomerDto mapFrom(Customer object) {
        return CustomerDto.builder()
                .id(object.getUserId())
                .name(object.getPersonName())
                .phone(object.getPhone())
                .location(object.getLocation())
                .role(object.getRole())
                .build();
    }

    public static CustomerMapper getInstance() {
        return INSTANCE;
    }
}