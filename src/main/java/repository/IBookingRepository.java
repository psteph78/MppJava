package repository;

import model.Booking;

public interface IBookingRepository extends IRepository<Booking> {
    public void addBooking(int idClient, int idExcursion, int nrTickets);
    public void deleteBooking(int idClient, int idExcursion);
}
