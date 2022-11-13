package ui.menus.buttons.main;

import api.HotelResource;
import model.IRoom;
import ui.menus.Menu;
import ui.menus.buttons.Button;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;


public class FindAndReserveARoom extends Button {

    private Date checkIn;
    private Date checkOut;
    private IRoom room;
    private Boolean free;

    public void setFree() {
        boolean free = false;
        String input = inputYN("Do you want find free rooms?(Y/n)");
        if (input.equalsIgnoreCase("y")){
            free = true;
        }

        this.free = free;
    }
    public void setRoom() {
        var input = inputDigits("Choose number of room.");
        this.room = HotelResource.getRoom(input);
    }

    public void setCheckIn() {
        Date date = inputDate("Please input CHECK IN date in following format YYYY-MM-DD");

        if (atStartOfDay(new Date()).compareTo(date) > 0){
            System.out.println("Date can't be in past");
            setCheckIn();
        }else{
            this.checkIn = date;
        }
    }

    public void setCheckOut() {
        Date date = inputDate("Please input CHECK OUT in following format YYYY-MM-DD");

        if (atStartOfDay(new Date()).compareTo(date) > 0){
            System.out.println("Date can't be in past");
            setCheckOut();
        }else{
            this.checkOut = date;
        }
    }

    public void setCheckInOffSet(Integer hours){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkIn);
        this.checkIn = updateTime(calendar, hours);
    }

    private void setCheckOutOffSet(Integer hours){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkOut);
        this.checkOut = updateTime(calendar, hours);
    }

    public FindAndReserveARoom(Menu menu) {
        super("Find and reserve room.", menu);
    }

    @Override
    public void press() {
        setDates();
        if (checkIn.after(checkOut)){
            System.out.println("Check-in date can't be after check-out. Please try again...");
            setDates();
        }
        setFree();
        findRooms();
    }

    private void setDates(){
        setCheckIn();
        setCheckOut();
    }

    private void findRooms(){
        var available = HotelResource.findARoom(checkIn, checkOut, free);

        if (available.isEmpty()){
            properRooms();
        }else {
            bookARoom(available);
        }
    }

    private void bookARoom(Collection <IRoom> available){
        available.forEach(System.out::println);
        setRoom();
        if (room == null){
            wrongRoom(available);
        }else{
            getCustomerAndBook();
        }
    }

    private void getCustomerAndBook() {
        var email = inputEmail("Input e-mail in format \"email@domain.com\"");

        String text = "Room can't be reserved.";

        var reserved = HotelResource.bookARoom(email, room, checkIn, checkOut);
        if (reserved != null) {
            text = "Room reserved successfully \n" + reserved;
        }

        System.out.println(text);
        reserveOtherRoom();
        menu.backMenu();

    }

    private void properRooms(){

        String formatText = "Rooms is not found between check-in date %1$tY-%1$tm-%1$td and check-out date %2$tY-%2$tm-%2$td\n";
        System.out.printf(formatText, checkIn, checkOut);

        var input  = inputYN("Do you want try another dates?(Y/n)");

        if (input.equalsIgnoreCase("y")){

            input = inputDigits("Input amount days offset check-in and check-out dates?");
            int days = Integer.parseInt(input);
            setCheckInOffSet(days * 24);
            setCheckOutOffSet(days * 24);
            findRooms();
        }else{
            menu.backMenu();
        }
    }

    private void wrongRoom(Collection <IRoom> available){
        var input = inputYN("You choose wrong number of room. Try again Yes or No (y/n)...");
        if (input.equalsIgnoreCase("y")){
            bookARoom(available);
        }else{
            menu.backMenu();
        }

    }

    private void reserveOtherRoom(){
        var yes = inputYN("You want to reserve next room? Choose y/n?");

        if (yes.toLowerCase().contains("y")){
            press();
        }else{
            menu.backMenu();
        }
    }
}


