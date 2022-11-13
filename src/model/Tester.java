package model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * This class for tests.
 */
public class Tester {
    public static void main(String[] args) {
        // test customer.
        Customer costumer = new Customer("first", "second", "j@domdain.com");
        System.out.println(costumer);

        // test customer invalid email
        try {
            Customer costumerEx = new Customer("first", "second", "mail");
            System.out.println(costumerEx);
        }catch (IllegalArgumentException ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }

        // test IRoom, FreeRoom, Room, RoomType
        IRoom room = new Room("123", 100d, RoomType.DOUBLE);
        IRoom freeRoom = new FreeRoom("321", RoomType.SINGLE);

        Set<IRoom> rooms = new HashSet<>();
        rooms.add(room);
        rooms.add(freeRoom);

        rooms.forEach(System.out::println);

        // test reservation
        Calendar calendar = Calendar.getInstance();

        calendar.set(2031, Calendar.MARCH, 2);
        Date checkOutDate = calendar.getTime();

        calendar.set(2020, Calendar.FEBRUARY, 1);
        Date checkInDate = calendar.getTime();

        Reservation reservation = new Reservation(costumer, room, checkInDate, checkOutDate);

        System.out.println(reservation);

    }
}
