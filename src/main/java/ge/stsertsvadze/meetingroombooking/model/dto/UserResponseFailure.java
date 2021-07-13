package ge.stsertsvadze.meetingroombooking.model.dto;

import java.util.List;

public class UserResponseFailure implements Response {
    private List<ErrorMessage> errors;

    public UserResponseFailure(List<ErrorMessage> errors) {
        this.errors = errors;
    }

    public List<ErrorMessage> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorMessage> errors) {
        this.errors = errors;
    }
}
