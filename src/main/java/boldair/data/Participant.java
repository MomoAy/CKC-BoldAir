package boldair.data;

import java.sql.Date;

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
	private Date dateNais;
	private String telephonePart;
	private Integer accordImage;
	private byte[] certificatMed;
	private byte[] autParentale;
	
	private Long idEquipe;
	
}
