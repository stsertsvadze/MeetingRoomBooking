package ge.stsertsvadze.meetingroombooking.repository;

import ge.stsertsvadze.meetingroombooking.model.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
