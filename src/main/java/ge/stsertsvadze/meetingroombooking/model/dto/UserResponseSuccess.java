package ge.stsertsvadze.meetingroombooking.model.dto;

import ge.stsertsvadze.meetingroombooking.model.entity.User;

public class UserResponseSuccess implements Response {
    private User result;

    public UserResponseSuccess(User result) {
        this.result = result;
    }

    public User getResult() {
        return result;
    }

    public void setResult(User result) {
        this.result = result;
    }
}
