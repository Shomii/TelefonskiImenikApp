package tel.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tel.dao.KorisnikDAO;
import tel.dao.RoleDAO;
import tel.domain.Korisnik;
import tel.domain.Rolee;
import tel.model.KorisnikModel;
import tel.service.ServisKorisnika;

@Service
public class ServisKorisnikaImpl implements ServisKorisnika {

    @Autowired
    private KorisnikDAO korisnikDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void registracijaKorisnika(KorisnikModel korisnikModel) {

        Korisnik korisnik = new Korisnik();

        korisnik.setIme(korisnikModel.getIme());
        korisnik.setPrezime(korisnikModel.getPrezime());
        korisnik.setEmail(korisnikModel.getEmail());
        korisnik.setUsername(korisnikModel.getUsername());
        korisnik.setPassword(passwordEncoder.encode(korisnikModel.getPassword()));
        korisnik.setDatumKreiranja(korisnikModel.getDatumKreiranja());

        Rolee r = roleDAO.nadjiRolu(1);
        List<Rolee> roles = new ArrayList<Rolee>();
        roles.add(r);
        korisnik.setAuthorities(roles);

        getKorisnikDAO().registracijaKorisnika(korisnik);
    }

    @Override
    public List<KorisnikModel> sviKorisnici() {
        List<KorisnikModel> korisnikModeli = new ArrayList<>();
        List<Korisnik> korisnici = getKorisnikDAO().sviKorisnici();
        KorisnikModel korisnikModel = new KorisnikModel();

        for (Korisnik korisnik : korisnici) {

            korisnikModel = new KorisnikModel();
            korisnikModel.setId(korisnik.getId());
            korisnikModel.setIme(korisnik.getIme());
            korisnikModel.setPrezime(korisnik.getPrezime());
            korisnikModel.setEmail(korisnik.getEmail());
            korisnikModel.setUsername(korisnik.getUsername());
            korisnikModel.setPassword(passwordEncoder.encode(korisnik.getPassword()));
            korisnikModel.setDatumKreiranja(korisnik.getDatumKreiranja());
            korisnikModel.setEnabled(korisnik.isEnabled());
            korisnikModeli.add(korisnikModel);
        }

        return korisnikModeli;
    }

    @Override
    public KorisnikModel korisnikId(Integer id) {

        Korisnik brisanjeKorisnika = getKorisnikDAO().korisnikId(id);
        KorisnikModel korisnikModel = new KorisnikModel();
        korisnikModel.setId(brisanjeKorisnika.getId());

        return korisnikModel;
    }

    @Override
    public KorisnikModel korisnikZaLog(Integer id) {

        Korisnik korisnik = getKorisnikDAO().korisnikId(id);
        KorisnikModel korisnikModel = null;
        korisnikModel.setId(korisnik.getId());

        return korisnikModel;
    }

    @Override
    public void brisanje(KorisnikModel korisnikModel) {
        Korisnik korisnik = new Korisnik();
        korisnik.setId(korisnikModel.getId());
        getKorisnikDAO().brisanje(korisnik);
    }

    @Override
    public KorisnikModel getKorisnikModel(String username) {

        Korisnik korisnik = korisnikDAO.getKorisnik(username);
        KorisnikModel korisnikModel = new KorisnikModel();
        korisnikModel.setUsername(korisnik.getUsername());

        return korisnikModel;
    }

    @Override
    public KorisnikModel findByUsername(String username) {

        Korisnik korisnik = korisnikDAO.findByUsername(username);
        KorisnikModel korisnikModel = null;
        korisnikModel.setUsername(korisnik.getUsername());

        return korisnikModel;
    }

    @Override
    public KorisnikModel findByEmail(String email) {

        Korisnik korisnik = korisnikDAO.findByEmail(email);
        KorisnikModel korisnikModel = new KorisnikModel();

        //korisnikModel.setEmail(korisnik.getEmail());
        korisnikModel.setId(korisnik.getId());
        korisnikModel.setIme(korisnik.getIme());
        korisnikModel.setPrezime(korisnik.getPrezime());
        korisnikModel.setEmail(korisnik.getEmail());
        korisnikModel.setUsername(korisnik.getUsername());
        korisnikModel.setPassword(passwordEncoder.encode(korisnik.getPassword()));
        korisnikModel.setDatumKreiranja(korisnik.getDatumKreiranja());
        korisnikModel.setEnabled(korisnik.isEnabled());

        return korisnikModel;
    }

    @Override
    public void azuriranje(KorisnikModel korisnikModel) {

        Korisnik korisnik = new Korisnik();

        korisnik.setId(korisnikModel.getId());
        korisnik.setIme(korisnikModel.getIme());
        korisnik.setPrezime(korisnikModel.getPrezime());
        korisnik.setEmail(korisnikModel.getEmail());
        korisnik.setDatumKreiranja(korisnikModel.getDatumKreiranja());
        korisnik.setEnabled(korisnikModel.isEnabled());
        korisnik.setPassword(korisnikModel.getPassword());
        korisnik.setUsername(korisnikModel.getUsername());

        Rolee r = roleDAO.nadjiRolu(1);
        List<Rolee> roles = new ArrayList<Rolee>();
        roles.add(r);
        korisnik.setAuthorities(roles);

        getKorisnikDAO().azuriranje(korisnik);
    }

    @Override
    public KorisnikModel findByPassword(String password) {
        Korisnik korisnik = null;
        KorisnikModel korisnikModel = null;
        korisnikModel.setPassword(korisnik.getPassword());

        return korisnikModel;
    }

    @Override
    public KorisnikModel findByRetypePassword(String retypePassword) {
        Korisnik korisnik = null;
        KorisnikModel korisnikModel = null;
        korisnikModel.setRetypePassword(korisnik.getRetypePassword());

        return korisnikModel;
    }

    public KorisnikDAO getKorisnikDAO() {
        return korisnikDAO;
    }

    public void setKorisnikDAO(KorisnikDAO korisnikDAO) {
        this.korisnikDAO = korisnikDAO;
    }

}
