package myPck.database.utils;

import myPck.database.models.Car;
import myPck.database.models.Client;
import myPck.database.models.Service;
import myPck.database.models.User;
import myPck.services.CarService;
import myPck.services.ClientService;
import myPck.services.ServiceService;
import myPck.services.UserService;

import java.util.Date;
import java.util.Random;

public class DbManager {

    private UserService userService;
    private CarService carService;
    private ServiceService serviceService;
    private ClientService clientService;

    public DbManager () {
        this.userService = new UserService();
        this.carService = new CarService();
        this.serviceService = new ServiceService();
        this.clientService = new ClientService();
    }

    public void addSampleData() {
        Random generator = new Random();
        int numberOfRows = 10;

        String[] firstNames = {"Jan", "Roman"};
        String[] lastNames = {"Kowalski", "Nowak"};
        String[] carModels = {"A6", "E220"};
        String[] carBrands = {"Audi", "Mercedes"};
        String[] carTypes = {"Sedan", "Combi"};

        for (int i = 0; i <=numberOfRows; i++) {
            int index = generator.nextInt(2);
            Car car = this.populateCar(carModels[index], carBrands[index], carTypes[index]);
            User user = this.populateUser(firstNames[index], lastNames[index]);
            Client client = this.populateClient(firstNames[index], lastNames[index]);
            Service service = this.populateService(car, client);
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
}
