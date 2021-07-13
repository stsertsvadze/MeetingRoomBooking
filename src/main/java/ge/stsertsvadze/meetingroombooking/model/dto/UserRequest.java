package ge.stsertsvadze.meetingroombooking.model.dto;

import ge.stsertsvadze.meetingroombooking.model.entity.User;

public class UserRequest {
    private User data;

    public UserRequest() {}

    public UserRequest(User data) {
        this.data = data;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
