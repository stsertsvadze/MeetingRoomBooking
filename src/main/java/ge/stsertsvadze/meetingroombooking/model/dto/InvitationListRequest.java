package ge.stsertsvadze.meetingroombooking.model.dto;

import java.util.List;

public class InvitationListRequest {
    private Long meetingId;
    private List<String> invitations;

    public InvitationListRequest() {}

    public InvitationListRequest(Long meetingId, List<String> invitations) {
        this.meetingId = meetingId;
        this.invitations = invitations;
    }

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }

    public List<String> getInvitations() {
        return invitations;
    }

    public void setInvitations(List<String> invitations) {
        this.invitations = invitations;
    }
}
