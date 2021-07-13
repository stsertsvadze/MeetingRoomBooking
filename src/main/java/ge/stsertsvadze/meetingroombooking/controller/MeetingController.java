package ge.stsertsvadze.meetingroombooking.controller;

import ge.stsertsvadze.meetingroombooking.model.dto.*;
import ge.stsertsvadze.meetingroombooking.model.entity.Meeting;
import ge.stsertsvadze.meetingroombooking.model.entity.MeetingRoom;
import ge.stsertsvadze.meetingroombooking.model.entity.User;
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

    @PostMapping()
    public Response addMeeting(@RequestBody MeetingRequest meetingRequest) {
        Long startTime = meetingRequest.getStartTime();
        Long duration = meetingRequest.getDuration();
        MeetingRoom meetingRoom = meetingRequest.getMeetingRoom();
        User author = meetingRequest.getAuthor();
        Meeting meeting = new Meeting(startTime, duration, meetingRoom, author);
        boolean success = meetingService.addMeeting(meeting);
        if (success) {
            return new MeetingResponseSuccess(meeting);
        } else {
            List<ErrorMessage> errors = Collections.singletonList(new ErrorMessage("invalid_meeting"));
            return new ResponseFailure(errors);
        }
    }

    @DeleteMapping
    public void deleteMeeting(@RequestParam Long meetingId) {
        meetingService.deleteMeeting(meetingId);
    }

    @GetMapping
    public Response getMeeting(@RequestParam Long meetingId) {
        Optional<Meeting> meeting = meetingService.getMeeting(meetingId);
        if (meeting.isPresent()) {
            return new MeetingResponseSuccess(meeting.get());
        } else {
            List<ErrorMessage> errors = Collections.singletonList(new ErrorMessage("invalid_meeting_id"));
            return new ResponseFailure(errors);
        }
    }
}
