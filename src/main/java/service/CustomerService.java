package service;

import dao.CustomerRepository;
import dto.CreateCustomerDto;
import dto.CustomerDto;
import entity.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mapper.CreateCustomerMapper;
import mapper.CustomerMapper;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;

import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CustomerService {
    @Getter
    private static final CustomerService INSTANCE = new CustomerService();
    private final CreateCustomerMapper createCustomerMapper = CreateCustomerMapper.getInstance();
    private final CustomerMapper customerMapper = CustomerMapper.getInstance();
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final CustomerRepository customerRepository = new CustomerRepository(HibernateUtil
            .getSessionFromFactory(sessionFactory));

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