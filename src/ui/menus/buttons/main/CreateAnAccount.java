package ui.menus.buttons.main;

import api.HotelResource;
import ui.menus.Menu;
import ui.menus.buttons.Button;

public class CreateAnAccount extends Button {
    private String firstName;
    private String lastName;
    private String email;

    public void setFirstName() {
        this.firstName = inputName("Input first name...");
    }

    public void setLastName() {
        this.lastName = inputName("Input last name...");
    }

    public void setEmail() {
        this.email = inputEmail("Input e-mail in format \"email@domain.com\"");
    }

    public CreateAnAccount(Menu menu) {
        super("Create an account.", menu);
    }

    @Override
    public void press() {

        setFirstName();
        setLastName();
        setEmail();

        HotelResource.createACustomer(email, firstName, lastName);

        var yes = inputYN("You want to add next account? Choose y/n?");

        if (yes.toLowerCase().contains("y")){
            press();
        }else{
            menu.backMenu();
        }
    }
}
