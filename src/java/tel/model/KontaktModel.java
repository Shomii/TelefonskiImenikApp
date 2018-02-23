package tel.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class KontaktModel {

    private int kontaktID;

    @Size(min = 3, message = "Ime mora biti veće od 3 karaktera.")
    private String ime;

    @Size(min = 5, message = "Prezime mora biti veće od 5 karaktera.")
    private String prezime;

    @Pattern(regexp = "^[a-z]+@[a-z]+\\.[a-z]{1,3}$", message = "Nepravilan unos email-a.")
    private String email;

    @Pattern(regexp = "^[0-9]{3}$", message = "Pozivni broj se sastoji od 3 broja.")
    private String pozivniBroj;

    @Size(min = 5, max = 10, message = "Broj telefona mora biti između 5 i 10 brojeva.")
    private String brojTelefona;

    @Size(min = 5, max = 30, message = "Adresa mora biti između 5 i 30 karaktera.")
    private String adresa;

    @Size(min = 5, max = 20, message = "Grad mora biti između 5 i 20 karaktera.")
    private String grad;

    @Size(max = 50, message = "Komentar mora biti do 50 karaktera.")
    private String opis;

    private String kontaktSlika;

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

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public KontaktModel() {
    }

    public KontaktModel(int id, String ime) {
        this.kontaktID = id;
        this.ime = ime;
    }

    public String getKontaktSlika() {
        return kontaktSlika;
    }

    public void setKontaktSlika(String kontaktSlika) {
        this.kontaktSlika = kontaktSlika;
    }

}
