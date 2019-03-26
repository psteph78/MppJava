package model;

public class Excursion {
    private String landmark;
    private int leavingHour;
    private int availablePlaces;
    private String transportCompany;
    private double price;

    public Excursion(String landmark, int leavingHour, int availablePlaces, String transportCompany, double price) {
        this.landmark = landmark;
        this.leavingHour = leavingHour;
        this.availablePlaces = availablePlaces;
        this.transportCompany = transportCompany;
        this.price = price;
    }

    public String getLandmark() {return landmark; }

    public void setLandmark(String landmark) { this.landmark = landmark; }

    public int getLeavingHour() {return leavingHour; }

    public void setLeavingHour(int leavingHour) {
        this.leavingHour = leavingHour;
    }

    public int getAvailablePlaces() {
        return availablePlaces;
    }

    public void setAvailablePlaces(int availablePlaces) {
        this.availablePlaces = availablePlaces;
    }

    public String getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(String transportCompany) {
        this.transportCompany = transportCompany;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
