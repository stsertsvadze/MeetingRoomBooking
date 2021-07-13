package ge.stsertsvadze.meetingroombooking.model.dto.response;

public class ResponseDataHolder<T> {
    private T data;

    public ResponseDataHolder() {}

    public ResponseDataHolder(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
