package model;


import ui.input.InputScanner;
import ui.input.Patterns;


public final class Customer {
    private final String firstName;
    private final String lastName;
    private final String email;

    public Customer(String firstName, String lastName, String email) {
        // I made this only for project requirements.
        // I've already done check data for proper input, when user was inputting e-mail.
        if(!InputScanner.isProperInput(email, Patterns.EMAIL.getPattern())){
            throw new IllegalArgumentException("Incorrect input e-mail \"email@domain.com\" format indeed");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String fullName(){
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Name: "
                + firstName
                + " "
                + lastName
                + " "
                + "email: "
                + email
                + "\n";
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this){
            return true;
        }

        if ((obj == null) || getClass() != obj.getClass()){
            return false;
        }

        Customer customer = (Customer) obj;

        return email.equals(customer.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}
