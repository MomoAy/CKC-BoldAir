package boldair.data;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode( of = { "idEquipe" } )
public class Equipe {

	@Id
	private Long idEquipe;
	private String nomEquipe;
	private String dossard;
	private Integer accordDonnee;
}
