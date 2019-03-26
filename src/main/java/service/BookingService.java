package service;

import repository.BookingRepository;

public class BookingService {
    BookingRepository repository = new BookingRepository();

    public void addBooking(int idClient, int idExcursion, int nrTickets){ repository.addBooking(idClient, idExcursion, nrTickets); }
    public void deleteBooking(int idClient, int idExcursion){ repository.deleteBooking(idClient, idExcursion); }

}
