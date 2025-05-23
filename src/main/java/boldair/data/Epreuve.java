package boldair.data;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode( of = { "idEpreuve" })
public class Epreuve {

	@Id
	private Long idEpreuve;
	private double tarif;
	private String lieu;
	private int nbKilometre;
	
}
