package tel.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import tel.domain.Korisnik;
import tel.model.KontaktModel;

public interface ServisKontakata {

    public void dodavanjeNovogKontakta(KontaktModel kontaktModel, MultipartFile[] projectImages, Korisnik korisnik);

    public List<KontaktModel> sviKontakti();

    public KontaktModel podaciOKontaktu(KontaktModel kontaktModel);

    public KontaktModel kontaktiId(Integer kontaktID);

    public KontaktModel kontaktIme(String ime);

    public void azuriranje(KontaktModel kontaktModel, MultipartFile[] projectImages, Korisnik korisnik);

    public void brisanje(KontaktModel kontaktModel);
}
