package boldair.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;

import boldair.data.Poste;

public interface DaoPoste extends CrudRepository<Poste, Long> {
	
	@Query("SELECT COUNT(*) = 0 FROM poste where nom = :nom AND id_p <> COALESCE(:id, 0)")
	boolean verifierUniciteNom(String nom, Long id);
	
	//@Query("SELECT p.nom, p.description, COUNT(*) as NBP FROM poste p JOIN assignation a on p.id_p = a.id_p where a.id_ben = b.id_ben group by p.nom, p.description")
	//@NonNull Iterable<Poste> findAll();

}
