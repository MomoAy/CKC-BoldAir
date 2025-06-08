package boldair.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode( of = { "idAssignation" } )
public class Assignation {

	@Id
	private Long idAssignation;
	private Long idBen;
	private Long idP;

}
