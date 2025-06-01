package boldair.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import boldair.dao.DaoBenevole;
import boldair.dao.DaoEquipe;
import boldair.dao.DaoInscriptionEquipeData;
import boldair.dao.DaoParticipant;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RolesAllowed("ADMIN")
@RequestMapping("/gestion")
@SessionAttributes("PagingAdmin")
public class WebAdmin {
	
	private final DaoParticipant daoParticipant;
	private final DaoEquipe daoEquipe;
	private final DaoBenevole daoBenevole;
	private final DaoInscriptionEquipeData daoInscriptionEquipeData;
	
	
	@GetMapping( "/participant" )
	public String gestion_participant(Model model) {
		model.addAttribute( "participants", daoParticipant.findAll());
		return "compte/gestion_participant";
	}
	
	
	@GetMapping( "/equipe" )
	public String gestion_équipe(Model model) {
		model.addAttribute( "equipes", daoInscriptionEquipeData.findAllEquipeAvecInscription() );
		return "compte/gestion_équipe";
	}
	
	@GetMapping( "/benevole" )
	public String gestion_benevole(Model model) {
		model.addAttribute( "benevoles", daoBenevole.findAll() );
		return "compte/gestion_benevole";
	}
	

}
