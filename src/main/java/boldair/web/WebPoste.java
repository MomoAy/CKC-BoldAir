package boldair.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import boldair.dao.DaoAssignation;
import boldair.dao.DaoPoste;
import boldair.data.Assignation;
import boldair.data.Poste;
import boldair.util.Alert;
import boldair.util.Paging;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RolesAllowed( "ADMIN" )
@RequestMapping( "/poste" )
public class WebPoste {
	
	private final DaoPoste daoPoste;
	private final DaoAssignation daoAssignation;

	@GetMapping("/postes")
	public String AllPostes(Model model) {
		model.addAttribute( "postes", daoPoste.findAll() );
		return "compte/gestion_poste";
	}
	
	//Ajout de Poste
	@GetMapping("/form")
	public String edit(Long id, Model model) {
		Poste item;
		
		if(id == null) {
			item = new Poste();
		} else {
			item = daoPoste.findById( id ).get();
		}
		
		model.addAttribute( "item", item );		
		
		return "form";
	}
	
	@PostMapping("/form")
	public String save(@ModelAttribute("item") Poste item, RedirectAttributes ra, Model model, BindingResult result) {
		
		if(daoPoste.verifierUniciteNom( item.getNom(), item.getIdP() )) {
			daoPoste.save( item );
			ra.addFlashAttribute( "alert", new Alert( Alert.Color.SUCCESS, "Action effectuée avec succès" ) );
			return "redirect:/list";
		} else {
			model.addAttribute( "item", item );
			result.rejectValue( "nom", "", "Ce nom est déjà utilisé" );
			return "form";
		}
		
	}
	
	
	//Supp un poste
	// -------
		// delete()

	@PostMapping( "/delete" )
	public String delete( Long id, Paging paging, Model model ) {
		daoPoste.deleteById( id );
		model.addAttribute( "alert", new Alert( Alert.Color.SUCCESS, "Suppression effectuée avec succès" ) );
		return "compte/gestion_poste";
	}
	
	
	//Affecter un poste 
	@GetMapping("/affect")
	public String formAffectation(Long id, Model model) {
		Assignation item;
		
		if(id == null) {
			item = new Assignation();
		} else {
			item = daoAssignation.findById(id).get();
		}
		
		model.addAttribute( "item", item );
		return "affect/form";
	}
	
	@PostMapping("/affect")
	public String effectiveAffectation(@ModelAttribute("item") Assignation item, RedirectAttributes ra, Model model, BindingResult result) {
		
		daoAssignation.save( item );
		ra.addFlashAttribute( "alert", new Alert( Alert.Color.SUCCESS, "Action effectuée avec succès" ) );
		
		return "redirect:/list/post";
	}
		
	
}
