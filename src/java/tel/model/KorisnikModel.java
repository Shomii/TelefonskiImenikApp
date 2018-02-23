package tel.model;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class KorisnikModel {

    private int id;

    @Size(min = 3, message = "Ime mora biti veće od 3 karaktera.")
    private String ime;

    @Size(min = 5, message = "Prezime mora biti veće od 5 karaktera.")
    private String prezime;

    private String email;

    @Size(min = 3, max = 14, message = "Korisničko ime mora biti veće od 3 karaktera.")
    private String username;

    @Size(min = 3, message = "Lozinka mora biti veće od 3 karaktera.")
    private String password;

    private String datumKreiranja;

    private boolean enabled;

    @Transient
    @Size(min = 3, message = "Lozinke se moraju podudarati.")
    private String retypePassword;

    @Transient
    private String oldPassword;

    @Transient
    private String newPassword;

    @Transient
    private String emailReset;

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
