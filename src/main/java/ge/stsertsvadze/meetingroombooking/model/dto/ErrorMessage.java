package ge.stsertsvadze.meetingroombooking.model.dto;

public class ErrorMessage {
    private String keyword;

    public ErrorMessage(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
