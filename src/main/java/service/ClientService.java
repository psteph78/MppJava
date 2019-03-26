package service;

import model.Client;
import repository.ClientRepository;

public class ClientService {
    ClientRepository repository = new ClientRepository();

    public int findClient(Client client){ return repository.findOne(client); }
}
