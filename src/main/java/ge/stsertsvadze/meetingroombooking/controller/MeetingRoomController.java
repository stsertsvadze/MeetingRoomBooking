package ge.stsertsvadze.meetingroombooking.controller;

import ge.stsertsvadze.meetingroombooking.model.dto.ErrorMessage;
import ge.stsertsvadze.meetingroombooking.model.dto.request.DeleteRoomDto;
import ge.stsertsvadze.meetingroombooking.model.dto.request.MeetingRoomDto;
import ge.stsertsvadze.meetingroombooking.model.dto.request.Request;
import ge.stsertsvadze.meetingroombooking.model.dto.response.Response;
import ge.stsertsvadze.meetingroombooking.model.dto.response.ResponseFailure;
import ge.stsertsvadze.meetingroombooking.model.dto.response.ResponseSuccess;
import ge.stsertsvadze.meetingroombooking.model.entity.MeetingRoom;
import ge.stsertsvadze.meetingroombooking.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/room")
public class MeetingRoomController {

    private final MeetingRoomService meetingRoomService;

    @Autowired
    public MeetingRoomController(MeetingRoomService meetingRoomService) {
        this.meetingRoomService = meetingRoomService;
    }

    @GetMapping()
    public Response getMeetingRoom(@RequestParam int roomNumber) {
        Optional<MeetingRoom> meetingRoom = meetingRoomService.getMeetingRoom(roomNumber);
        if (meetingRoom.isPresent()) {
            return new ResponseSuccess<>(meetingRoom.get());
        } else {
            List<ErrorMessage> errors = Collections.singletonList(new ErrorMessage("invalid_meeting_room_id"));
            return new ResponseFailure<>(errors);
        }
    }

    @PostMapping()
    public Response addMeetingRoom(@RequestBody Request<MeetingRoomDto> request) {
        MeetingRoomDto dto = request.getData();
        MeetingRoom meetingRoom = new MeetingRoom(dto.getRoomNumber(), dto.getCapacity());
        meetingRoomService.saveMeetingRoom(meetingRoom);
        return new ResponseSuccess<>(meetingRoom);
    }

    @PutMapping()
    public Response updateMeetingRoom(@RequestBody Request<MeetingRoomDto> request) {
        MeetingRoomDto dto = request.getData();
        MeetingRoom meetingRoom = new MeetingRoom(dto.getRoomNumber(), dto.getCapacity());
        meetingRoomService.saveMeetingRoom(meetingRoom);
        return new ResponseSuccess<>(meetingRoom);
    }

    @DeleteMapping
    public Response deleteMeetingRoom(@RequestBody Request<DeleteRoomDto> request) {
        boolean success = meetingRoomService.deleteMeetingRoom(request.getData().getRoomNumber());
        if (success) {
            return new ResponseSuccess<>("meeting_room_deleted");
        } else {
            List<ErrorMessage> errors = Collections.singletonList(new ErrorMessage("invalid_room_number"));
            return new ResponseFailure<>(errors);
        }
    }
}
