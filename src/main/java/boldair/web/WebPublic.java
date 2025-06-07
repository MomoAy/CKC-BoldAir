package boldair.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import boldair.dao.DaoBenevole;
import boldair.dao.DaoEvenement;
import boldair.data.Benevole;
import boldair.data.Evenement;
import boldair.util.Alert;

@Controller
public class WebPublic {

	private final DaoEvenement	daoEvenement;
	private final DaoBenevole	daoBenevole;
	
	public WebPublic(DaoBenevole daoBenevole, DaoEvenement	daoEvenement) {
		this.daoBenevole = daoBenevole;
		this.daoEvenement = daoEvenement;
	}

	@Autowired
	private EmailService emailService;

	// -------
	// Endpoints
	// -------

	// -------
	// home()

	@GetMapping( "/" )
	public String home(Model model) {
		model.addAttribute( "event", daoEvenement.getDateMainEvent() );
		return "public/accueil";
	}

	// -------
	// mentionsLegales()

	@GetMapping( "/mentions-legales" )
	public String mentionsLegales() {
		return "public/mentions-legales";
	}

	// -------
	// quiSommesNous()

	@GetMapping( "/qui-sommes-nous" )
	public String quiSommesNous() {
		return "public/qui-sommes-nous";
	}

	@GetMapping( "/all-event" )
	public String getAllEvent( Model model ) {
		List<Evenement> items = daoEvenement.findEvenementsActifs();
		model.addAttribute( "list", items );
		return "/all-evenements";
	}


	@GetMapping( "/benevole" )
	public String edit( Long id, Model model ) {

		Benevole item;


		if ( id == null ) {
			item = new Benevole();
		} else {
			item = daoBenevole.findById( id ).get();
		}

		model.addAttribute( "item", item );
		return "public/benevole";

	}

	@PostMapping( "/benevole" )
	public String save(
			 @ModelAttribute( "item" ) Benevole item, BindingResult result,
			RedirectAttributes ra, Model model) {

		if(daoBenevole.verifierUniciteEmail( item.getEmail(), item.getIdBen() )) {
			item.setType( "Externe" );
			Benevole savedBenevole = daoBenevole.save( item );
			emailService.envoyerEmailConfirmationBenevole(savedBenevole);
			ra.addFlashAttribute( "alert", new Alert( Alert.Color.SUCCESS, "Action effectuée avec succès" ) );
			return "redirect:/benevole";
		} else {
			model.addAttribute( "item", item );
			model.addAttribute( "alert", new Alert( Alert.Color.DANGER,"Un benevole avec ce email est déjà inscrit") );
			return "public/benevole";
		}


	}
}
