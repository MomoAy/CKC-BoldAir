package boldair.data;

import lombok.*;

import java.sql.Date;

import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode( of = "idBen" )
public class Benevole {

	@Id
	private Long	idBen;
	private String	type;
	private String	nomBen;
	private String	prenom;
	private String	email;
	private Date	disponibilite;
	private String	telephone;

}
