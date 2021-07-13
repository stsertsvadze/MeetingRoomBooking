package ge.stsertsvadze.meetingroombooking.model.dto.response;

public class ResponseSuccess<T> extends Response {
    private ResponseDataHolder<T> result;

    public ResponseSuccess() {}

    public ResponseSuccess(T data) {
        this.result = new ResponseDataHolder<>(data);
    }

    public ResponseDataHolder<T> getResult() {
        return result;
    }

    public void setResult(ResponseDataHolder<T> result) {
        this.result = result;
    }
}
