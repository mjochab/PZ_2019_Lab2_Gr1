package myPck.database.utils;

import myPck.database.models.*;
import myPck.services.*;

import java.util.Date;
import java.util.Random;

public class DbManager {

    private UserService userService;
    private CarService carService;
    private ServiceService serviceService;
    private ClientService clientService;
    private InvoiceService invoiceService;
    private InvoicePositionService invoicePositionService;
    private ServicePathService servicePathService;
    private ServiceReportService serviceReportService;


    public DbManager () {
        this.userService = new UserService();
        this.carService = new CarService();
        this.serviceService = new ServiceService();
        this.clientService = new ClientService();

        this.invoicePositionService = new InvoicePositionService();
        this.invoiceService = new InvoiceService();
        this.servicePathService = new ServicePathService();
        this.serviceReportService = new ServiceReportService();

    }

    public DbManager(InvoiceService invoiceService, InvoicePositionService invoicePositionService, ServicePathService servicePartService, ServiceReportService serviceReportService) {
        this.invoiceService = invoiceService;
        this.invoicePositionService = invoicePositionService;
        this.servicePathService = servicePathService;
        this.serviceReportService = serviceReportService;
    }

    public void addSampleData() {
        Random generator = new Random();
        int numberOfRows = 10;

        String[] firstNames = {"Jan", "Roman"};
        String[] lastNames = {"Kowalski", "Nowak"};

        String[] carModels = {"A6", "E220"};
        String[] carBrands = {"Audi", "Mercedes"};
        String[] carTypes = {"Sedan", "Combi"};

        String[] dateInvoice = {"2018-07-03", "2014-03-21"};
        int[] priceInvoice = {850, 1200};
        int[] amountInvoice = {200,100};

        String[] loremIpsum = {"Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                " Vivamus vitae nisi eget nisl sagittis mollis in id diam. " +
                "Sed iaculis fringilla turpis in tempor. In quis risus ante. Donec et sapien massa." ,
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Vivamus vitae nisi eget nisl sagittis mollis in id diam. Sed iaculis fringilla turpis in tempor." +
                " In quis risus ante. Donec et sapien massa." };



        for (int i = 0; i <=numberOfRows; i++) {
            int index = generator.nextInt(2);
            Car car = this.populateCar(carModels[index], carBrands[index], carTypes[index]);
            User user = this.populateUser(firstNames[index], lastNames[index]);
            Client client = this.populateClient(firstNames[index], lastNames[index]);
            Service service = this.populateService(car, client);

            //Invoice invoice = this.populateInvoice(priceInvoice[index],amountInvoice[index]);


        }
    }







    public Service populateService(Car car, Client client) {
        Service service = new Service(client, car, "In service");
        this.serviceService.persist(service);

        return service;
    }

    public Client populateClient(String firstName, String lastName) {
        Client client = new Client(firstName,  lastName, 323232, "Rzeszów Pigonia 1");
        this.clientService.persist(client);

        return client;
    }

    public User populateUser(String firstName, String lastName) {
        User user = new User("email@o2.pl", firstName, lastName, "login", "password", "A");
        this.userService.persist(user);

        return user;
    }

    public Car populateCar(String model, String brand, String type) {
            Car car = new Car(model, brand, type, new Date());
            this.carService.persist(car);

            return car;
    }
    public InvoicePosition populateInvoicePosition(int price, String name)
    {
         InvoicePosition invoicePosition = new InvoicePosition(name, price);
         this.invoicePositionService.persist(invoicePosition);
         return invoicePosition;
    }





}
