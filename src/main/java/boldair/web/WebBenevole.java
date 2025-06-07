package boldair.web;

import boldair.dao.DaoBenevole;
import boldair.data.Benevole;
import boldair.util.Alert;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RolesAllowed( "ADMIN" )
@RequestMapping( "/gestion-b" )
public class WebBenevole {

	private final DaoBenevole daoBenevole;

	@Autowired
	private EmailService emailService;

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
			RedirectAttributes ra, Model model ) {

		if ( daoBenevole.verifierUniciteEmail( item.getEmail(), item.getIdBen() ) ) {
			item.setType( "Interne" );
			Benevole savedBenevole = daoBenevole.save( item );
			emailService.envoyerEmailConfirmationBenevole( savedBenevole );
			ra.addFlashAttribute( "alert", new Alert( Alert.Color.SUCCESS, "Action effectuée avec succès" ) );
			return "redirect:/benevole";
		} else {
			model.addAttribute( "item", item );
			model.addAttribute( "alert",
					new Alert( Alert.Color.DANGER, "Un bénévole avec ce adresse email est déjà enregistré" ) );
			return "public/benevole";
		}

	}

}
