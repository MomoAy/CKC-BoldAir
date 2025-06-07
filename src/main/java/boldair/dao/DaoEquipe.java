package boldair.dao;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import boldair.data.Equipe;

public interface DaoEquipe extends CrudRepository<Equipe, Long>{

	@Query("SELECT COUNT(*) = 0 FROM equipe where nom_equipe = :nomEquipe AND id_equipe <> COALESCE(:id, 0)")
	boolean verifierUniciteNom(String nomEquipe, Long id);
	
}
