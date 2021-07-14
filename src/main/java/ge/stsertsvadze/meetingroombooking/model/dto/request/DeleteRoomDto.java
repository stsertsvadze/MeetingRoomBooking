package ge.stsertsvadze.meetingroombooking.model.dto.request;

public class DeleteRoomDto extends ValidDto {
    private int roomNumber;

    public DeleteRoomDto() {}

    public DeleteRoomDto(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    boolean isValid() {
        return roomNumber > 0;
    }
}
