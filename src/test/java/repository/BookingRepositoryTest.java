package repository;

import org.junit.Test;

import static org.junit.Assert.*;

public class BookingRepositoryTest {

    @Test
    public void addBooking() {
        BookingRepository repo = new BookingRepository();
        repo.addBooking(1,1,10);
    }
//
//    @Test
//    public void deleteBooking() {
//        BookingRepository repo = new BookingRepository();
//        repo.deleteBooking(2,7);
//    }
}