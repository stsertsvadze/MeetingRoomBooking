package ge.stsertsvadze.meetingroombooking.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class MeetingRoom {
    @Id
    private int roomNumber;

    @Column
    private int capacity;

    public MeetingRoom() {}

    public MeetingRoom(int roomNumber, int capacity) {
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
