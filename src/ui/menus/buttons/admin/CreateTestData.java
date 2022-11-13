package ui.menus.buttons.admin;


import api.AdminResource;
import api.HotelResource;
import model.IRoom;
import model.RoomType;
import ui.menus.Menu;
import ui.menus.buttons.Button;


import java.util.*;

public class CreateTestData extends Button {

    public CreateTestData(Menu menu){
        super("Create test data", menu);
    }

    @Override
    public void press() {
        var input = inputYN("Do you want to create test data?(Y/n)");
        if (input.equalsIgnoreCase("y")){
            testDateCreate();
        }else{
            menu.backMenu();
        }
    }

    private void testDateCreate() {
        if (AdminResource.getDataTest()) {
            System.out.println("You can't create test data again");
        } else {
            createTestDate();
            System.out.println("Data has created successfully");
        }
        menu.backMenu();
    }

    private void createTestDate() {
        List<IRoom> rooms = new ArrayList<>();

        var room1 = AdminResource.createARoom("1", 50d, RoomType.SINGLE);
        var room2= AdminResource.createARoom("2", 100d, RoomType.DOUBLE);
        var room3= AdminResource.createARoom("3", 0d, RoomType.SINGLE);
        var room4= AdminResource.createARoom("4", 0d, RoomType.DOUBLE);

        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        rooms.add(room4);

        AdminResource.addRoom(rooms);

        HotelResource.createACustomer("sen@gmail.com", "Sean", "Poll");
        HotelResource.createACustomer("den@gmail.com", "Den", "Brown");
        HotelResource.createACustomer("alex@gmail.com", "Alex", "White");
        HotelResource.createACustomer("sara@gmail.com", "Sara", "Beam");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Integer h = 24;

        HotelResource.bookARoom("sen@gmail.com", room1, updateTime(calendar, 0), updateTime(calendar, h));
        HotelResource.bookARoom("den@gmail.com", room2, updateTime(calendar, h), updateTime(calendar, h));
        HotelResource.bookARoom("alex@gmail.com", room3, updateTime(calendar, h), updateTime(calendar, h));
        HotelResource.bookARoom("sara@gmail.com", room4, updateTime(calendar, h), updateTime(calendar, h));

        AdminResource.setDataTest();
    }
}
