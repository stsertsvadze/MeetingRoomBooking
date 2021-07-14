package ge.stsertsvadze.meetingroombooking.model.dto.request;

public class UserDto extends ValidDto {
    private String username;
    private String password;
    private String fullName;

    public UserDto() {}

    public UserDto(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    boolean isValid() {
        return username != null && password != null;
    }
}
