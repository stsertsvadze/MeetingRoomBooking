package ge.stsertsvadze.meetingroombooking.repository;

import ge.stsertsvadze.meetingroombooking.model.entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {
}
