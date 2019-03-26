package model;

public class Booking {
    private int clientID;
    private int excursionID;
    private int nrTickets;

    public Booking(int clientID, int excursionID, int nrTickets) {
        this.clientID = clientID;
        this.excursionID = excursionID;
        this.nrTickets = nrTickets;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getExcursionID() {
        return excursionID;
    }

    public void setExcursionID(int excursionID) {
        this.excursionID = excursionID;
    }

    public int getNrTickets() {
        return nrTickets;
    }

    public void setNrTickets(int nrTickets) {
        this.nrTickets = nrTickets;
    }
}
