package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    private static CustomerService instance = CustomerService.getInstance();
    private final Map<String, Customer> mapCustomers;
    private Boolean testDataCreated;

    public Boolean getTestDataCreated() {
        return testDataCreated;
    }

    public void setTestDataCreated(Boolean testDataCreated) {
        this.testDataCreated = testDataCreated;
    }

    public CustomerService() {
        mapCustomers = new HashMap<>();
        testDataCreated = false;
    }

    public static void setTestData(Boolean testData){
        instance.setTestDataCreated(testData);
    }

    public static Boolean getTestData(){
        return instance.getTestDataCreated();
    }
    public static CustomerService getInstance(){
        if (instance == null) {
            instance = new CustomerService();
        }
        return instance;
    }

    public static void addCustomer(String email, String firstname, String lastname){
        try {
            Customer customer = new Customer(firstname, lastname, email);
            putCustomer(email.toLowerCase(), customer);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            System.out.printf("Customer with e-mail %s isn't added\n", email);
        }
    }

    public static Customer getCustomer(String customerEmail){
        Customer customer = instance.mapCustomers.get(customerEmail);
        if (customer == null) {
            System.out.printf("Customer with email \" %s\" is not found.\n", customerEmail);
        }
        return  customer;
    }
    private static void putCustomer(String email, Customer customer) throws ItemAlreadyExistsException {

        if (instance.mapCustomers.containsKey(email))
        {
            String msg = "User with \"" + email + "\" already exists.";
            throw new ItemAlreadyExistsException(msg);
        }else{
            instance.mapCustomers.put(email, customer);
        }
    }

    public static Collection<Customer> getAllCustomers(){
        return instance.mapCustomers.values();
    }
}
