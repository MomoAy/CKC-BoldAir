package boldair.web;

import boldair.dao.DaoBenevole;
import boldair.data.Benevole;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/benevole")
public class WebBenevole {

    private final DaoBenevole daoBenevole;

    @GetMapping("/list")
    public String afficherListeBenevoles(Model model) {
        Page<Benevole> page = daoBenevole.findAllByOrderByNom(Pageable.unpaged());
        model.addAttribute("Benevole", page.getContent());
        model.addAttribute("pagingBenevole", page); // Utile pour la pagination dans Thymeleaf
        return "benevole/list";
    }

}
