package ge.stsertsvadze.meetingroombooking.model.entity;

import ge.stsertsvadze.meetingroombooking.model.dto.MeetingRequest;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

import static org.hibernate.annotations.CascadeType.*;

@Entity
@Table
public class Meeting {

    @Id
    @SequenceGenerator(name = "meeting_seq", sequenceName = "meeting_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meeting_seq")
    private Long id;


    @Column
    private Long startTime;

    @Column
    private Long duration;

    @OneToOne
    private MeetingRoom meetingRoom;

    @OneToOne
    private User author;

    public Meeting() {}

    public Meeting(Long startTime, Long duration, MeetingRoom meetingRoom, User author) {
        this.startTime = startTime;
        this.duration = duration;
        this.meetingRoom = meetingRoom;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
