package boldair.data;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode( of = { "idCompte" } )
public class Compte {

	// -------
	// Champs
	// -------

	@Id
	private Long	idCompte;
	private String	pseudo;
	private String	motDePasse;
	private String	empreinteMdp;
	private String	email;
	private boolean	roleAdmin;

}
