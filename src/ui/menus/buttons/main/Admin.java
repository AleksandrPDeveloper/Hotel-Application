package ui.menus.buttons.main;

import ui.menus.AdminMenu;
import ui.menus.Menu;
import ui.menus.buttons.Button;

public class Admin extends Button {

    public Admin(Menu menu) {
        super("Admin.", menu);
    }

    @Override
    public void press() {
        var adminMenu = new AdminMenu();
        adminMenu.backMenu();
    }
}
