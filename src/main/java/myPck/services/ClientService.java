package myPck.services;

import myPck.database.dao.ClientDao;
import myPck.database.models.Client;

import java.util.List;

public class ClientService {
    /** @var myPck.database.models.ClientDao **/
    private ClientDao clientDao;

    /**
     * Konstruktor klasy ClientService i inicjalizacja ClientDao
     */
    public ClientService(){
        clientDao = new ClientDao();
    }

    /**
     * Metoda pobiera wszystkie samochody z bazy danych.
     * @return List<Client>
     */
    public List<Client> findAll() {
        clientDao.openCurrentSession();
        List<Client> clients = clientDao.findAll();
        clientDao.closeCurrentSession();

        return clients;
    }

    /**
     * Metoda zapisuje samochód do bazy danych.
     * @param entity
     */
    public void persist(Client entity) {
        clientDao.openCurrentSessionwithTransaction();
        clientDao.persist(entity);
        clientDao.closeCurrentSessionwithTransaction();
    }

    /**
     * Metoda aktualizuje dane o samochodzie w bazie danych.
     * @param Client entity
     */
    public void update(Client entity) {
        clientDao.openCurrentSessionwithTransaction();
        clientDao.update(entity);
        clientDao.closeCurrentSessionwithTransaction();
    }

    /**
     * Metoda znajduje samochód o podanym id.
     * @param String id
     * @return Client
     */
    public Client findById(String id) {
        clientDao.openCurrentSession();
        Client client = clientDao.findById(id);
        clientDao.closeCurrentSession();
        return client;
    }

    /**
     * Metoda usuwa z bazy danych samochó o podanym id.
     * @param String id
     */
    public void delete(String id) {
        clientDao.openCurrentSessionwithTransaction();
        Client client = clientDao.findById(id);
        clientDao.delete(client);
        clientDao.closeCurrentSessionwithTransaction();
    }

    /**
     * Metoda usuwa wszystkie samochody z bazy danych.
     */
    public void deleteAll() {
        clientDao.openCurrentSessionwithTransaction();
        clientDao.deleteAll();
        clientDao.closeCurrentSessionwithTransaction();
    }
}
