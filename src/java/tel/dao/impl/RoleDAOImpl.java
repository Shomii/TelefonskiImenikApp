package tel.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tel.dao.RoleDAO;
import tel.domain.Rolee;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    @Override
    public Rolee nadjiRolu(Integer roleeID) {
        Session s = getCurrentSession();
        Transaction trans = s.beginTransaction();
        Rolee rola = (Rolee) s.get(Rolee.class, roleeID);

        trans.commit();

        return rola;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
