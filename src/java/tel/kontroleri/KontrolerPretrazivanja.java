package tel.kontroleri;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tel.dao.KontaktDAO;
import tel.domain.Kontakt;
import tel.model.KontaktModel;
import tel.service.ServisKontakata;

@Controller
public class KontrolerPretrazivanja {

    @Autowired
    private ServisKontakata servisKontakata;

    @Autowired
    private KontaktDAO kontaktDAO;

    List<KontaktModel> kontaktModeli = new ArrayList<KontaktModel>();

    @RequestMapping(value = {"pretrazivanjeKontakta"}, method = RequestMethod.GET)
    public String pretrazivanje(Model model) {

        kontaktModeli = getServisKontakata().sviKontakti();
        model.addAttribute("kontaktModelii", kontaktModeli);

        KontaktModel kontaktModel = new KontaktModel();
        int kontaktId = kontaktModel.getKontaktID();
        KontaktModel kontaktModel2 = getServisKontakata().sviKontakti().set(kontaktId, kontaktModel);
        int idKontakta = kontaktModel2.getKontaktID();
        model.addAttribute("idKontakta", idKontakta);
        model.addAttribute("idKontakta", idKontakta);

        return "pretrazivanjeKontakta";
    }

    @RequestMapping(value = {"/kontaktPregled"})
    public String pregled(@RequestParam(value = "kontaktID", required = false) Integer kontaktID, Model model, KontaktModel kontaktModel) {

        if (kontaktID == null) {
            return "redirect:greska";
        }

        kontaktModeli = getServisKontakata().sviKontakti();
        model.addAttribute("kontaktModelii", kontaktModeli);

        List<Kontakt> kontaktiZaProveru = kontaktDAO.sviKontakti();
        model.addAttribute("kontaktiZaProveru", kontaktiZaProveru);

        KontaktModel podaciOKontaktu = getServisKontakata().kontaktiId(kontaktID);
        model.addAttribute("prikaziKontakt", podaciOKontaktu);

        return "kontaktPregled";

    }

    @RequestMapping(value = {"/popup"})
    public String pregledPopup(@RequestParam(value = "kontaktID", required = false) Integer kontaktID, Model model, KontaktModel kontaktModel) {

        if (kontaktID == null) {
            return "redirect:greska";
        }

        kontaktModeli = getServisKontakata().sviKontakti();
        model.addAttribute("kontaktModelii", kontaktModeli);

        List<Kontakt> kontaktiZaProveru = kontaktDAO.sviKontakti();
        model.addAttribute("kontaktiZaProveru", kontaktiZaProveru);

        KontaktModel podaciOKontaktu = getServisKontakata().kontaktiId(kontaktID);
        model.addAttribute("prikaziKontakt", podaciOKontaktu);

        return "popup";

    }

    @RequestMapping(value = {"/uIzradi"})
    public String mapa(Model model) {

        return "uIzradi";
    }

    @RequestMapping(value = {"kontaktPregled_1"})
    public String pregled_1(Model model,
            @RequestParam(value = "kontaktID", required = false) Integer kontaktID) {

        kontaktModeli = getServisKontakata().sviKontakti();
        model.addAttribute("kontaktModelii", kontaktModeli);

        KontaktModel podaciOKontaktu = getServisKontakata().kontaktiId(kontaktID);
        model.addAttribute("prikaziKontakt", podaciOKontaktu);

        return "kontaktPregled_1";
    }

    @RequestMapping(value = "/traziKontakte", method = RequestMethod.GET)
    public @ResponseBody
    List<KontaktModel> traziKontakte(@RequestParam String ime) {
        return traziIme(ime);

    }

    private List<KontaktModel> traziIme(String ime) {

        List<KontaktModel> result = new ArrayList<KontaktModel>();

        for (KontaktModel tag : kontaktModeli) {
            if (tag.getIme().contains(ime)) {
                result.add(tag);
            }
        }
        return result;

    }

    @RequestMapping(value = "/traziKontakte2", method = RequestMethod.GET)
    public @ResponseBody
    List<KontaktModel> traziKontakte2(@RequestParam String brojTelefona) {
        return traziBroj(brojTelefona);
    }

    private List<KontaktModel> traziBroj(String brojTelefona) {

        List<KontaktModel> result = new ArrayList<KontaktModel>();

        for (KontaktModel tag : kontaktModeli) {
            if (tag.getBrojTelefona().contains(brojTelefona)) {
                result.add(tag);
            }
        }
        return result;
    }

    public ServisKontakata getServisKontakata() {
        return servisKontakata;
    }

    public void setServisKontakata(ServisKontakata servisKontakata) {
        this.servisKontakata = servisKontakata;
    }

}
