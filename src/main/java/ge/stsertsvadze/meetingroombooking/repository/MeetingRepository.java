package ge.stsertsvadze.meetingroombooking.repository;

import ge.stsertsvadze.meetingroombooking.model.entity.Meeting;
import ge.stsertsvadze.meetingroombooking.model.entity.MeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    List<Meeting> findAllByMeetingRoom(MeetingRoom meetingRoom);
}
