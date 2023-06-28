package spring.mapper;

import org.springframework.stereotype.Component;
import spring.dto.CreateCustomerDto;
import spring.database.entity.Customer;
import spring.database.entity.Role;


@Component
public class CreateCustomerMapper implements Mapper<CreateCustomerDto, Customer> {

    @Override
    public Customer mapFrom(CreateCustomerDto object) {
        return Customer.builder()
                .personName(object.getName())
                .password(object.getPassword())
                .phone(object.getPhone())
                .role(Role.valueOf(object.getRole()))
                .build();
    }

}