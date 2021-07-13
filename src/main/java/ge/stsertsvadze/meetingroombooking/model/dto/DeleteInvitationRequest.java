package ge.stsertsvadze.meetingroombooking.model.dto;

public class DeleteInvitationRequest {
    Long invitationId;

    public DeleteInvitationRequest() {}

    public DeleteInvitationRequest(Long invitationId) {
        this.invitationId = invitationId;
    }

    public Long getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(Long invitationId) {
        this.invitationId = invitationId;
    }
}
