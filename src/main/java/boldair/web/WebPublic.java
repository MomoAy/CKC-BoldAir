package boldair.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import boldair.dao.DaoBenevole;
import boldair.dao.DaoEvenement;
import boldair.data.Evenement;

@Controller
public class WebPublic {

	private DaoEvenement	daoEvenement;
	private DaoBenevole		daoBenevole;

	// -------
	// Endpoints
	// -------

	// -------
	// home()

	@GetMapping( "/" )
	public String home() {
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

	@GetMapping( "/benevole" )
	public String bénévole() {
		return "public/benevole";
	}

	@GetMapping( "/dashbord" )
	public String dashbord() {
		return "public/dashbord";
	}

	@GetMapping( "/all-event" )
	public String getAllEvent( Model model ) {
		List<Evenement> items = daoEvenement.findEvenementsActifs();
		model.addAttribute( "list", items );
		return "/all-evenements";
	}

	// hello

}
