package boldair.data;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode( of = "idBen" )
public class Benevole {

	@Id
	private Long idBen;
	private String type;
	private String nomBen;
	private String prenom;
	private String email;
	private String disponibilites;
	private String telephone;

}
