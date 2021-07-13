package ge.stsertsvadze.meetingroombooking.model.dto;

public class ErrorMessage {
    private String keyword;

    public ErrorMessage(String keyword) {
        this.keyword = keyword;
    }

    public String getKeywords() {
        return keyword;
    }

    public void setKeywords(String keyword) {
        this.keyword = keyword;
    }
}
