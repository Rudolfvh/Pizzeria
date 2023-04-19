package mapper;

import dto.CreateCustomerDto;
import entity.Customer;
import entity.Role;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateCustomerMapper implements Mapper<CreateCustomerDto, Customer> {

    private static final CreateCustomerMapper INSTANCE = new CreateCustomerMapper();

    @Override
    public Customer mapFrom(CreateCustomerDto object) {
        return Customer.builder()
                .personName(object.getName())
                .password(object.getPassword())
                .phone(object.getPhone())
                .location(object.getLocation())
                .role(Role.valueOf(object.getRole()))
                .build();
    }

    public static CreateCustomerMapper getInstance() {
        return INSTANCE;
    }
}