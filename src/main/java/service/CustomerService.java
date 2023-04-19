package service;

import dao.CustomerDao;
import dto.CreateCustomerDto;
import dto.CustomerDto;
import exception.ValidationException;
import lombok.NoArgsConstructor;
import mapper.CreateCustomerMapper;
import mapper.CustomerMapper;
import validation.CreateCustomerValidation;

import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CustomerService {
    private static final CustomerService INSTANCE = new CustomerService();

    private final CreateCustomerValidation createCustomerValidation = CreateCustomerValidation.getInstance();
    private final CustomerDao customerDao = CustomerDao.getInstance();
    private final CreateCustomerMapper createCustomerMapper = CreateCustomerMapper.getInstance();
    private final CustomerMapper customerMapper = CustomerMapper.getInstance();

    public Optional<CustomerDto> login(String password) {
        return customerDao.findByPassword(password)
                .map(customerMapper::mapFrom);
    }

    public Integer create(CreateCustomerDto createCustomerDto) {
        var validationResult = createCustomerValidation.isValid(createCustomerDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var customerEntity = createCustomerMapper.mapFrom(createCustomerDto);
        customerDao.save(customerEntity);
        return customerEntity.getUserId();
    }

    public static CustomerService getInstance() {
        return INSTANCE;
    }
}
