package tel.kontroleri;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import tel.dao.KorisnikDAO;
import tel.domain.Korisnik;
import tel.service.ServisKorisnika;

@Controller
public class SigurnosniKontroler {

    @Autowired
    private KorisnikDAO korisnikDAO;

    @Autowired
    private ServisKorisnika servisKorisnika;

    @RequestMapping("/filter")
    public String redirecttAfterLogin2(HttpServletRequest request, Model modell) {
        if (request.isUserInRole("ROLE_USER")) {
            return "redirect:/telefonskiImenik";

        } else if (request.isUserInRole("ROLE_ADMIN")) {

            return "redirect:/admin";
        } else {
            return "redirect:/nedozvoljenaStranica";
        }
    }

    @RequestMapping("/nedozvoljenaStranica")
    public String nedozvoljenaStranica() {

        return "nedozvoljenaStranica";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView modell = new ModelAndView();
        if (error != null) {
            modell.addObject("error", "Ne postoji korisnik sa tim korisničkim imenom ili lozinkom. \n Moguće je da niste aktivirali nalog, da li ste proverili vaš mail !");
        }

        if (logout != null) {
            modell.addObject("msg", "Odjavili ste se.");
        }
        modell.setViewName("index");

        return modell;

    }

    private Korisnik getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = null;
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        }
        Korisnik user = korisnikDAO.getKorisnik(userName);
        return user;
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

}
