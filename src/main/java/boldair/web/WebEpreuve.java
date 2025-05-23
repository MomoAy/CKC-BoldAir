package boldair.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import boldair.dao.DaoEpreuve;
import boldair.data.Epreuve;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RolesAllowed("ADMIN")
@RequestMapping("/epreuve")
@SessionAttributes("PagingEpreuve")
public class WebEpreuve {
	
	private final DaoEpreuve daoEpreuve;
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute( "items", daoEpreuve.findAll() );
		return "epreuve/list";
	}
	
	@GetMapping("/form")
	public String edit(Long id, Model model) {
		Epreuve item;
		
		if(id == null) {
			item = new Epreuve();
		} else {
			item = daoEpreuve.findById(id).get();
		}
		
		model.addAttribute( "item", item );
		return "epreuve/form";
		
	}
	
	
}
