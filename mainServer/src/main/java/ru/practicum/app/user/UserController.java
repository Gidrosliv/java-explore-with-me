package ru.practicum.app.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping(value = "/admin/users")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto create(@RequestBody @Valid UserDto userDto) {
        return userService.create(userDto);
    }


    @GetMapping
    public List<UserDto> getUser(@RequestParam("ids") @NotNull String[] ids,
                                 @RequestParam(required = false) Integer from,
                                 @RequestParam(required = false) Integer size) {
        return userService.getUser(ids, from, size);
    }

    @DeleteMapping(value = "/{userId}")
    public HttpStatus delete(@PathVariable int userId) {
        return userService.delete(userId);
    }

}
