package tel.dao;

import java.util.List;
import java.util.Map;
import tel.domain.Korisnik;

public interface KorisnikDAO {

    public void registracijaKorisnika(Korisnik korisnik);

    public void azuriranje(Korisnik korisnik);

    public List<Korisnik> sviKorisnici();

    public Korisnik korisnikId(Integer id);

    public Korisnik korisnikZaLog(Integer id);

    public void brisanje(Korisnik korisnik);

    public Korisnik findByUsername(String username);

    public Korisnik findByEmail(String email);

    public Korisnik findByPassword(String password);

    public Korisnik findByRetypePassword(String retypePassword);

    public Korisnik getKorisnik(String username);

    public Korisnik getUserById(int id);
}
