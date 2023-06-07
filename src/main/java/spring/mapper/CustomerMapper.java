package spring.mapper;

import org.springframework.stereotype.Component;
import spring.dto.CustomerDto;
import spring.entity.Customer;

@Component
public class CustomerMapper implements Mapper<Customer, CustomerDto> {

    @Override
    public CustomerDto mapFrom(Customer object) {
        return CustomerDto.builder()
                .id(object.getUserId().intValue())
                .name(object.getPersonName())
                .password(object.getPassword())
                .phone(object.getPhone())
                .role(object.getRole())
                .build();
    }

}