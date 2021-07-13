package ge.stsertsvadze.meetingroombooking.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table()
public class Invitation {
    public enum Status {
        PENDING,
        ACCEPTED,
        DECLINED
    }

    @Id
    @SequenceGenerator(name = "invitation_seq", sequenceName = "invitation_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invitation_seq")
    private Long id;

    @OneToOne
    private User user;

    @Column
    private Status status;

    @ManyToOne
    @JoinColumn(name = "meeting_id", referencedColumnName = "id")
    @JsonBackReference
    private Meeting meeting;

    public Invitation() {}

    public Invitation(User user, Status status, Meeting meeting) {
        this.user = user;
        this.status = status;
        this.meeting = meeting;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }
}
