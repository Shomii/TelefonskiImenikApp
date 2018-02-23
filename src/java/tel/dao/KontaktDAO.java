package tel.dao;

import java.util.List;
import tel.domain.Kontakt;

public interface KontaktDAO {

    public void dodavanjeNovogKontakta(Kontakt kontakt);

    public List<Kontakt> sviKontakti();

    public Kontakt podaciOKontaktu(Kontakt kontakt);

    public Kontakt kontaktiId(Integer kontaktID);

    public Kontakt kontaktIme(String ime);

    public void azuriranje(Kontakt kontakt);

    public void brisanje(Kontakt kontakt);
}
