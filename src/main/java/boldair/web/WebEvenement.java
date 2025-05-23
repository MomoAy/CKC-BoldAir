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

import boldair.dao.DaoEvenement;
import boldair.data.Evenement;
import boldair.util.Alert;
import boldair.util.Paging;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RolesAllowed("ADMIN")
@RequestMapping("/evenement")
@SessionAttributes("PagingEvenement")
public class WebEvenement {
	
	private final DaoEvenement daoEvenement;
	
	// -------
		// Attributs de session
		// -------

		@ModelAttribute
		public Paging getPaging( @ModelAttribute( "pagingEvenement" ) Paging paging ) {
			return paging;
		}

		// -------
		// Endpoints
		// -------

		// -------
		// listContent()

		@PostMapping( "/list/content" )
		public String getListContent( Paging paging, Model model ) {

			var page = getPage( paging );

			// Si la n° de page demandé est > au nombre total, on affiche la dernière page
			if ( paging.getPageNum() > page.getTotalPages() && page.getTotalPages() > 0 ) {
				paging.setPageNum( page.getTotalPages() );
				page = getPage( paging );
			}

			model.addAttribute( "list", page.getContent() );
			model.addAttribute( "totalItems", page.getTotalElements() );
			model.addAttribute( "totalPages", page.getTotalPages() );
			
			return "evenement/list :: #dynamic_view";

		}
		
		@GetMapping( "/list" )
		public String list( Paging paging, Model model ) {
			getListContent( paging, model );
			return "evenement/list";
		}
		
		@GetMapping("/add")
		public String edit(Long id, Model model) {
			Evenement item;
			
			if(id == null) {
				item = new Evenement();
			} else {
				item = daoEvenement.findById( id ).get();
			}
			
			model.addAttribute( "item", item );
			return "evenement/form";
		}
		
		@PostMapping("/add")
		public String save(@ModelAttribute("item") Evenement item, RedirectAttributes ra, Model model, BindingResult result) {
			
			if(daoEvenement.verifierUniciteNom( item.getNom(), item.getIdEv() )) {
				daoEvenement.save( item );
				ra.addFlashAttribute( "alert", new Alert( Alert.Color.SUCCESS, "Action effectué avec succès" ) );
				return "redirect:/evenement/list";
			} else {
				model.addAttribute( "item", item );
				result.rejectValue( "nom", "", "Un évènement avec ce nom existe déjà" );
				return "evenement/form";
			}
		}	
		
		
		@GetMapping("/:id")
		public String getOneEvent(Long id, Model model) {
			Evenement item = daoEvenement.findById( id ).get();
			model.addAttribute( item );
			return "evenement/page";
		}
		
		
		
		// -------
		// Méthodes auxiliaires
		// ------

		private Page<Evenement> getPage( Paging paging ) {
			Page<Evenement> page;
			var pageable = PageRequest.of( paging.getPageNum() - 1, paging.getPageSize(), Sort.by("nom") );

			if(paging.getSearch().isBlank()) {
				page = daoEvenement.findAll( pageable );
			} else {
				page = daoEvenement.findByNomContainingIgnoreCase(paging.getSearch(), pageable );
			}

			return page;

		}
		
		

	
}
