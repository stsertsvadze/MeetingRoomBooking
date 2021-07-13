package ge.stsertsvadze.meetingroombooking.model.dto.request;

import java.util.List;

public class InvitationListDto {
    private Long meetingId;
    private List<String> invitations;

    public InvitationListDto() {}

    public InvitationListDto(Long meetingId, List<String> invitations) {
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
