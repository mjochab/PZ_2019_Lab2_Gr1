package myPck.database.utils;

import myPck.database.models.Car;
import myPck.database.models.User;
import myPck.services.CarService;
import myPck.services.ServiceService;
import myPck.services.UserService;

import java.util.Date;

public class DbManager {

    private UserService userService;
    private CarService carService;
    private ServiceService serviceService;

    public DbManager () {
        this.userService = new UserService();
        this.carService = new CarService();
        this.serviceService = new ServiceService();
    }

    public void addSampleDataTest(){
        Car car = new Car("testModel", "testBrand", "testType", new Date());
        User user = new User("email@o2.pl", "Jan", "Kowalski", "login", "password", "A");

        this.userService.persist(user);
        this.carService.persist(car);
    }
}
