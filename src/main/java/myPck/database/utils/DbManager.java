package myPck.database.utils;

import myPck.database.models.Car;
import myPck.database.models.User;
import myPck.services.UserService;

import java.util.Date;

public class DbManager {

    private UserService userService;

    public DbManager () {
        this.userService = new UserService();
    }

    public void addSampleDataTest(){
        Car car = new Car("testModel", "testBrand", "testType", new Date());
        User user = new User("email@o2.pl", "Jan", "Kowalski", "login", "password", "A");

        this.userService.persist(user);
    }
}
