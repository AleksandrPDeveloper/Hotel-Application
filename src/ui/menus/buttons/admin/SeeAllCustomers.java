package ui.menus.buttons.admin;

import api.AdminResource;
import ui.menus.Menu;
import ui.menus.buttons.Button;

public class SeeAllCustomers extends Button {

    public SeeAllCustomers(Menu menu) {
        super("See all customers.", menu);
    }

    @Override
    public void press() {
        var allCustomers = AdminResource.getAllCustomers();
        if (allCustomers.isEmpty()) {
            System.out.println("No one customer exists\n");
        }else{
            allCustomers.forEach(System.out::println);
        }
        menu.backMenu();
    }
}
