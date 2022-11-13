package model;

public class Room implements IRoom, Comparable<IRoom>{

    private final String roomNumber;

    private final RoomType enumeration;

    private final Double price;

    public Room(String roomNumber, Double price, RoomType enumeration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    @Override
    public Boolean isFree() {
        return false;
    }

    @Override
    public String toString() {
        return "Room number: "
                + getRoomNumber()
                + " "
                + "enumeration: "
                + getRoomType()
                + " "
                + "price: "
                + getRoomPrice()
                + "$\n";

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }

        if ((obj == null) || getClass() != obj.getClass()){
            return false;
        }

        Room room = (Room) obj;

        return roomNumber.equals(room.roomNumber);
    }

    @Override
    public int hashCode() {
        return roomNumber.hashCode();
    }

    @Override
    public int compareTo(IRoom o) {
        return roomNumber.compareTo(o.getRoomNumber());
    }
}
