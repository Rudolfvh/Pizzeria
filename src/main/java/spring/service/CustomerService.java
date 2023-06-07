package spring.service;

import dao.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.dto.CreateCustomerDto;
import spring.dto.CustomerDto;
import spring.entity.Customer;
import spring.mapper.CreateCustomerMapper;
import spring.mapper.CustomerMapper;

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

    public Optional<CustomerDto> login(String phone, String password) {
        return customerRepository.findAll()
                .stream()
                .filter(i -> i.getPhone().equals(phone)
                             && i.getPassword().equals(password))
                .map(customerMapper::mapFrom).findFirst();
    }

    public Optional<Customer> find(String phone, String password) {
        return customerRepository.findAll()
                .stream()
                .filter(i -> i.getPhone().equals(phone)
                             && i.getPassword().equals(password)).findFirst();
    }
}