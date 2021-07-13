package ge.stsertsvadze.meetingroombooking.model.dto;

import ge.stsertsvadze.meetingroombooking.model.entity.MeetingRoom;
import ge.stsertsvadze.meetingroombooking.model.entity.User;

public class MeetingRequest {
    private Long startTime;
    private Long duration;
    private MeetingRoom meetingRoom;
    private User author;

    public MeetingRequest() {}

    public MeetingRequest(Long startTime, Long duration, MeetingRoom meetingRoom, User author) {
        this.startTime = startTime;
        this.duration = duration;
        this.meetingRoom = meetingRoom;
        this.author = author;
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

    public MeetingRoom getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(MeetingRoom meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
