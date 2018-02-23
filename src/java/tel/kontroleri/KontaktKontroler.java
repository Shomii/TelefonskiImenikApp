package tel.kontroleri;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import tel.dao.KontaktDAO;
import tel.dao.KorisnikDAO;
import tel.domain.Kontakt;
import tel.domain.Korisnik;
import tel.model.KontaktModel;
import tel.service.ServisKontakata;

@Controller
public class KontaktKontroler {

    @Autowired
    private ServisKontakata servisKontakata;

    @Autowired
    private KorisnikDAO korisnikDAO;

    @Autowired
    private KontaktDAO kontaktDAO;

    @Autowired
    //@Qualifier("sessionRegistry")
    private SessionRegistry sessionRegistry;

    @RequestMapping("/proba")
    public String emailFossrm(Model model) {

        return "proba";
    }

    @RequestMapping("/slika")
    public String getSlika(Model model) {

        return "slika";
    }

    @RequestMapping("/noviKontakt")
    public String getNoviKontakt(Model model) {
        model.addAttribute("noviKontakt", new KontaktModel());

        return "noviKontakt";
    }

    @RequestMapping(value = "/noviKontakt", method = RequestMethod.POST)
    public String postNoviKontakt(@Valid @ModelAttribute("noviKontakt") KontaktModel kontakt, BindingResult rezultatDodavanjaKontakta, HttpServletRequest request,
            @RequestParam("projectImages") MultipartFile[] projectImages/*, Korisnik korisnik*/)
            throws FileNotFoundException, IOException {

        if (rezultatDodavanjaKontakta.hasErrors()) {
            return "noviKontakt";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();

        Korisnik k = korisnikDAO.getKorisnik(userDetail.getUsername());
        request.getSession().setAttribute("id", k.getId());

        int korisnikID = (int) request.getSession().getAttribute("id");

        Korisnik korisnik = new Korisnik();
        korisnik.setId(korisnikID);

        getServisKontakata().dodavanjeNovogKontakta(kontakt, projectImages, korisnik);

        return "redirect:telefonskiImenik";
    }

    @RequestMapping(value = {"/telefonskiImenik"})
    public String kontakti(Model model, HttpServletRequest request) {

        List<Kontakt> kontaktii = kontaktDAO.sviKontakti();

//        
//        for (Kontakt kontaktZaIspis : kontaktii) {
//            
//        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();

        Korisnik k = korisnikDAO.getKorisnik(userDetail.getUsername());
        request.getSession().setAttribute("id", k.getId());

        int korisnikID = (int) request.getSession().getAttribute("id");

        Korisnik korisnik = korisnikDAO.getUserById(korisnikID);
        model.addAttribute("korisnik", korisnik);

        model.addAttribute("kontakti", kontaktii);
        model.addAttribute("korisnikID", korisnikID);

        model.addAttribute("users", sessionRegistry.getAllPrincipals());
        model.addAttribute("total", sessionRegistry.getAllPrincipals().size());
        model.addAttribute("svi", sessionRegistry.getAllSessions(sessionRegistry.getAllPrincipals().get(0), true).size());

        return "telefonskiImenik";
    }

    @RequestMapping(value = {"/azuriranjeKontakta"})
    public String azuriranje(@RequestParam Integer kontaktID, Model model) {
        model.addAttribute("azuriraniKontakt", getServisKontakata().kontaktiId(kontaktID));

        return "azuriranjeKontakta";
    }

    @RequestMapping(value = {"/azuriranjeKontakta"}, method = RequestMethod.POST)
    public String azuriranjePost(@Valid @ModelAttribute("azuriraniKontakt") KontaktModel kontaktModel, BindingResult rezultatAzuriranja, HttpServletRequest request, @RequestParam("projectImages") MultipartFile[] projectImages) {

        if (rezultatAzuriranja.hasErrors()) {
            return "azuriranjeKontakta";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();

        Korisnik k = korisnikDAO.getKorisnik(userDetail.getUsername());
        request.getSession().setAttribute("id", k.getId());

        int korisnikID = (int) request.getSession().getAttribute("id");

        Korisnik korisnik = new Korisnik();
        korisnik.setId(korisnikID);

        getServisKontakata().azuriranje(kontaktModel, projectImages, korisnik);
        return "redirect:/telefonskiImenik";
    }

    @RequestMapping(value = {"/brisanjeKontakta"})
    public String brisanje(@RequestParam Integer kontaktID, Model model, HttpServletRequest request) {
        KontaktModel kontaktModel = getServisKontakata().kontaktiId(kontaktID);
        getServisKontakata().brisanje(kontaktModel);
        return "redirect:/telefonskiImenik";
    }

    @RequestMapping(value = {"/greska"})
    public String greska() {
        return "greska";
    }

    @RequestMapping(value = "/podaciOKontaktu", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody

    KontaktModel kontakt(@RequestParam("kontaktID") Integer kontaktID) {
        KontaktModel kontaktModel = servisKontakata.kontaktiId(kontaktID);
//        getUserService().getKorisnikByUsername(name);
        return kontaktModel;
    }

    public ServisKontakata getServisKontakata() {
        return servisKontakata;
    }

    public void setServisKontakata(ServisKontakata servisKontakata) {
        this.servisKontakata = servisKontakata;
    }
}
