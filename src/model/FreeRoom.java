package model;

public class FreeRoom extends Room{

    public FreeRoom(String roomNumber, RoomType enumeration) {
        super(roomNumber, 0d, enumeration);
    }

    @Override
    public String toString() {
        return "Room number: "
                + getRoomNumber()
                + " "
                + "enumeration: "
                + getRoomType()
                + " price: 0$\n";
    }

    @Override
    public Boolean isFree() {
        return true;
    }
}
