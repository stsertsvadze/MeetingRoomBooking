package ge.stsertsvadze.meetingroombooking.model.dto.request;

public class DeleteInvitationDto {
    Long invitationId;

    public DeleteInvitationDto() {}

    public DeleteInvitationDto(Long invitationId) {
        this.invitationId = invitationId;
    }

    public Long getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(Long invitationId) {
        this.invitationId = invitationId;
    }
}
