package boldair.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import boldair.data.Benevole;

public interface DaoBenevole extends CrudRepository<Benevole, Long>, PagingAndSortingRepository<Benevole, Long> {

	// Cette méthode utilise une convention de nom Spring Data JPA
	Page<Benevole> findAllByOrderByNomBen( Pageable pageable );

	Page<Benevole> findByNomBenContainingIgnoreCase( String search, Pageable pageable );

}
