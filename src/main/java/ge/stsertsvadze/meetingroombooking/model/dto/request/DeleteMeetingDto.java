package ge.stsertsvadze.meetingroombooking.model.dto.request;

public class DeleteMeetingDto extends ValidDto {
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

    @Override
    boolean isValid() {
        return meetingId != null;
    }
}
