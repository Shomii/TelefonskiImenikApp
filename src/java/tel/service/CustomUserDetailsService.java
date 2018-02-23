package tel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tel.dao.KorisnikDAO;
import tel.domain.Korisnik;
import tel.domain.Rolee;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private KorisnikDAO korisnikDAO;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Korisnik korisnik = korisnikDAO.getKorisnik(username);

        if (korisnik == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        } else if (korisnik.isEnabled() == false) {
            System.out.println("User not activeted");
            throw new UsernameNotFoundException("Za " + korisnik.getUsername() + " not activeted");
        }
        return new org.springframework.security.core.userdetails.User(korisnik.getUsername(), korisnik.getPassword(),
                korisnik.isEnabled(), true, true, true, getGrantedAuthorities(korisnik));
    }

    private List<GrantedAuthority> getGrantedAuthorities(Korisnik user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (Rolee roles : user.getAuthorities()) {
            authorities.add(new SimpleGrantedAuthority(roles.getRole()));
        }
        return authorities;
    }
}
