package ge.stsertsvadze.meetingroombooking.model.dto.request;

public class Request<T> {
    private T data;

    public Request() {}

    public Request(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
