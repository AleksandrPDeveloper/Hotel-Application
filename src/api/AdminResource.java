package api;

import model.Customer;
import model.IRoom;
import model.RoomType;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    /** I don't know why in project requirements getCustomer used in two classes.
     * In this project, I use only HotelResource "getCustomer",
     * this duplicated method here because
     * it was assignments from project requirements.*/
    public static Customer getCustomer(String email){
        return CustomerService.getCustomer(email);
    }

    public static void addRoom(List<IRoom> rooms){
        for (IRoom room : rooms) {
            ReservationService.addRoom(room);
        }
    }

    public static Collection<IRoom> getAllRooms(){
        return ReservationService.getAllRooms();
    }

    public static Collection<Customer> getAllCustomers(){
        return CustomerService.getAllCustomers();
    }

    public static void displayAllReservations(){
        ReservationService.printAllReservations();
    }

    public static IRoom createARoom(String roomId, Double price, RoomType enumeration){
        return ReservationService.createARoom(roomId, price, enumeration);
    }

    public static void setDataTest(){
        ReservationService.setTestData(true);
        CustomerService.setTestData(true);
    }

    /** It isn't matter what exactly flag of installed we have,
     * because we finally install 2 services together.
     * This approach with two flags into services give us opportunity,
     * create data separately if it indeed, with minimal cost.*/
    public static Boolean getDataTest(){
        return ReservationService.getTestData();
    }
}
