package ge.stsertsvadze.meetingroombooking.model.dto.request;

public class DeleteMeetingDto {
    private Long meetingId;

    public DeleteMeetingDto() {}

    public DeleteMeetingDto(Long roomNumber) {
        this.meetingId = roomNumber;
    }

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }
}
