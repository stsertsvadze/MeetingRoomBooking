package ge.stsertsvadze.meetingroombooking.model.dto;

public class AnswerInvitationRequest {
    private Long invitationId;
    private boolean accept;

    public AnswerInvitationRequest() {}

    public AnswerInvitationRequest(Long invitationId, boolean accept) {
        this.invitationId = invitationId;
        this.accept = accept;
    }

    public Long getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(Long invitationId) {
        this.invitationId = invitationId;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }
}
