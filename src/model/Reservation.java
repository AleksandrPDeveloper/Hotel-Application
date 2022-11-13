package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation implements Comparable<Reservation>{
    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        setRoom(room);
        setCustomer(customer);
        setCheckInDate(checkInDate);
        setCheckOutDate(checkOutDate);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public IRoom getRoom() {
        return room;
    }

    public void setRoom(IRoom room) {
        this.room = room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return "Customer: "
                + customer.fullName()
                + " "
                + "room : "
                + room.getRoomNumber()
                + " "
                + "check in date: "
                + dateFormat.format(checkInDate) + " "
                + "check out date: "
                + dateFormat.format(checkOutDate)
                + "\n";
    }

    @Override
    public int hashCode() {
        var result = customer.hashCode();
        result = 31 * result + room.hashCode();
        result = 31 * result + checkInDate.hashCode();

        return 31 * result * checkOutDate.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }

        if ((obj == null)||(getClass() != obj.getClass())){
            return false;
        }

        Reservation reservation = (Reservation) obj;

        return customer.equals(reservation.customer)
                && room.equals(reservation.room)
                && checkInDate.equals(reservation.checkInDate)
                && checkOutDate.equals(reservation.getCheckOutDate());
    }

    @Override
    public int compareTo(Reservation o) {
        return checkInDate.compareTo(o.checkInDate);
    }
}
