package ru.practicum.app.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;


@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {

        this.userRepository = userRepository;

        this.userMapper = userMapper;
    }

    @Override
    public UserDto create(UserDto userDto) {
        if(userDto.getName() == null || userDto.getEmail() == null){
            throw new UserCastomException("Неверные данные");
        }
        User user = userRepository.save(userMapper.mapToUser(userDto));
        log.info("Пользователь создан");
        return userMapper.mapToUserDto(user);
    }
    @Override
    public HttpStatus delete(long userId) {
        log.info("Пользователь удален");
        userRepository.deleteById(userId);
        return HttpStatus.OK;
    }
    @Override
    public List<UserDto> getUser(String[] ids, Integer from, Integer size) {
        checkIds(ids);

        if (ids == null){
          throw new UserCastomException("Неправильный формат ввода");
        }
            long[] array = Arrays.stream(ids).mapToLong(Long::parseLong).toArray();

        List<UserDto> returnList = new ArrayList<>();


        if (size != null) {
            Pageable page = PageRequest.of(from, size);
            returnList = userMapper.maptoAllUserDto(userRepository.findAllById(array,page));
        } else {
            for (long x : array) {
                returnList.add(userMapper.mapToUserDto(userRepository.findById(x).orElseThrow(() ->
                        new UserCastomException("Пользователь не найден"))));
            }
        }
        return returnList;
    }

    private void checkIds(String[] ids) {
        boolean exceptionFrom =false;
        try {
            for (String x : ids) {
                Long.parseLong(x);
            }
        } catch (NumberFormatException e) {
            exceptionFrom = true;
            throw new UserCastomException("Input String cannot be parsed to Integer.");
        }

        if (exceptionFrom == true){
            throw new UserCastomException("Input String cannot be parsed to Integer.");
        }
    }
}