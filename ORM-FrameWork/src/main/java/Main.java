import entities.Address;
import entities.User;
import orm.EntityManager;
import orm.MyConnector;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {

        Connection connection = MyConnector.getConnection("root", "7521", "custom-orm");
        EntityManager<User> userEntityManager = new EntityManager<>(connection);
        EntityManager<Address> addressEntityManager = new EntityManager<>(connection);

        User user = new User("Dani", 27, LocalDate.now());
        User user2 = new User("Viki", 24, LocalDate.now());
        Address address = new Address("Samokov", "Bulgaria", "2000", "Makedonia", 10);
//        user.setId(1);
//        user.setAge(22);
//          userEntityManager.doCreate(User.class);
//        userEntityManager.persist(user);
//        userEntityManager.persist(user2);
//        Iterable<User> users = userEntityManager.find(User.class, "id < 5");
//        System.out.println(users);
//
//        User toDelete = userEntityManager.findFirst(User.class, "id = 4");
//        userEntityManager.delete(toDelete);
//
//        Iterable<User> allUsers = userEntityManager.find(User.class, "id < 5");
//        System.out.println(allUsers);

        addressEntityManager.doCreate(Address.class);
    }
}
