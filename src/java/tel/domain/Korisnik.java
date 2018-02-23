package tel.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = "korisnik")

public class Korisnik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String ime;
    private String prezime;
    private String email;
    private String username;
    private String password;
    private String datumKreiranja;
    private boolean enabled;

    @Transient
    private String retypePassword;

    @Transient
    private String oldPassword;

    @Transient
    private String newPassword;

    @Transient
    private String emailReset;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "korisnik_rola",
            joinColumns = {
                @JoinColumn(name = "korisnik_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                @JoinColumn(name = "rolee_id", referencedColumnName = "roleeID")}
    )

    private List<Rolee> authorities;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "korisnikZaImenik")
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Kontakt> sviKontakti;

    public Korisnik() {
    }

    public Korisnik(int id) {
        this.id = id;
    }

    public Korisnik(int id, String ime, String prezime, String email, String username, String password, String datumKreiranja, boolean enabled) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.username = username;
        this.password = password;
        this.datumKreiranja = datumKreiranja;
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getRetypePassword() {
        return retypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }

    public void setAuthorities(List<Rolee> authorities) {
        this.authorities = authorities;
    }

    public List<Rolee> getAuthorities() {
        return authorities;
    }

    public Collection<Kontakt> getSviKontakti() {
        return sviKontakti;
    }

    public void setSviKontakti(Collection<Kontakt> sviKontakti) {
        this.sviKontakti = sviKontakti;
    }

    public String getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(String datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getEmailReset() {
        return emailReset;
    }

    public void setEmailReset(String emailReset) {
        this.emailReset = emailReset;
    }

}
