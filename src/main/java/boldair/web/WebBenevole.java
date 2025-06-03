package boldair.web;

import boldair.dao.DaoBenevole;
import boldair.data.Benevole;
import boldair.util.Alert;
import boldair.util.Paging;
import lombok.RequiredArgsConstructor;

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

@Controller
@RequiredArgsConstructor
@RequestMapping("/gestion-b")
@SessionAttributes("PagingBenevole")
public class WebBenevole {

    private final DaoBenevole daoBenevole;

    @ModelAttribute
	public Paging getPaging( @ModelAttribute( "pagingBenevole" ) Paging paging ) {
		return paging;
	}

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
		return "benevole/list :: #dynamic_view";

	}


	// -------
	// list() - GET

	@GetMapping( "/list" )
	public String list( Paging paging, Model model ) {
		getListContent( paging, model );
		return "benevole/list";
	}



	// -------
	// list() - POST

	@PostMapping( "/list" )
	public String list() {
		return "redirect:/benevole/list";
	}

    //@GetMapping("/list")
    //public String afficherListeBenevoles(Model model) {
      //  Page<Benevole> page = daoBenevole.findAllByOrderByNomBen(Pageable.unpaged());
        //model.addAttribute("Benevole", page.getContent());
        //model.addAttribute("pagingBenevole", page); // Utile pour la pagination dans Thymeleaf
        //return "benevole/list";
    //}
	
	@GetMapping( path = "/benevole" )
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
			item.setType( "Interne" );
			daoBenevole.save( item );
			ra.addFlashAttribute( "alert", new Alert( Alert.Color.SUCCESS, "Action effectuée avec succès" ) );
			return "redirect:/benevole";
		} else {
			model.addAttribute( "item", item );
			ra.addFlashAttribute( "alert", new Alert( Alert.Color.INFO, "Action effectuée avec succès" ) );
			return "redirect:/benevole";
		}

	}

	
    private Page<Benevole> getPage( Paging paging ) {
		Page<Benevole> page;
		var pageable = PageRequest.of( paging.getPageNum() - 1, paging.getPageSize(), Sort.by("nom") );

		if(paging.getSearch().isBlank()) {
			page = daoBenevole.findAll( pageable );
		} else {
			page = daoBenevole.findByNomBenContainingIgnoreCase(paging.getSearch(), pageable );
		}

		return page;

	}

}
