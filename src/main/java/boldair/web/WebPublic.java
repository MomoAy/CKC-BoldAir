package boldair.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebPublic {

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
	@GetMapping( "/inscription" )
	  public String inscription() {
	    return "public/inscription";
	  }
	  @GetMapping( "/benevole" )
	  public String bénévole() {
	    return "public/benevole";
	  }
	  @GetMapping( "/dashbord" )
	  public String dashbord() {
	    return "public/dashbord";
	  }

}
