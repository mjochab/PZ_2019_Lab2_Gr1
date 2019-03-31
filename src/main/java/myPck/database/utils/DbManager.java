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

    public void addSampleDataTest(){
        Car car = new Car("testModel", "testBrand", "testType", new Date());
        User user = new User("email@o2.pl", "Jan", "Kowalski", "login", "password", "A");
        Client client = new Client("Jan",  "Kowalski", 323232, "Rzeszów Pigonia 1");
        Service service = new Service(client, car, "In service");

        this.clientService.persist(client);
        this.userService.persist(user);
        this.carService.persist(car);
        this.serviceService.persist(service);
    }
}
