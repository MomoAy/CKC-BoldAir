package boldair.dao;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import boldair.data.Participant;

public interface DaoParticipant extends CrudRepository<Participant, Long>{
	
	@Query("SELECT COUNT(*) = 0 FROM participant where email_part = :email AND id_part <> COALESCE(:id, 0)")
	boolean verifierUniciteEmail(String email, Long id);

}
