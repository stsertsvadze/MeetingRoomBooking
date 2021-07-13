package ge.stsertsvadze.meetingroombooking.controller;

import ge.stsertsvadze.meetingroombooking.model.dto.*;
import ge.stsertsvadze.meetingroombooking.model.dto.request.AnswerInvitationDto;
import ge.stsertsvadze.meetingroombooking.model.dto.request.DeleteInvitationDto;
import ge.stsertsvadze.meetingroombooking.model.dto.request.InvitationListDto;
import ge.stsertsvadze.meetingroombooking.model.dto.request.Request;
import ge.stsertsvadze.meetingroombooking.model.dto.response.Response;
import ge.stsertsvadze.meetingroombooking.model.dto.response.ResponseFailure;
import ge.stsertsvadze.meetingroombooking.model.dto.response.ResponseSuccess;
import ge.stsertsvadze.meetingroombooking.model.entity.Invitation;
import ge.stsertsvadze.meetingroombooking.model.entity.Meeting;
import ge.stsertsvadze.meetingroombooking.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/invitation")
public class InvitationController {
    private final InvitationService invitationService;

    @Autowired
    public InvitationController(InvitationService invitationService) {
        this.invitationService = invitationService;
    }

    @PostMapping()
    public Response invitePeople(@RequestBody Request<InvitationListDto> request) {
        Optional<Meeting> meeting = invitationService.saveInvitations(request.getData());
        if (meeting.isPresent()) {
            return new ResponseSuccess<>(meeting);
        } else {
            List<ErrorMessage> errors = Collections.singletonList(new ErrorMessage("invalid_meeting_id"));
            return new ResponseFailure<>(errors);
        }
    }

    @GetMapping
    public Response getInvitations(@RequestParam Long meetingId) {
        Optional<List<Invitation>> result = invitationService.getInvitations(meetingId);
        if (result.isPresent()) {
            return new ResponseSuccess<>(result.get());
        } else {
            List<ErrorMessage> errors = Collections.singletonList(new ErrorMessage("invalid_meeting_id"));
            return new ResponseFailure<>(errors);
        }
    }

    @DeleteMapping
    public Response deleteInvitation(@RequestBody Request<DeleteInvitationDto> request) {
        boolean success = invitationService.deleteInvitation(request.getData().getInvitationId());
        if (success) {
            return new ResponseSuccess<>("invitation_deleted");
        } else {
            List<ErrorMessage> errors = Collections.singletonList(new ErrorMessage("invalid_invitation_id"));
            return new ResponseFailure<>(errors);
        }
    }

    @PutMapping
    public Response answerInvitation(@RequestBody Request<AnswerInvitationDto> request) {
        Optional<Invitation> invitation = invitationService.answerInvitation(request.getData());
        if (invitation.isPresent()) {
            return new ResponseSuccess<>(invitation.get());
        } else {
            List<ErrorMessage> errors = Collections.singletonList(new ErrorMessage("invalid_invitation_id"));
            return new ResponseFailure<>(errors);
        }
    }
}
