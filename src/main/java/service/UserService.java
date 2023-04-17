package service;

import dao.UserDao;
import dto.CreateUserDto;
import dto.UserDto;
import exception.ValidationException;
import lombok.NoArgsConstructor;
import mapper.CreateUserMapper;
import mapper.UserMapper;
import validation.CreateUserValidation;

import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserService {
    private static final UserService INSTANCE = new UserService();

    private final CreateUserValidation createUserValidator = CreateUserValidation.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();

    public Optional<UserDto> login(String email, String password) {
        return userDao.findByPassword(password)
                .map(userMapper::mapFrom);
    }

    public Integer create(CreateUserDto userDto) {
        var validationResult = createUserValidator.isValid(userDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var userEntity = createUserMapper.mapFrom(userDto);
        userDao.save(userEntity);
        return userEntity.getUserId();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
