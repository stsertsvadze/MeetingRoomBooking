package ge.stsertsvadze.meetingroombooking.controller;

import ge.stsertsvadze.meetingroombooking.model.dto.*;
import ge.stsertsvadze.meetingroombooking.model.entity.User;
import ge.stsertsvadze.meetingroombooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/v1")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public Response registerUser(@RequestBody UserRequest userRequest) {
        User user = userRequest.getData();
        if (userService.userExists(user.getUsername())) {
            List<ErrorMessage> errors = Collections.singletonList(new ErrorMessage("username_taken"));
            return new UserResponseFailure(errors);
        } else {
            userService.addUser(user);
            return new UserResponseSuccess(user);
        }
    }

    @PostMapping("/session")
    public Response login(@RequestBody UserRequest userRequest) {
        User user = userRequest.getData();
        Optional<User> result = userService.getUserByCredentials(user);
        if (result.isPresent()) {
            return new UserResponseSuccess(result.get());
        } else {
            List<ErrorMessage> errors = Collections.singletonList(new ErrorMessage("wrong_credentials"));
            return new UserResponseFailure(errors);
        }
    }
}
