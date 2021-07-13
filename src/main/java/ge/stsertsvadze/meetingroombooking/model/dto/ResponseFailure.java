package ge.stsertsvadze.meetingroombooking.model.dto;

import java.util.List;

public class ResponseFailure implements Response {
    private List<ErrorMessage> errors;

    public ResponseFailure(List<ErrorMessage> errors) {
        this.errors = errors;
    }

    public List<ErrorMessage> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorMessage> errors) {
        this.errors = errors;
    }
}
