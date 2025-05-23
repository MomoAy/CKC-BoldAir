package boldair.web;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import boldair.dao.DaoEquipe;
import boldair.dao.DaoInscriptionEquipe;
import boldair.dao.DaoParticipant;
import boldair.data.Equipe;
import boldair.data.InscriptionEquipe;
import boldair.data.Participant;
import boldair.util.Alert;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/participant")
@SessionAttributes("PagingParticipant")
public class WebParticipant {
	
	private DaoParticipant daoParticipant;
	private DaoEquipe daoEquipe;
	private DaoInscriptionEquipe daoInscriptionEquipe;
	
	@GetMapping("/inscription-equipe-form")
	public String edit(Model model) {
	
		return "form";
	}
	
	@PostMapping("/inscription-equipe")
	public String save(
		@RequestParam Long evenementId,
			
		@RequestParam String nomEquipe,

		@RequestParam String nomPart1,
		@RequestParam String prenomPart1,
		@RequestParam String emailPart1,
		@RequestParam String datNais1,
		@RequestParam String telephonePart1,
		@RequestParam String accordDonnee1,
		@RequestParam String accordImage1,
		@RequestParam String certificatMedical1,
		@RequestParam String autParentale1,
		
		@RequestParam String nomPart2,
		@RequestParam String prenomPart2,
		@RequestParam String emailPart2,
		@RequestParam String datNais2,
		@RequestParam String telephonePart2,
		@RequestParam String accordDonnee2,
		@RequestParam String accordImage2,
		@RequestParam String certificatMedical2,
		@RequestParam String autParentale2,
		Model model,
		RedirectAttributes ra, 
		BindingResult result
		)
	{
		//Je créer l'équipe dab
		Equipe equipe = new Equipe(); 
		equipe.setNomEquipe( nomEquipe );
		daoEquipe.save( equipe );
		
		//Ensuite le premier participant
		Participant p1 = new Participant();
		p1.setNomPart( prenomPart1 );
		p1.setPrenomPart( prenomPart1 );
		p1.setEmailPart( emailPart1 );
		p1.setDatNais( datNais1 );
		p1.setTelephonePart( telephonePart1 );
		p1.setAccordDonnee( accordDonnee1 );
		p1.setAccordImage( accordImage1 );
		p1.setCertificatMedical( certificatMedical1 );
		p1.setAutParentale( autParentale1 );
		p1.setIdEquipe( equipe.getIdEquipe() );
		
		//Ensuite le second participant
		Participant p2 = new Participant();
		p2.setNomPart( prenomPart1 );
		p2.setPrenomPart( prenomPart1 );
		p2.setEmailPart( emailPart1 );
		p2.setDatNais( datNais1 );
		p2.setTelephonePart( telephonePart1 );
		p2.setAccordDonnee( accordDonnee1 );
		p2.setAccordImage( accordImage1 );
		p2.setCertificatMedical( certificatMedical1 );
		p2.setAutParentale( autParentale1 );
		p2.setIdEquipe( equipe.getIdEquipe() );
		
		daoParticipant.save( p1 );
		daoParticipant.save( p2 );
		
		
		//Je finalise avec l'Inscription
		InscriptionEquipe ins = new InscriptionEquipe();
		ins.setIdEvenement(evenementId);
	    ins.setIdEquipe(equipe.getIdEquipe());
	    ins.setDateInscription(LocalDate.now().toString());
		
		daoInscriptionEquipe.save( ins );
		
		ra.addAttribute( "alert1", new Alert( Alert.Color.SUCCESS, "Votre inscription a bien été pris en cours" ) );
		ra.addAttribute( "alert2", new Alert( Alert.Color.SUCCESS, "Votre inscription a bien été pris en cours" ) );
				
				
		return "redirect:/acceuil";
		
	}
}
