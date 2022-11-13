package ui.menus.buttons.admin;

import api.AdminResource;
import model.IRoom;
import model.RoomType;
import ui.menus.Menu;
import ui.menus.buttons.Button;

import java.util.ArrayList;
import java.util.List;


public class AddARoom extends Button {

    private final List<IRoom> rooms;
    private String roomNumber;
    private RoomType type;
    private Double price;
    public void setRoomNumber() {
        this.roomNumber = inputDigits("Input room number...");
    }

    public void setType() {
        RoomType type = RoomType.SINGLE;
        var enumeration = inputEnumeration("Choose how many places in the room: 1 or 2?");
        if (enumeration.equals("2")) {
            type = RoomType.DOUBLE;
        }
        this.type = type;
    }

    public void setPrice() {
        double price = 0d;

        var isFree = inputYN("Room is free? (Y or N)");

        if (isFree.toLowerCase().contains("n")){
            price = Double.parseDouble(inputDigits("Input price"));
        }
        this.price = price;
    }

    public AddARoom(Menu menu) {
        super("Add a room.", menu);
        this.rooms = new ArrayList<>();
    }

    @Override
    public void press() {

        setRoomNumber();
        setType();
        setPrice();

        rooms.add(AdminResource.createARoom(roomNumber, price, type));

        var yes = inputYN("You want to add next room? Choose y/n?");

        if (yes.equalsIgnoreCase("y")){
            press();
        }else{
            AdminResource.addRoom(rooms);
            rooms.clear();
            menu.backMenu();
        }
    }
}
