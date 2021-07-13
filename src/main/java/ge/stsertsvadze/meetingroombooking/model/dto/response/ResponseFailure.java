package ge.stsertsvadze.meetingroombooking.model.dto.response;

public class ResponseFailure<T> extends Response {
    private T errors;

    public ResponseFailure() {}

    public ResponseFailure(T errors) {
        this.errors = errors;
    }

    public T getErrors() {
        return errors;
    }

    public void setErrors(T errors) {
        this.errors = errors;
    }
}
