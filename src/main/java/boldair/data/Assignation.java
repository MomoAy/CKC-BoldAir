package boldair.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode( of = { "idAssignation" } )
public class Assignation {
	
	private Long idAssignation;
	private Long idBen;
	private Long idP;

}
