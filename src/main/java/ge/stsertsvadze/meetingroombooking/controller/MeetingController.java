package ge.stsertsvadze.meetingroombooking.controller;

import ge.stsertsvadze.meetingroombooking.model.dto.*;
import ge.stsertsvadze.meetingroombooking.model.dto.request.DeleteMeetingDto;
import ge.stsertsvadze.meetingroombooking.model.dto.request.MeetingDto;
import ge.stsertsvadze.meetingroombooking.model.dto.request.Request;
import ge.stsertsvadze.meetingroombooking.model.dto.response.Response;
import ge.stsertsvadze.meetingroombooking.model.dto.response.ResponseFailure;
import ge.stsertsvadze.meetingroombooking.model.dto.response.ResponseSuccess;
import ge.stsertsvadze.meetingroombooking.model.entity.Meeting;
import ge.stsertsvadze.meetingroombooking.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/meeting")
public class MeetingController {
    private final MeetingService meetingService;

    @Autowired
    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping
    public Response getMeeting(@RequestParam Long meetingId) {
        if (meetingId <= 0) {
            List<ErrorMessage> errors = Collections.singletonList(new ErrorMessage("invalid_request"));
            return new ResponseFailure<>(errors);
        }
        Optional<Meeting> meeting = meetingService.getMeeting(meetingId);
        if (meeting.isPresent()) {
            return new ResponseSuccess<>(meeting.get());
        } else {
            List<ErrorMessage> errors = Collections.singletonList(new ErrorMessage("invalid_meeting"));
            return new ResponseFailure<>(errors);
        }
    }

    @PostMapping()
    public Response addMeeting(@RequestBody Request<MeetingDto> request, @RequestHeader String authorization) {
        if (!request.isValid()) {
            List<ErrorMessage> errors = Collections.singletonList(new ErrorMessage("invalid_request"));
            return new ResponseFailure<>(errors);
        }
        Optional<Meeting> meeting = meetingService.addMeeting(request.getData());
        if (meeting.isPresent()) {
            meeting.get().getAuthor().setJwt(authorization.substring(7));
            return new ResponseSuccess<>(meeting.get());
        } else {
            List<ErrorMessage> errors = Collections.singletonList(new ErrorMessage("invalid_meeting"));
            return new ResponseFailure<>(errors);
        }
    }

    @DeleteMapping
    public Response deleteMeeting(@RequestBody Request<DeleteMeetingDto> request, @RequestHeader String authorization) {
        if (!request.isValid()) {
            List<ErrorMessage> errors = Collections.singletonList(new ErrorMessage("invalid_request"));
            return new ResponseFailure<>(errors);
        }
        boolean success = meetingService.deleteMeeting(request.getData().getMeetingId(), authorization.substring(7));
        if (success) {
            return new ResponseSuccess<>("meeting_deleted");
        } else {
            List<ErrorMessage> errors = Collections.singletonList(new ErrorMessage("invalid_meeting"));
            return new ResponseFailure<>(errors);
        }
    }
}
