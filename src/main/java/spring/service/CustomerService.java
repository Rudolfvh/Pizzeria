package spring.service;

import spring.database.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.dto.CreateCustomerDto;
import spring.dto.CustomerDto;
import spring.database.entity.Customer;
import spring.dto.LoginDto;
import spring.mapper.CreateCustomerMapper;
import spring.mapper.CustomerMapper;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {
    private final CreateCustomerMapper createCustomerMapper;
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper,
                           CreateCustomerMapper createCustomerMapper){
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.createCustomerMapper = createCustomerMapper;
    }

    public CustomerDto create(CreateCustomerDto customerDto) {
        var customerEntity = createCustomerMapper.mapFrom(customerDto);
        return customerMapper.mapFrom(customerRepository.save(customerEntity));
    }

    public Optional<CustomerDto> login(LoginDto loginDto) {
        return customerRepository.findByPhoneAndPassword(loginDto.phone(),loginDto.password())
                .map(customerMapper::mapFrom);
    }

    public Optional<Customer> find(String phone) {
        return customerRepository.findAll()
                .stream()
                .filter(i -> i.getPhone().equals(phone)).findFirst();
    }
    public List<CustomerDto> findAll() {
        return customerRepository.findAll().stream()
                .map(customerMapper::mapFrom).toList();
    }
}