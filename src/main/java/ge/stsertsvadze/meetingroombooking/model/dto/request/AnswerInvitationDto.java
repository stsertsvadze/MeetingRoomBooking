package ge.stsertsvadze.meetingroombooking.model.dto.request;

public class AnswerInvitationDto extends ValidDto {
    private Long invitationId;
    private boolean accept;

    public AnswerInvitationDto() {}

    public AnswerInvitationDto(Long invitationId, boolean accept) {
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

    @Override
    public boolean isValid() {
        return invitationId != null;
    }
}
