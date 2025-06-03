package boldair.dao;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import boldair.data.InscriptionEquipeData;

public interface DaoInscriptionEquipeData extends CrudRepository<InscriptionEquipeData, Long> {
	
	@Query("SELECT e.id_equipe, e.nom_equipe, e.dossard, e.accord_donnee, " +
	           "i.id_inscription, i.date_inscription, i.statut " +
	           "FROM equipe e JOIN inscription_equipe i ON e.id_equipe = i.id_equipe")
	    List<InscriptionEquipeData> findAllEquipeAvecInscription();
}
