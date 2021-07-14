package ge.stsertsvadze.meetingroombooking.model.dto.request;

import ge.stsertsvadze.meetingroombooking.model.entity.Invitation;
import ge.stsertsvadze.meetingroombooking.model.entity.MeetingRoom;
import ge.stsertsvadze.meetingroombooking.model.entity.User;

import java.util.List;

public class MeetingDto extends ValidDto {
    private Long startTime;
    private Long duration;
    private int roomNumber;
    private String author;
    private List<String> invitations;

    public MeetingDto() {}

    public MeetingDto(Long startTime, Long duration, int roomNumber, String author, List<String> invitations) {
        this.startTime = startTime;
        this.duration = duration;
        this.roomNumber = roomNumber;
        this.author = author;
        this.invitations = invitations;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setMeetingRoom(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getInvitations() {
        return invitations;
    }

    public void setInvitations(List<String> invitations) {
        this.invitations = invitations;
    }

    @Override
    boolean isValid() {
        return startTime != null && duration != null && roomNumber > 0 && author != null && invitations != null;
    }
}
