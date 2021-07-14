package ge.stsertsvadze.meetingroombooking.service;

import ge.stsertsvadze.meetingroombooking.model.dto.request.MeetingDto;
import ge.stsertsvadze.meetingroombooking.model.entity.Invitation;
import ge.stsertsvadze.meetingroombooking.model.entity.Meeting;
import ge.stsertsvadze.meetingroombooking.model.entity.MeetingRoom;
import ge.stsertsvadze.meetingroombooking.model.entity.User;
import ge.stsertsvadze.meetingroombooking.repository.MeetingRepository;
import ge.stsertsvadze.meetingroombooking.repository.MeetingRoomRepository;
import ge.stsertsvadze.meetingroombooking.repository.UserRepository;
import ge.stsertsvadze.meetingroombooking.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MeetingService {
    private final MeetingRepository meetingRepository;
    private final UserRepository userRepository;
    private final MeetingRoomRepository meetingRoomRepository;
    private final JwtUtils jwtUtils;

    @Autowired
    public MeetingService(MeetingRepository meetingRepository, UserRepository userRepository, MeetingRoomRepository meetingRoomRepository, JwtUtils jwtUtils) {
        this.meetingRepository = meetingRepository;
        this.userRepository = userRepository;
        this.meetingRoomRepository = meetingRoomRepository;
        this.jwtUtils = jwtUtils;
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
            if (isAlreadyBooked(meetingRoom.get(), meetingDto.getStartTime(), meetingDto.getDuration())) {
                return Optional.empty();
            }
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

    private boolean isAlreadyBooked(MeetingRoom meetingRoom, Long start, Long duration) {
        long end = start + duration;
        List<Meeting> meetings = meetingRepository.findAllByMeetingRoom(meetingRoom);
        for (int i = 0; i < meetings.size(); i++) {
            Meeting currMeeting = meetings.get(i);
            Long currStart = currMeeting.getStartTime();
            long currEnd = currMeeting.getStartTime() + currMeeting.getDuration();
            if (!(start > currEnd || end < currStart)) return true; // two meetings have interception
        }
        return false;
    }

    private List<Invitation> createInvitations(List<String> usernames, Meeting meeting) {
        List<Invitation> invitations = new ArrayList<>();
        HashSet<String> invited = new HashSet<>();
        if (meeting.getInvitations() != null) {
            for (int i = 0; i < meeting.getInvitations().size(); i++) {
                invited.add(meeting.getInvitations().get(i).getUser().getUsername());
            }
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

    public boolean deleteMeeting(Long meetingId, String auth) {
        try {
            Optional<Meeting> meeting = meetingRepository.findById(meetingId);
            if (meeting.isPresent()) {
                if (auth != null && jwtUtils.validateToken(auth, meeting.get().getAuthor())) {
                    meetingRepository.deleteById(meetingId);
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public Optional<Meeting> getMeeting(Long meetingId) {
        return meetingRepository.findById(meetingId);
    }
}
