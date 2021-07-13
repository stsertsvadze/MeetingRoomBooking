package ge.stsertsvadze.meetingroombooking.model.dto;

import ge.stsertsvadze.meetingroombooking.model.entity.Meeting;

public class MeetingResponseSuccess implements Response{
    private Meeting result;

    public MeetingResponseSuccess(Meeting  result) {
        this.result = result;
    }

    public Meeting getResult() {
        return result;
    }

    public void setResult(Meeting result) {
        this.result = result;
    }
}
