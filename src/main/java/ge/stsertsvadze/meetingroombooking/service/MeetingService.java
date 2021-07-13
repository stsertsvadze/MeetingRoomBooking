package ge.stsertsvadze.meetingroombooking.service;

import ge.stsertsvadze.meetingroombooking.model.entity.Meeting;
import ge.stsertsvadze.meetingroombooking.model.entity.MeetingRoom;
import ge.stsertsvadze.meetingroombooking.model.entity.User;
import ge.stsertsvadze.meetingroombooking.repository.MeetingRepository;
import ge.stsertsvadze.meetingroombooking.repository.MeetingRoomRepository;
import ge.stsertsvadze.meetingroombooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MeetingService {
    private final MeetingRepository meetingRepository;
    private final UserRepository userRepository;
    private final MeetingRoomRepository meetingRoomRepository;

    @Autowired
    public MeetingService(MeetingRepository meetingRepository, UserRepository userRepository, MeetingRoomRepository meetingRoomRepository) {
        this.meetingRepository = meetingRepository;
        this.userRepository = userRepository;
        this.meetingRoomRepository = meetingRoomRepository;
    }

    public boolean addMeeting(Meeting meeting) {
        String username = meeting.getAuthor().getUsername();
        int roomNumber = meeting.getMeetingRoom().getRoomNumber();
        Optional<User> author = userRepository.findByUsername(username);
        Optional<MeetingRoom> meetingRoom = meetingRoomRepository.findByRoomNumber(roomNumber);

        if (author.isPresent()) {
            meeting.setAuthor(author.get());
        } else {
            return false;
        }

        if (meetingRoom.isPresent()) {
            meeting.setMeetingRoom(meetingRoom.get());
        } else {
            return false;
        }

        meetingRepository.save(meeting);
        return true;
    }

    public void deleteMeeting(Long meetingId) {
        meetingRepository.deleteById(meetingId);
    }

    public Optional<Meeting> getMeeting(Long meetingId) {
        return meetingRepository.findById(meetingId);
    }
}
