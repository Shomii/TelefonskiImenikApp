package tel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import tel.dao.RoleDAO;
import tel.domain.Rolee;
import tel.service.ServisRola;

public class ServisRolaImpl implements ServisRola {

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public Rolee nadjiRolu(Integer roleeID) {

        Rolee nadjiRolu = roleDAO.nadjiRolu(roleeID);

        return nadjiRolu;
    }

}
