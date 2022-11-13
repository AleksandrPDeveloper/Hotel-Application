package ui.menus.buttons.main;

import api.HotelResource;
import ui.menus.Menu;
import ui.menus.buttons.Button;

public class SeeMyReservations extends Button {

    public SeeMyReservations(Menu menu) {
        super("See my reservations.", menu);
    }

    @Override
    public void press() {
        var email = inputEmail("Input e-mail in format \"email@domain.com\"");
        var customer = HotelResource.getCostumer(email);

        if (customer == null) {
            var msg = "User with the e-mail  \"%s\" is not found\n";
            System.out.printf(msg, email);
        }else{
            email = customer.getEmail();
            var reservations= HotelResource.getCustomersReservations(email);
            if (reservations.isEmpty()){
                System.out.printf("Reservations for account \"%s\" is not found.\n", email);
            }else{
            reservations.forEach(System.out::println);
            }
        }

        var yes = inputYN("Do you want to found reservations with another email?(Y/n)");

        if (yes.equalsIgnoreCase("y")){
            press();
        }else{
            menu.backMenu();
        }
    }
}
