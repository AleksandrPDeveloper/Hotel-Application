package ui.menus.buttons.admin;

import api.AdminResource;
import ui.menus.Menu;
import ui.menus.buttons.Button;

public class SeeAllReservations extends Button {

    public SeeAllReservations(Menu menu) {
        super("See all reservations.", menu);
    }

    @Override
    public void press() {
        AdminResource.displayAllReservations();
        menu.backMenu();
    }
}
