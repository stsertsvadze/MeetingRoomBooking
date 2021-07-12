package ge.stsertsvadze.meetingroombooking.controller;

import ge.stsertsvadze.meetingroombooking.model.dto.MeetingRoomRequest;
import ge.stsertsvadze.meetingroombooking.model.entity.MeetingRoom;
import ge.stsertsvadze.meetingroombooking.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Optional<MeetingRoom> getMeetingRoom(@RequestParam int roomNumber) {
        return meetingRoomService.getMeetingRoom(roomNumber);
    }

    @PostMapping()
    public void addMeetingRoom(@RequestBody MeetingRoomRequest request) {
        meetingRoomService.saveMeetingRoom(new MeetingRoom(request.getRoomNumber(), request.getCapacity()));
    }

    @PutMapping()
    public void updateMeetingRoom(@RequestBody MeetingRoomRequest request) {
        meetingRoomService.saveMeetingRoom(new MeetingRoom(request.getRoomNumber(), request.getCapacity()));
    }

    @DeleteMapping
    public void deleteMeetingRoom(@RequestParam int roomNumber) {
        meetingRoomService.deleteMeetingRoom(roomNumber);
    }
}
