package ge.stsertsvadze.meetingroombooking.controller;

import ge.stsertsvadze.meetingroombooking.model.dto.*;
import ge.stsertsvadze.meetingroombooking.model.entity.Invitation;
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
    public void invitePeople(@RequestBody InvitationListRequest invitationListRequest) {
        invitationService.saveInvitations(invitationListRequest);
    }

    @GetMapping
    public Response getInvitations(@RequestParam Long meetingId) {
        Optional<List<Invitation>> result = invitationService.getInvitations(meetingId);
        if (result.isPresent()) {
            return new InvitationResponseSuccess(result.get());
        } else {
            List<ErrorMessage> errors = Collections.singletonList(new ErrorMessage("invalid_meeting_id"));
            return new ResponseFailure(errors);
        }
    }

    @DeleteMapping
    public void deleteInvitation(@RequestBody DeleteInvitationRequest invitationId) {
        invitationService.deleteInvitation(invitationId.getInvitationId());
    }

    @PutMapping
    public void answerInvitation(@RequestBody AnswerInvitationRequest answerInvitationRequest) {
        invitationService.answerInvitation(answerInvitationRequest);
    }
}
