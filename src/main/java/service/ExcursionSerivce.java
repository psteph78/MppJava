package service;

import model.Excursion;
import repository.ExcursionRepository;

import java.util.List;

public class ExcursionSerivce {
    ExcursionRepository repository = new ExcursionRepository();

    public List<Excursion> findExcursions(String landmark, int beginTimeInterval, int endTimeInterval){ return repository.findExcursions(landmark, beginTimeInterval, endTimeInterval); }
    public List<Excursion> findAllExcursions(){ return repository.findAllExcursions(); }
    public int findAvailablePlaces(String landmark, int leavingHour){ return repository.findAvailablePlaces(landmark, leavingHour); }
    public void updateTickets(int idExcursion, int nrTickets) { repository.updateTickets(idExcursion, nrTickets); }
    public int findExcursion(String landmark, int leavingHour) { return repository.findOne(landmark, leavingHour); }
}
