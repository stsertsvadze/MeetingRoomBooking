package ge.stsertsvadze.meetingroombooking.service;

import ge.stsertsvadze.meetingroombooking.model.entity.MeetingRoom;
import ge.stsertsvadze.meetingroombooking.repository.MeetingRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MeetingRoomService {
    private final MeetingRoomRepository meetingRoomRepository;

    @Autowired
    public MeetingRoomService(MeetingRoomRepository meetingRoomRepository) {
        this.meetingRoomRepository = meetingRoomRepository;
    }

    public void saveMeetingRoom(MeetingRoom room) {
        meetingRoomRepository.save(room);
    }

    public Optional<MeetingRoom> getMeetingRoom(int roomNumber) {
        return meetingRoomRepository.findById(roomNumber);
    }

    public boolean deleteMeetingRoom(int roomNumber) {
        try {
            meetingRoomRepository.deleteById(roomNumber);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
