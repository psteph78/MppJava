package repository;

import model.Client;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientRepositoryTest {

    @Test
    public void findOne() {
        ClientRepository repo = new ClientRepository();
        Client client = new Client("Pricop", "Stefania", "0746848443");
        Client cl = new Client("s", "b", "5");
        int id = repo.findOne(client);
        assertEquals(id,1);
        id = repo.findOne(cl);
        assertEquals(id, 4);
    }
}