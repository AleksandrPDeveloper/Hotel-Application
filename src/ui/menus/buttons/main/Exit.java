package ui.menus.buttons.main;

import ui.input.InputScanner;
import ui.menus.Menu;
import ui.menus.buttons.Button;

public class Exit extends Button {

    public Exit(Menu menu) {
        super("Exit.", menu);
    }

    @Override
    public void press() {
        System.out.println("Program has finished.");
        InputScanner.closeScanner();
    }
}
