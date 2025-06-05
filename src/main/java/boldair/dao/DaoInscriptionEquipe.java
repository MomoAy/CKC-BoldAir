package boldair.dao;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import boldair.data.InscriptionEquipe;

public interface DaoInscriptionEquipe extends CrudRepository<InscriptionEquipe, Long> {

    @Modifying
    @Query("UPDATE inscription_equipe SET statut = :statut WHERE id_equipe = :id")
    boolean setStatut(Long id, String statut);
}
