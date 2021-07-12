package ge.stsertsvadze.meetingroombooking.repository;

import ge.stsertsvadze.meetingroombooking.model.entity.MeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Integer> {}
