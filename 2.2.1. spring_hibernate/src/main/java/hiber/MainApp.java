package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("VAZ", 2106)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("GAZ", 3109)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("UAZ", 469)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("ZAZ", 968)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("model = " + user.getUserCar().getModel());
         System.out.println("series = " + user.getUserCar().getSeries());
         System.out.println();
      }

      try {
         User user = userService.getUserCar("ZAZ", 968);
         System.out.println(user.toString());
         System.out.println();

//         User user1 = user.getUserCar("VAZ", X5);
      }
      catch (NoResultException e) {
         System.out.println("Пользователя с такой моделью и серией автомобиля нет в базе данных");
      }

      context.close();
   }
}
