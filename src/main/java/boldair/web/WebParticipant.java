package boldair.web;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import boldair.dao.DaoEquipe;
import boldair.dao.DaoInscriptionEquipe;
import boldair.dao.DaoParticipant;
import boldair.data.Equipe;
import boldair.data.InscriptionEquipe;
import boldair.data.Participant;
import boldair.util.Alert;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/participant")
@SessionAttributes("PagingParticipant")
public class WebParticipant {

	private final DaoParticipant daoParticipant;
	private final DaoEquipe daoEquipe;
	private final DaoInscriptionEquipe daoInscriptionEquipe;

	@Autowired
	private EmailService emailService;

	@GetMapping("/inscription")
	public String edit(Model model) {

		return "public/inscription";
	}

	@PostMapping("/inscription")
	public String save(
		@RequestParam Long evenementId,

		@RequestParam String nomEquipe,
		@RequestParam Integer accordDonnee,

		@RequestParam String nomPart1,
		@RequestParam String prenomPart1,
		@RequestParam String emailPart1,
		@RequestParam Date datNais1,
		@RequestParam String telephonePart1,
		@RequestParam Integer accordImage1,
		@RequestParam("certificatMedical1") MultipartFile file1_1,
		@RequestParam("autParentale1") MultipartFile file1_2,

		@RequestParam String nomPart2,
		@RequestParam String prenomPart2,
		@RequestParam String emailPart2,
		@RequestParam Date datNais2,
		@RequestParam String telephonePart2,
		@RequestParam Integer accordImage2,
		@RequestParam("certificatMedical2") MultipartFile file2_1,
		@RequestParam("autParentale2") MultipartFile file2_2,
		Model model,
		RedirectAttributes ra
		) throws IOException
	{
		//Je créer l'équipe dab
		Equipe equipe = new Equipe();
		if(!daoEquipe.verifierUniciteNom( nomEquipe, equipe.getIdEquipe() )) {
			model.addAttribute( "alert", new Alert( Alert.Color.DANGER, "Une équipe avec ce nom existe déjà") );
			return "public/inscription";
		}
		equipe.setNomEquipe( nomEquipe );
		if(accordDonnee == 1) {
			equipe.setAccordDonnee( accordDonnee ) ;
		} else {
			ra.addFlashAttribute("error", "L'accord est obligatoire");
	        return "redirect:/form";
		}
		daoEquipe.save( equipe );

		//Ensuite le premier participant
		Participant p1 = new Participant();
		if(!daoParticipant.verifierUniciteEmail( emailPart1, p1.getIdPart() )) {
			model.addAttribute( "alert", new Alert( Alert.Color.DANGER,"Un des participants est déjà inscrite avec une autre équipe") );
			return "public/inscription";
		}
		p1.setNomPart( nomPart1 );
		p1.setPrenomPart( prenomPart1 );
		p1.setEmailPart( emailPart1 );
		p1.setDateNais( datNais1 );
		p1.setTelephonePart( telephonePart1 );
		p1.setAccordImage( accordImage1 );
		p1.setCertificatMed( file1_1.getBytes() );
		p1.setAutParentale( file1_2.getBytes() );
		p1.setIdEquipe( equipe.getIdEquipe() );

		//Ensuite le second participant
		Participant p2 = new Participant();
		if(!daoParticipant.verifierUniciteEmail( emailPart2, p2.getIdPart() )) {
			model.addAttribute( "alert", new Alert( Alert.Color.DANGER, "Un des participants est déjà inscrite avec une autre équipe") );
			return "public/inscription";
		}
		p2.setNomPart( nomPart2 );
		p2.setPrenomPart( prenomPart2 );
		p2.setEmailPart( emailPart2 );
		p2.setDateNais( datNais2 );
		p2.setTelephonePart( telephonePart2 );
		p2.setAccordImage( accordImage2 );
		p2.setCertificatMed( file2_1.getBytes() );
		p2.setAutParentale( file2_2.getBytes() );
		p2.setIdEquipe( equipe.getIdEquipe() );

		daoParticipant.save( p1 );
		daoParticipant.save( p2 );

		//Je finalise avec l'Inscription
		InscriptionEquipe ins = new InscriptionEquipe();
		ins.setIdEv(evenementId);
	    ins.setIdEquipe(equipe.getIdEquipe());
	    ins.setDateInscription( new Timestamp(System.currentTimeMillis()) );
	    ins.setStatut( "Attente" );

		daoInscriptionEquipe.save( ins );

		emailService.envoyerEmailConfirmationParticipants(p1, p2, equipe);
		emailService.envoyerEmailConfirmationParticipants(p2, p1, equipe);
		ra.addFlashAttribute( "alert", new Alert( Alert.Color.SUCCESS, "Votre inscription a bien été pris en compte." ) );
		return "redirect:/";

	}
	
}
