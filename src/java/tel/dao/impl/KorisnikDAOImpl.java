package tel.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tel.dao.KorisnikDAO;
import tel.domain.Korisnik;

@Repository
public class KorisnikDAOImpl implements KorisnikDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session openSession() {
        return sessionFactory.getCurrentSession();
    }

    public Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    @Override
    public void registracijaKorisnika(Korisnik korisnik) {

        Session s = getCurrentSession();
        Transaction trans = s.beginTransaction();
        getCurrentSession().save(korisnik);
        trans.commit();

    }

    @Override
    public void azuriranje(Korisnik korisnik) {

        Session s = getCurrentSession();
        Transaction trans = s.beginTransaction();
        getCurrentSession().update(korisnik);
        trans.commit();
    }

    @Override
    public List<Korisnik> sviKorisnici() {

        Session s = getCurrentSession();
        Transaction trans = s.beginTransaction();
        List<Korisnik> korisnici = s.createCriteria(Korisnik.class).list();
        trans.commit();

        return korisnici;
    }

    @Override
    public Korisnik korisnikId(Integer id) {
        Session s = getCurrentSession();
        Transaction trans = s.beginTransaction();
        Korisnik koris = (Korisnik) s.get(Korisnik.class, id);
        trans.commit();

        return koris;
    }

    @Override
    public Korisnik korisnikZaLog(Integer id) {
        Session s = getCurrentSession();
        Transaction trans = s.beginTransaction();
        Korisnik koris = (Korisnik) s.get(Korisnik.class, id);

        trans.commit();

        return koris;
    }

    @Override
    public void brisanje(Korisnik korisnik) {
        Transaction trans = null;
        Session s = null;
        try {
            s = getCurrentSession();
            trans = s.beginTransaction();
            getCurrentSession().delete(korisnik);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();

        }
    }

    @Override
    public Korisnik findByUsername(String username) {
        Session s = getCurrentSession();
        Transaction trans = s.beginTransaction();
        Korisnik koris = (Korisnik) s.get(Korisnik.class, username);

        trans.commit();

        return koris;
    }

    @Override
    public Korisnik findByEmail(String email) {

        Session s = getCurrentSession();
        Transaction trans = s.beginTransaction();

        List<Korisnik> listaKorisnika = new ArrayList<Korisnik>();
        Query query = openSession().createQuery("from korisnik k where k.email = :email");
        query.setParameter("email", email);
        listaKorisnika = query.list();
        trans.commit();

        if (listaKorisnika.size() > 0) {
            return listaKorisnika.get(0);
        } else {
            return null;
        }

    }

    @Override
    public Korisnik findByPassword(String password) {
        Session s = getCurrentSession();
        Transaction trans = s.beginTransaction();
        Korisnik koris = (Korisnik) s.get(Korisnik.class, password);

        trans.commit();

        return koris;
    }

    @Override
    public Korisnik findByRetypePassword(String retypePassword) {
        Session s = getCurrentSession();
        Transaction trans = s.beginTransaction();
        Korisnik koris = (Korisnik) s.get(Korisnik.class, retypePassword);

        trans.commit();

        return koris;
    }

    @Override
    public Korisnik getKorisnik(String username) {
        Session s = getCurrentSession();
        Transaction trans = s.beginTransaction();

        List<Korisnik> listaKorisnika = new ArrayList<Korisnik>();
        Query query = openSession().createQuery("from korisnik k where k.username = :username");
        query.setParameter("username", username);
        listaKorisnika = query.list();
        trans.commit();

        if (listaKorisnika.size() > 0) {
            return listaKorisnika.get(0);
        } else {
            return null;
        }

    }

    @Override
    public Korisnik getUserById(int id) {
        Korisnik user = null;
        Transaction trns = null;
        Session session = getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from korisnik where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", id);
            user = (Korisnik) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return user;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
