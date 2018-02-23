package tel.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tel.dao.KontaktDAO;
import tel.domain.Kontakt;

@Repository
public class KontaktDAOImpl implements KontaktDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    @Override
    public void dodavanjeNovogKontakta(Kontakt kontakt) {

        Session s = getCurrentSession();
        Transaction trans = s.beginTransaction();
        getCurrentSession().save(kontakt);
        trans.commit();

    }

    @Override
    public List<Kontakt> sviKontakti() {

        Session s = getCurrentSession();
        Transaction trans = s.beginTransaction();
        List<Kontakt> kontakti = s.createCriteria(Kontakt.class).list();
        trans.commit();

        return kontakti;
    }

    @Override
    public Kontakt podaciOKontaktu(Kontakt kontakt) {
        Session s = getCurrentSession();
        Transaction trans = s.beginTransaction();
        Kontakt kont = (Kontakt) s.get(Kontakt.class, kontakt);
        trans.commit();

        return kontakt;
    }

    @Override
    public Kontakt kontaktiId(Integer kontaktID) {

        Session s = getCurrentSession();
        Transaction trans = s.beginTransaction();
        Kontakt kont = (Kontakt) s.get(Kontakt.class, kontaktID);
        trans.commit();

        return kont;
    }

    @Override
    public Kontakt kontaktIme(String ime) {

        Session s = getCurrentSession();
        Transaction trans = s.beginTransaction();
        Kontakt kont = (Kontakt) s.get(Kontakt.class, ime);
        trans.commit();

        return kont;
    }

    @Override
    public void azuriranje(Kontakt kontakt) {

        Session s = getCurrentSession();
        Transaction trans = s.beginTransaction();
        getCurrentSession().update(kontakt);
        trans.commit();
    }

    @Override
    public void brisanje(Kontakt kontakt) {
        Session s = getCurrentSession();
        Transaction trans = s.beginTransaction();
        getCurrentSession().delete(kontakt);
        trans.commit();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
