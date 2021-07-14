package ge.stsertsvadze.meetingroombooking.model.dto.request;

public class SessionDto {
    private String authorization;

    public SessionDto() {}

    public SessionDto(String authorization) {
        this.authorization = authorization;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}
