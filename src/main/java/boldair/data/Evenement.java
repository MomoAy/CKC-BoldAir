package boldair.data;

import java.sql.Date;
import java.sql.Time;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode( of = { "idEv" })
public class Evenement {
	
	@Id
	private Long idEv;
	private String nom;
	private int nbRepas;
	private int nbreParticipants;
	private Date dateEvenement;
	private String lieu;
	private Time heure;
	private int tarifRepas;
	
}
