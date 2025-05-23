package boldair.data;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode( of = { "idBenevole" } )
public class Benevole {
	
	@Id
	private Long idBenevole;
	private String nom;
	private String catégorie;
	private String dossart;
	private String Etat;
	
}
