package tel.service;

import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.User;
import tel.domain.Korisnik;
import tel.model.KorisnikModel;
import tel.domain.Korisnik;

public interface ServisKorisnika {

    public void registracijaKorisnika(KorisnikModel korisnikModel);

    public void azuriranje(KorisnikModel korisnikModel);

    public List<KorisnikModel> sviKorisnici();

    public KorisnikModel korisnikId(Integer id);

    public KorisnikModel korisnikZaLog(Integer id);

    public void brisanje(KorisnikModel korisnikModel);

    public KorisnikModel findByUsername(String username);

    public KorisnikModel findByEmail(String email);

    public KorisnikModel findByPassword(String password);

    public KorisnikModel findByRetypePassword(String retypePassword);

    public KorisnikModel getKorisnikModel(String username);
}
