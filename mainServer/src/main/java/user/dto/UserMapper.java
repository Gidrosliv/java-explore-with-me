package user.dto;

import user.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserMapper {

    public static User mapToUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());

        return user;
    }

    public static UserDto mapToUserDto(User user) {

        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public static List<UserDto> maptoAllUserDto(Collection<User> users) {
        List<UserDto> dtos = new ArrayList<>();
        for (User user : users) {
            dtos.add(mapToUserDto(user));
        }

        return dtos;
    }

}