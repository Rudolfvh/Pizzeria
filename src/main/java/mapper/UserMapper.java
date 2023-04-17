package mapper;

import dto.UserDto;
import entity.User;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserMapper implements Mapper<User, UserDto> {

    private static final UserMapper INSTANCE = new UserMapper();

    @Override
    public UserDto mapFrom(User object) {
        return UserDto.builder()
                .id(object.getUserId())
                .name(object.getPersonName())
                .phone(object.getPhone())
                .location(object.getLocation())
                .role(object.getRole())
                .build();
    }

    public static UserMapper getInstance() {
        return INSTANCE;
    }
}