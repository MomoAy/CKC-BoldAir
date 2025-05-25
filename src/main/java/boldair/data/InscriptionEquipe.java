package boldair.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode( of = { "idInscription" })
public class InscriptionEquipe {

	private Long idInscription;
	private Long idEquipe;
	private Long idEvenement;
	private String dateInscription;

}
