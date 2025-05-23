package boldair.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import boldair.data.Benevole;
import boldair.data.Compte;

public interface DaoBenevole extends CrudRepository<Benevole, Long>, PagingAndSortingRepository<Benevole, Long>  {

	// Cette méthode utilise une convention de nom Spring Data JPA
	Page<Benevole> findAllByOrderByNom( Pageable pageable );
	
	Page<Benevole> findByNomContainingIgnoreCase( String search, Pageable pageable );

}
