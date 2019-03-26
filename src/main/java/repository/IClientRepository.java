package repository;

import model.Client;

public interface IClientRepository extends IRepository<Client> {
    int findOne(Client client);
}
