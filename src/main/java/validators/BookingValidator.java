package validators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.BookingRepository;
import repository.ExcursionRepository;

public class BookingValidator {
    private static final Logger logger = LogManager.getLogger(BookingRepository.class);
    private ExcursionRepository repository = new ExcursionRepository();

    public boolean validTransaction(String landmark, int leavingHour, int nrTickets){
        logger.traceEntry();
        logger.trace("Obtaining total number of available places.");
        int totalTickets = repository.findAvailablePlaces(landmark, leavingHour);
        if (nrTickets > totalTickets){
            logger.trace("Transaction is invalid; there are not enough available places.");
            return false;
        }
        logger.trace("Transaction is valid; there are enough available places.");
        return true;
    }
}
