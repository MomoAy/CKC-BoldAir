package boldair.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import boldair.data.Benevole;

public interface DaoBenevole extends CrudRepository<Benevole, Long> {

	// Cette méthode utilise une convention de nom Spring Data JPA
	Page<Benevole> findAllByOrderByNom( Pageable pageable );

}
