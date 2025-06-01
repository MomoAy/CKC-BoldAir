package boldair.data;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscriptionEquipeData {

	private Long idEquipe;
    private String nomEquipe;
    private String dossard;
    private Integer accordDonnee;

    // Champs de inscription_equipe
    private Long idInscription;
    private Long idEv;
    private Timestamp dateInscription;
    private String statut;
}
