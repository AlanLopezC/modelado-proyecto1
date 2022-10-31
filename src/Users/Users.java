package Users;

import java.util.ArrayList;

public class Users {
    private ArrayList<User> users = new ArrayList<User>();

    public Users() {
        users.add(new User("Alan Lopez", "alanlopez",
                "alanlopez123",
                "Jose Garcia 33, 55551",
                22,
                "212 555-1234",
                "123456789",
                "MX"));
        users.add(new User(
                "Jona",
                "jonathanm",
                "jonasiu",
                "Jose carmelo 35, 54532",
                17,
                "899 555-1234",
                "012343212",
                "US"));
        users.add(new User(
                "Martha Patricia",
                "marmar",
                "maremare",
                "Dominguez 5, 00001",
                78,
                "000 555-1234",
                "1212121212",
                "ES"));
    }

}
