package model;

public class Client {
    private String firstName;
    private String lastName;
    private String telephoneNr;

    public Client(String firstName, String lastName, String telephoneNr) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephoneNr = telephoneNr;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephoneNr() {
        return telephoneNr;
    }

    public void setTelephoneNr(String telephoneNr) {
        this.telephoneNr = telephoneNr;
    }
}
