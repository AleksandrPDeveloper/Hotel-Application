package service;

import model.*;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * This class for tests.
 */
public class Tester {
    public static void main(String[] args) {
        // test CustomerService class
        System.out.println("Test CustomerService class");
        // test addCustomer
        CustomerService.addCustomer("Bob@mail.gg", "Bob", "Celso");
        CustomerService.addCustomer("Rob@mail.eas", "Rob", "Brown");

        // test allCustomers
        Collection<Customer> allCustomers= CustomerService.getAllCustomers();
        for (Customer customer: allCustomers
             ) {
            System.out.println(customer);
        }

        // test getCustomer
        System.out.println(CustomerService.getCustomer("Bob@mail.gg"));
        System.out.println(CustomerService.getCustomer("ddd@mail.gg"));

        // test ReservationService class
        // test addRoom
        ReservationService.addRoom(new Room("213", 140d, RoomType.SINGLE));
        ReservationService.addRoom(new FreeRoom("221", RoomType.SINGLE));

        System.out.println("Test ReservationService class");

        // test getARoom
        System.out.println(ReservationService.getARoom("213"));
        System.out.println(ReservationService.getARoom("444"));

        // test getAllRooms
        Collection<IRoom> allRooms = ReservationService.getAllRooms();
        for (IRoom room: allRooms
             ) {
            System.out.println(room);
        }
        // initialize dates
        Calendar date = Calendar.getInstance();
        Date checkInDate;
        date.set(2022, Calendar.FEBRUARY, 1);
        checkInDate = date.getTime();

        Date checkOutDate;
        date.set(2022, Calendar.FEBRUARY, 30);
        checkOutDate = date.getTime();

        // test reserveARoom
        Customer reserveCustomer = CustomerService.getCustomer("Bob@mail.gg");
        IRoom reserveRoom = ReservationService.getARoom("213");
        ReservationService.reserveARoom(reserveCustomer, reserveRoom, checkInDate, checkOutDate);


        // test findRooms
        System.out.println("Test free rooms");
        Collection<IRoom> freeRooms = ReservationService.findRooms(checkInDate, checkOutDate, false);
        for (IRoom freeRoom: freeRooms
             ) {
            System.out.println(freeRoom);
        }
    }
}
