package boldair.web;

import java.util.List;

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

	private DaoEvenement daoEvenement;
	private DaoBenevole daoBenevole;

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

	@GetMapping( path = "/add-extern-ben" )
		public String edit( Long id, Model model ) {

			Benevole item;


			if ( id == null ) {
				item = new Benevole();
			} else {
				item = daoBenevole.findById( id ).get();
			}

			model.addAttribute( "item", item );
			return "plat/form";

		}

		// -------
		// save()

		@PostMapping( "/form" )
		public String save(
				 @ModelAttribute( "item" ) Benevole item,
				RedirectAttributes ra, Model model, BindingResult result ) {

			if(daoBenevole.verifierUniciteNom( item.getNomBen(), item.getIdBen() )) {
				daoBenevole.save( item );
				ra.addFlashAttribute( "alert", new Alert( Alert.Color.SUCCESS, "Mise à jour effectuée avec succès" ) );
				return "redirect:/plat/list";
			} else {
				model.addAttribute( "item", item );
				model.addAttribute( "typePlats", daoBenevole.findAll() );
				result.rejectValue( "nom", "", "Ce nom est déjà utilisé" );
				return "plat/form";
			}


		}

}
