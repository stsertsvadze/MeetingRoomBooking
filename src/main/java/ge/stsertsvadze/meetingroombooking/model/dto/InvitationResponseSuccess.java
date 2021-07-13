package ge.stsertsvadze.meetingroombooking.model.dto;

import ge.stsertsvadze.meetingroombooking.model.entity.Invitation;

import java.util.List;

public class InvitationResponseSuccess implements Response {
    private List<Invitation> result;

    public InvitationResponseSuccess(List<Invitation> result) {
        this.result = result;
    }

    public List<Invitation> getResult() {
        return result;
    }

    public void setResult(List<Invitation> result) {
        this.result = result;
    }
}
