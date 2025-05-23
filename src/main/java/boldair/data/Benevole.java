package boldair.data;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode( of = "id_ben" )
public class Benevole {

	@Id
	private Long id_ben;

	private String type;

	private String nom_ben;

	private String prenom;

	private String email;

	private String disponibilites;

	private String telephone;

}
