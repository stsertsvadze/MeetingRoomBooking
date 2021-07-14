package ge.stsertsvadze.meetingroombooking.controller;

import ge.stsertsvadze.meetingroombooking.model.dto.*;
import ge.stsertsvadze.meetingroombooking.model.dto.request.Request;
import ge.stsertsvadze.meetingroombooking.model.dto.response.Response;
import ge.stsertsvadze.meetingroombooking.model.dto.response.ResponseFailure;
import ge.stsertsvadze.meetingroombooking.model.dto.response.ResponseSuccess;
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
    public Response registerUser(@RequestBody Request<User> request) {
        User user = request.getData();
        if (userService.userExists(user.getUsername())) {
            List<ErrorMessage> errors = Collections.singletonList(new ErrorMessage("username_taken"));
            return new ResponseFailure<>(errors);
        } else {
            userService.addUser(user);
            return new ResponseSuccess<>(user);
        }
    }

    @GetMapping("/session")
    public Response getSession(@RequestHeader String authorization) {
        Optional<User> user = userService.getSession(authorization);
        if (user.isPresent()) {
            return new ResponseSuccess<>(user);
        } else {
            List<ErrorMessage> errors = Collections.singletonList(new ErrorMessage("invalid_token"));
            return new ResponseFailure<>(errors);
        }
    }

    @PostMapping("/session")
    public Response login(@RequestBody Request<User> request) {
        User user = request.getData();
        Optional<User> result = userService.getUserByCredentials(user);
        if (result.isPresent()) {
            return new ResponseSuccess<>(result.get());
        } else {
            List<ErrorMessage> errors = Collections.singletonList(new ErrorMessage("wrong_credentials"));
            return new ResponseFailure<>(errors);
        }
    }
}
