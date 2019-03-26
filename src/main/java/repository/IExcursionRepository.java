package repository;

import model.Excursion;

import java.util.List;

public interface IExcursionRepository extends IRepository<Excursion> {
    List<Excursion> findExcursions(String landmark, int beginTimeInterval, int endTimeInterval);
    List<Excursion> findAllExcursions();
    int findAvailablePlaces(String landmark, int leavingHour);
    void updateTickets(int idExcursion, int nrTickets);
    int findOne(String landmark, int leavingHour);

}
