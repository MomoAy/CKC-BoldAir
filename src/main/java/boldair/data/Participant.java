package boldair.data;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "idPart" } )
public class Participant {
	
	@Id
	private Long idPart;
	private String nomPart;
	private String prenomPart;
	private String emailPart;
	private String datNais;
	private String telephonePart;
	private String accordDonnee;
	private String accordImage;
	private String certificatMedical;
	private String autParentale;
	
	private Long idEquipe;
	
}
