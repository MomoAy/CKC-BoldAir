package boldair.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import boldair.dao.DaoBenevole;
import boldair.dao.DaoEquipe;
import boldair.dao.DaoInscriptionEquipe;
import boldair.dao.DaoInscriptionEquipeData;
import boldair.dao.DaoParticipant;
import boldair.dao.DaoPoste;
import boldair.util.Alert;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RolesAllowed( "ADMIN" )
@RequestMapping( "/gestion" )
@SessionAttributes( "PagingAdmin" )
public class WebAdmin {

	private final DaoParticipant			daoParticipant;
	private final DaoBenevole				daoBenevole;
	private final DaoInscriptionEquipeData	daoInscriptionEquipeData;
	private final DaoInscriptionEquipe		daoInscriptionEquipe;
	private final DaoPoste 					daoPoste;

	@GetMapping( "/participant" )
	public String gestion_participant( Model model ) {
		model.addAttribute( "participants", daoParticipant.findAll() );
		return "compte/gestion_participant";
	}

	@GetMapping( "/equipe" )
	public String gestion_équipe( Model model ) {
		model.addAttribute( "equipes", daoInscriptionEquipeData.findAllEquipeAvecInscription() );
		return "compte/gestion_équipe";
	}

	@GetMapping( "/benevole" )
	public String gestion_benevole( Model model ) {
		model.addAttribute( "benevoles", daoBenevole.findAll() );
		model.addAttribute( "postes", daoPoste.findAll() );
		return "compte/gestion_benevole";
	}

	@PostMapping( "/update-statut/{id}" )
	public String UpdateStatusValidate( @PathVariable Long id, @RequestParam( required = false ) String statut,
			Model model, RedirectAttributes ra ) {

		if ( statut == null ) {
			ra.addFlashAttribute( "alert", new Alert( Alert.Color.DANGER, "Statut manquant" ) );
			return "redirect:/gestion/equipe";
		}

		daoInscriptionEquipe.setStatut( id, statut );
		ra.addFlashAttribute( "alert", new Alert( Alert.Color.SUCCESS, "Statut mis à jour" ) );
		return "redirect:/gestion/equipe";

	}

}
