package boldair.data;


import lombok.*;

import java.sql.Time;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode( of = "idP" )
public class Poste {
	
   @Id
   private long idP;
   private String nom;
   private String description;
   private LocalTime heureDebut;
   private LocalTime heureFin;
	
}
