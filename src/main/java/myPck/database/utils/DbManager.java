package myPck.database.utils;

import myPck.database.models.*;
import myPck.services.*;
import java.util.Date;
import java.util.Random;
import static myPck.utils.Password.hashPassword;

public class DbManager {
    private UserService userService;
    private CarService carService;
    private ServiceService serviceService;
    private ClientService clientService;
    private InvoiceService invoiceService;
    private InvoicePositionService invoicePositionService;
    private ServicePartService servicePathService;
    private ServiceReportService serviceReportService;

    public DbManager () {
        this.userService = new UserService();
        this.carService = new CarService();
        this.serviceService = new ServiceService();
        this.clientService = new ClientService();
        this.invoicePositionService = new InvoicePositionService();
        this.invoiceService = new InvoiceService();
        this.servicePathService = new ServicePartService();
        this.serviceReportService = new ServiceReportService();
    }

    public void addSampleData() {
        Random generator = new Random();
        int numberOfRows = 10;

        String[] firstNames = {"Jan", "Roman", "Mateusz", "Judasz"};
        String[] lastNames = {"Kowalski", "Nowak", "Kwiatkowski", "Rząsa"};

        String[] carModels = {"A6", "E220", "C4", "XP"};
        String[] carBrands = {"Audi", "Mercedes", "Jaguar", "Clio"};
        String[] carTypes = {"Sedan", "Combi", "Sedan", "Combi"};

        String[] dateInvoice = {"2018-07-03", "2014-03-21", "2017-03-21", "2019-03-21"};
        int[] priceInvoice = {850, 1200, 355, 457};
        int[] amountInvoice = {200,100,300,400};
        Date[] dateOfInvoice = {new Date(2009,3,12), new Date(2014,8,20), new Date(), new Date()};

        String[] loremIpsum = {
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                " Vivamus vitae nisi eget nisl sagittis mollis in id diam. ",
                "Sed iaculis fringilla turpis in tempor. In quis risus ante. Donec et sapien massa.",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. ",
                "Vivamus vitae nisi eget nisl sagittis mollis in id diam. Sed iaculis fringilla turpis in tempor.",
                " In quis risus ante. Donec et sapien massa."
        };
//        Car car = this.populateCar(carModels[0], carBrands[0], carTypes[0]);
        clearDatabase();

        for (int i = 0; i <=numberOfRows; i++) {
            int index = generator.nextInt(4);

            Car car = this.populateCar(carModels[index], carBrands[index], carTypes[index]);
            User user = this.populateUser(firstNames[index], lastNames[index], i);
            Client client = this.populateClient(firstNames[index], lastNames[index]);
            Service service = this.populateService(car, client);
            Invoice invoice = this.populateInvoice(dateOfInvoice[index],priceInvoice[index]);
            ServiceReport serviceReport = this.populateServiceReport(loremIpsum[0]);
        }
    }
    public void clearDatabase(){
        //kolejność usuwania jest ważna!
        this.serviceService.deleteAll();
        this.carService.deleteAll();
        this.userService.deleteAll();
        this.clientService.deleteAll();
        this.invoiceService.deleteAll();
        this.serviceReportService.deleteAll();
    }

    public Service populateService(Car car, Client client) {
        Service service = new Service(client, car, "In service");
        service.setDescription("Opis");
        this.serviceService.persist(service);

        return service;
    }

    public InvoicePosition populateInvoicePosition(String name, double price) {
        InvoicePosition invoicePosition = new InvoicePosition(name, price);
        this.invoicePositionService.persist(invoicePosition);

        return invoicePosition;
    }

    public Invoice populateInvoice(Date date_of_issue, double total_price){
        Invoice invoice = new Invoice(date_of_issue, total_price);
        this.invoiceService.persist(invoice);

        return invoice;
    }

    public Client populateClient(String firstName, String lastName) {
        Client client = new Client(firstName,  lastName, "323232", "Rzeszów Pigonia 1");
        this.clientService.persist(client);

        return client;
    }

    public User populateUser(String firstName, String lastName, int index) {
        User user = new User("email" + index + "@o2.pl", firstName, lastName, "login" + index, hashPassword("password"), "A");
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

    public ServiceReport populateServiceReport(String description){
        ServiceReport serviceReport = new ServiceReport(description);
        this.serviceReportService.persist(serviceReport);

        return  serviceReport;
    }
}
