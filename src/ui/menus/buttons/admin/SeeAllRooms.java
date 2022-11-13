package ui.menus.buttons.admin;

import api.AdminResource;
import ui.menus.Menu;
import ui.menus.buttons.Button;

public class SeeAllRooms extends Button {

    public SeeAllRooms(Menu menu) {
        super("See all rooms.", menu);
    }

    @Override
    public void press(){
        var rooms = AdminResource.getAllRooms();
        rooms.forEach(System.out::println);
        menu.backMenu();
    }
}
