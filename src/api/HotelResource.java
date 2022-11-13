package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    public static Customer getCostumer(String email){
        return CustomerService.getCustomer(email);
    }

    public static void createACustomer(String email, String firstname, String lastname){
        CustomerService.addCustomer(email, firstname, lastname);
    }

    public static IRoom getRoom(String roomNumber){
        return ReservationService.getARoom(roomNumber);
    }

    public static Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reserve = null;

        Customer customer = getCostumer(customerEmail);

        if (customer != null){
            reserve = ReservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
        }
        return reserve;
    }

    public static Collection<Reservation> getCustomersReservations(String customerEmail){
        Customer customer = getCostumer(customerEmail);
        return ReservationService.getCustomersReservation(customer);
    }

    public static Collection<IRoom>findARoom(Date checkIn, Date checkOut, Boolean free){
        return ReservationService.findRooms(checkIn, checkOut, free);
    }

}
