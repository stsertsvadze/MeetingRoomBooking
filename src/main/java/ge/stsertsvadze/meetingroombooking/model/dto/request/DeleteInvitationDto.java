package ge.stsertsvadze.meetingroombooking.model.dto.request;

public class DeleteInvitationDto extends ValidDto {
    private Long invitationId;

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

    @Override
    boolean isValid() {
        return invitationId != null;
    }
}
