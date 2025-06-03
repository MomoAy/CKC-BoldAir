package boldair.data;



import java.sql.Timestamp;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode( of = { "idInscription" })
public class InscriptionEquipe {

	@Id
	private Long idInscription;
	private Long idEquipe;
	private Long idEv;
	private Timestamp dateInscription;
	private String statut;

}
