package ui.menus.buttons.admin;

import ui.menus.MainMenu;
import ui.menus.Menu;
import ui.menus.buttons.Button;

public class BackToMainMenu extends Button {

    public BackToMainMenu(Menu menu) {
        super("Back to main menu.", menu);
    }

    @Override
    public void press() {
        var menu = new MainMenu();
        menu.backMenu();
    }
}
