package boldair.dao;

import boldair.data.Benevole;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import boldair.data.Assignation;

import java.util.List;

public interface DaoAssignation extends CrudRepository<Assignation, Long>{

    @Query("SELECT b.* FROM assignation a JOIN benevole b ON a.id_ben = b.id_ben WHERE a.id_p = :idP")
    List<Benevole> findBenevolesByPosteId(long idP);
	
}
