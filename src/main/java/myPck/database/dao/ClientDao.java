package myPck.database.dao;

import myPck.database.models.Client;
import myPck.database.utils.SessionManager;

import java.util.List;

public class ClientDao extends SessionManager implements Dao<Client> {

    public ClientDao() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Client> findAll() {
        List<Client> clients = (List<Client>) getCurrentSession().createQuery("from Client").list();
        return clients;
    }

    @Override
    public void delete(Client client) {
        getCurrentSession().delete(client);
    }

    @Override
    public void persist(Client entity) {
        getCurrentSession().save(entity);

    }

    @Override
    public void update(Client entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public Client findById(long id) {
        Client client = (Client) getCurrentSession().get(Client.class, id);
        return client;
    }

    @Override
    public void deleteAll() {
        List<Client> entityList = findAll();
        for (Client entity : entityList) {
            delete(entity);
        }
    }
}
