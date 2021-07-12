package ge.stsertsvadze.meetingroombooking.model.dto;

public class MeetingRoomRequest {

    private int roomNumber;
    private int capacity;

    public MeetingRoomRequest(int roomNumber, int capacity) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
