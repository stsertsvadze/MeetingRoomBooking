package ge.stsertsvadze.meetingroombooking.service;

import ge.stsertsvadze.meetingroombooking.model.dto.request.AnswerInvitationDto;
import ge.stsertsvadze.meetingroombooking.model.dto.request.InvitationListDto;
import ge.stsertsvadze.meetingroombooking.model.entity.Invitation;
import ge.stsertsvadze.meetingroombooking.model.entity.Meeting;
import ge.stsertsvadze.meetingroombooking.model.entity.User;
import ge.stsertsvadze.meetingroombooking.repository.InvitationRepository;
import ge.stsertsvadze.meetingroombooking.repository.MeetingRepository;
import ge.stsertsvadze.meetingroombooking.repository.UserRepository;
import ge.stsertsvadze.meetingroombooking.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class InvitationService {
    private final InvitationRepository invitationRepository;
    private final MeetingRepository meetingRepository;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    @Autowired
    public InvitationService(InvitationRepository invitationRepository, MeetingRepository meetingRepository, UserRepository userRepository, JwtUtils jwtUtils) {
        this.invitationRepository = invitationRepository;
        this.meetingRepository = meetingRepository;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    public Optional<Meeting> saveInvitations(InvitationListDto invitationListDto, String auth) {
        Long meetingId = invitationListDto.getMeetingId();
        Optional<Meeting> meeting = meetingRepository.findById(meetingId);
        if (meeting.isPresent()) {
            if (auth == null || !jwtUtils.validateToken(auth, meeting.get().getAuthor())) {
                return Optional.empty();
            }
            addInvitations(meeting.get(), invitationListDto.getInvitations());
            meetingRepository.save(meeting.get());
        }
        return meeting;
    }

    private void addInvitations(Meeting meeting, List<String> usernames) {
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
                meeting.getInvitations().add(invitation);
            }
        }
    }

    public Optional<List<Invitation>> getInvitations(Long meetingId) {
        Optional<Meeting> meeting = meetingRepository.findById(meetingId);
        return meeting.map(Meeting::getInvitations);
    }

    public boolean deleteInvitation(Long invitationId, String auth) {
        try {
            Optional<Invitation> invitation = invitationRepository.findById(invitationId);
            if (invitation.isPresent()) {
                if (auth != null && jwtUtils.validateToken(auth, invitation.get().getMeeting().getAuthor())) {
                    invitationRepository.deleteById(invitationId);
                    return true;
                }
            }
        } catch (Exception ignored) {}
        return false;
    }

    public Optional<Invitation> answerInvitation(AnswerInvitationDto request, String auth) {
        Long invitationId = request.getInvitationId();
        Invitation.Status choice = request.isAccept() ? Invitation.Status.ACCEPTED : Invitation.Status.DECLINED;
        Optional<Invitation> invitation = invitationRepository.findById(invitationId);
        if (invitation.isPresent()) {
            if (auth == null || !jwtUtils.validateToken(auth, invitation.get().getUser())) {
                return Optional.empty();
            }
            invitation.get().setStatus(choice);
            invitation.get().getUser().setJwt(auth);
            invitationRepository.save(invitation.get());
        }
        return invitation;
    }

}
