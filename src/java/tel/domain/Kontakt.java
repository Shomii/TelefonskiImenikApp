package tel.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Kontakt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int kontaktID;

    private String ime;
    private String prezime;
    private String email;
    private String pozivniBroj;
    private String brojTelefona;
    private String adresa;
    private String grad;
    private String opis;
    private String kontaktSlika;

    @JoinColumn(name = "korisnik_id")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Korisnik korisnikZaImenik;

    public int getKontaktID() {
        return kontaktID;
    }

    public void setKontaktID(int kontaktID) {
        this.kontaktID = kontaktID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPozivniBroj() {
        return pozivniBroj;
    }

    public void setPozivniBroj(String pozivniBroj) {
        this.pozivniBroj = pozivniBroj;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Kontakt(int kontaktID, String ime, String prezime, String email, String pozivniBroj, String brojTelefona, String adresa, String grad, String opis/*, Korisnik username*/, String kontaktSlika) {

        this.kontaktID = kontaktID;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.pozivniBroj = pozivniBroj;
        this.brojTelefona = brojTelefona;
        this.adresa = adresa;
        this.grad = grad;
        this.opis = opis;
        this.kontaktSlika = kontaktSlika;
    }

    public Kontakt() {
    }

    public String getKontaktSlika() {
        return kontaktSlika;
    }

    public void setKontaktSlika(String kontaktSlika) {
        this.kontaktSlika = kontaktSlika;
    }

    public Korisnik getKorisnikZaImenik() {
        return korisnikZaImenik;
    }

    public void setKorisnikZaImenik(Korisnik korisnikZaImenik) {
        this.korisnikZaImenik = korisnikZaImenik;
    }
}
