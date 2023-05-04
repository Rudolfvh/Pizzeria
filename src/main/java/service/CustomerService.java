package service;

import dao.CustomerDao;
import dto.CreateCustomerDto;
import dto.CustomerDto;
import exception.ValidationException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mapper.CreateCustomerMapper;
import mapper.CustomerMapper;
import validation.CreateCustomerValidation;

import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CustomerService {
    @Getter
    private static final CustomerService INSTANCE = new CustomerService();

    private final CreateCustomerValidation createCustomerValidation = CreateCustomerValidation.getInstance();
    private final CustomerDao customerDao = CustomerDao.getInstance();
    private final CreateCustomerMapper createCustomerMapper = CreateCustomerMapper.getInstance();
    private final CustomerMapper customerMapper = CustomerMapper.getInstance();

    public Long create(CreateCustomerDto customerDto) {
        var validationResult = createCustomerValidation.isValid(customerDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var customerEntity = createCustomerMapper.mapFrom(customerDto);
        customerDao.save(customerEntity);
        return customerEntity.getUserId();
    }
    public Long find(String phone, String password){
        return customerDao.findId(phone,password);
    }

    public Optional<CustomerDto> login(String phone, String password) {
        return customerDao.findByPhoneAndPassword(phone, password)
                .map(customerMapper::mapFrom);
    }
}
