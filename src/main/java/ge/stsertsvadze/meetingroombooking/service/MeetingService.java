package ge.stsertsvadze.meetingroombooking.service;

import ge.stsertsvadze.meetingroombooking.model.dto.request.MeetingDto;
import ge.stsertsvadze.meetingroombooking.model.entity.Invitation;
import ge.stsertsvadze.meetingroombooking.model.entity.Meeting;
import ge.stsertsvadze.meetingroombooking.model.entity.MeetingRoom;
import ge.stsertsvadze.meetingroombooking.model.entity.User;
import ge.stsertsvadze.meetingroombooking.repository.MeetingRepository;
import ge.stsertsvadze.meetingroombooking.repository.MeetingRoomRepository;
import ge.stsertsvadze.meetingroombooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public Optional<Meeting> addMeeting(MeetingDto meetingDto) {
        String username = meetingDto.getAuthor();
        int roomNumber = meetingDto.getRoomNumber();

        Optional<User> author = userRepository.findByUsername(username);
        Optional<MeetingRoom> meetingRoom = meetingRoomRepository.findByRoomNumber(roomNumber);
        Meeting meeting = new Meeting();

        if (author.isPresent()) {
            meeting.setAuthor(author.get());
        } else {
            return Optional.empty();
        }

        if (meetingRoom.isPresent()) {
            meeting.setMeetingRoom(meetingRoom.get());
        } else {
            return Optional.empty();
        }
        meeting.setStartTime(meetingDto.getStartTime());
        meeting.setDuration(meetingDto.getDuration());
        List<Invitation> invitations = createInvitations(meetingDto.getInvitations(), meeting);
        meeting.setInvitations(invitations);
        meetingRepository.save(meeting);
        return Optional.of(meeting);
    }

    private List<Invitation> createInvitations(List<String> usernames, Meeting meeting) {
        List<Invitation> invitations = new ArrayList<>();
        HashSet<String> invited = new HashSet<>();
        for (int i = 0; i < meeting.getInvitations().size(); i++) {
            invited.add(meeting.getInvitations().get(i).getUser().getUsername());
        }
        for (int i = 0; i < usernames.size(); i++) {
            String username = usernames.get(i);
            Optional<User> user = userRepository.findByUsername(username);
            if (user.isPresent()) {
                if (invited.contains(username)) continue;
                else invited.add(username);
                Invitation invitation = new Invitation(user.get(), Invitation.Status.PENDING, meeting);
                invitations.add(invitation);
            }
        }
        return invitations;
    }

    public boolean deleteMeeting(Long meetingId) {
        try {
            meetingRepository.deleteById(meetingId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Optional<Meeting> getMeeting(Long meetingId) {
        return meetingRepository.findById(meetingId);
    }
}
