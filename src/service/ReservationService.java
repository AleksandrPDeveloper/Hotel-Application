package service;

import model.*;

import java.util.*;

public class ReservationService {

    private static ReservationService instance = ReservationService.getInstance();
    private final SortedMap<String, IRoom> rooms;
    private final SortedSet<Reservation> reservations;

    private Boolean testDataCreated;

    public Boolean getTestDataCreated() {
        return testDataCreated;
    }

    public void setTestDataCreated(Boolean testDataCreated) {
        this.testDataCreated = testDataCreated;
    }

    public ReservationService(){
        rooms = new TreeMap<>();
        reservations = new TreeSet<>();
        testDataCreated = false;
    }
    private static ReservationService getInstance() {
        if (instance == null){
            instance = new ReservationService();
        }

        return instance;
    }

    public static void setTestData(Boolean testData){
        instance.setTestDataCreated(testData);
    }

    public static Boolean getTestData(){
        return instance.getTestDataCreated();
    }
    public static void addRoom(IRoom room){
        try {
            putRoom(room.getRoomNumber(), room);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            System.out.printf("Room with number %s isn't added\n", room.getRoomNumber());
        }


    }
    public static IRoom getARoom(String roomId){
        return instance.rooms.get(roomId);
    }

    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservation = null;
        try {
            reservation = new Reservation(customer, room, checkInDate,checkOutDate);
            addReserve(reservation);
            return reservation;
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            System.out.printf("Reserve: %s isn't added", reservation);
        }
        return reservation;
    }

    public static Collection<Reservation> getCustomersReservation(Customer customer){
        List<Reservation> customersReservation = new ArrayList<>();

        for (Reservation reserve: instance.reservations){
            if (reserve.getCustomer().equals(customer)){
                customersReservation.add(reserve);
            }
        }
        return customersReservation;
    }

    public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate, Boolean free){
        Set<IRoom> reservedRooms = new HashSet<>();
        Set<IRoom> freeRooms = new HashSet<>(getAllRooms());

        for (Reservation reserve: instance.reservations){
            if (reserve.getRoom().isFree() != free || roomReserved(reserve, checkInDate, checkOutDate)){
                reservedRooms.add(reserve.getRoom());
            }
        }

        freeRooms.removeAll(reservedRooms);

        return freeRooms;
    }

    public static Collection<IRoom> getAllRooms(){
        return instance.rooms.values();
    }

    public static void printAllReservations(){
        if (instance.reservations.isEmpty()){
            System.out.println("Reservations is not found.");
            return;
        }
        for (Reservation reservation : instance.reservations) {
            System.out.println(reservation);
        }
    }

    public static IRoom createARoom(String roomId, Double price, RoomType enumeration) {

        if (price == 0) {
            return new FreeRoom(roomId, enumeration);
        } else {
            return new Room(roomId, price, enumeration);
        }
    }

    static Boolean roomReserved(Reservation reserve, Date checkInDate, Date checkOutDate){
        Date reserveStart = reserve.getCheckInDate();
        Date reserveEnd = reserve.getCheckOutDate();

        return (reserveStart.compareTo(checkInDate) <= 0
                && reserveEnd.compareTo(checkInDate) >= 0)
                || (reserveStart.compareTo(checkOutDate) <= 0
                && reserveEnd.compareTo(checkOutDate) >= 0);
    }
    private static void addReserve(Reservation reserve) throws ItemAlreadyExistsException {
        if (instance.reservations.contains(reserve)){
            String msg = "Reservation: \"" + reserve + "\" already exists.";
            throw new ItemAlreadyExistsException(msg);
        }else{
            instance.reservations.add(reserve);
        }

    }
    private static void putRoom(String number, IRoom room) throws ItemAlreadyExistsException {
        if (instance.rooms.containsKey(number))
        {
            String msg = "Room with \"" + number + "\" already exists.";
            throw new ItemAlreadyExistsException(msg);
        }else{
            instance.rooms.put(number, room);
        }
    }
}
