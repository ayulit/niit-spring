import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;
import zoo.dto.Food;
import zoo.dto.FoodType;
import zoo.event.HungryEvent;
import zoo.service.ZooService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Iterator;

@Configuration
@ComponentScan(basePackages = "zoo")
@PropertySource("application.yml")
@EnableAspectJAutoProxy
public class App {

    private static ApplicationContext context;
    private static Iterator itr = Arrays.asList(FoodType.values()).iterator();

    public static void main(String[] args) {
        context = new AnnotationConfigApplicationContext(App.class);
        feedAnimals();
    }

    public static void feedAnimals() {
        ZooService service = context.getBean(ZooService.class);
        Food food = new Food();
        food.setFoodName((FoodType) itr.next());
        food.setExpirationDate(LocalDateTime.now().plusHours(6));
        service.feedAnimals(food);
    }

    @EventListener(HungryEvent.class)
    public void feedAgain(HungryEvent hungryEvent) {
        ZooService service = context.getBean(ZooService.class);
        Food food = new Food();
        if (itr.hasNext()) {
            food.setFoodName((FoodType) itr.next());
            food.setExpirationDate(LocalDateTime.now().plusHours(6));
            service.feedAnimals(food);
        }
    }
}
