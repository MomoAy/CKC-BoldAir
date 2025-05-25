package boldair.data;


import lombok.*;

import java.sql.Time;

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
   
   private Time heure;
   

   	
	
}
