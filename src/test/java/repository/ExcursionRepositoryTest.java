package repository;

import model.Excursion;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ExcursionRepositoryTest {

//    @Test
//    public void findExcursions() {
//        ExcursionRepository repo = new ExcursionRepository();
//        List<Excursion> excursions = repo.findExcursions("Sighisoara", 12, 14);
//        assertEquals(3, excursions.size());
//    }
//
//    @Test
//    public void findAvailablePlaces() {
//        ExcursionRepository repo = new ExcursionRepository();
//        int availablePlaces = repo.findAvailablePlaces("Sighisoara", 12);
//        assertEquals(75, availablePlaces);
//    }
//
    @Test
    public void updateTickets() {
        ExcursionRepository repo = new ExcursionRepository();
        repo.updateTickets(1,10);
    }
}