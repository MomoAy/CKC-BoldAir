package boldair.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import boldair.data.Evenement;

public interface DaoEvenement extends CrudRepository<Evenement, Long>, PagingAndSortingRepository<Evenement, Long> {
	
	Page<Evenement> findByNomContainingIgnoreCase( String search, Pageable pageable );
	
	
	@Query("SELECT COUNT(*) = 0 FROM evenement where nom = :nom AND id_ev <> COALESCE(:id, 0)")
	boolean verifierUniciteNom(String nom, Long id);
	
	@Query("SELECT * FROM evenement WHERE date_limite_inscription > CURRENT_DATE")
	List<Evenement> findEvenementsActifs();
}
