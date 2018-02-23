package tel.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Rolee implements Serializable {

    @Id
    @GeneratedValue
    private int roleeID;

    private String role;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "authorities")
    private Set<Korisnik> korisnickeRole;

    public int getRoleeID() {
        return roleeID;
    }

    public void setRoleeID(int roleeID) {
        this.roleeID = roleeID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Korisnik> getKorisnickeRole() {
        return korisnickeRole;
    }

    public void setKorisnickeRole(Set<Korisnik> korisnickeRole) {
        this.korisnickeRole = korisnickeRole;
    }

}
