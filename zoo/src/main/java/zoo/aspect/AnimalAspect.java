package zoo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import zoo.animal.Animal;
import zoo.dto.Food;
import zoo.dto.FoodType;

import java.time.LocalDateTime;

@Aspect
@Component
public class AnimalAspect {
    @Pointcut("execution(* zoo.animal.Animal.eat(..))")
    public void eatPoint() {
    }

    @Around(value = "eatPoint() && args(food)")
    public Object validateFood(ProceedingJoinPoint proceedingJoinPoint, Food food) throws Throwable {
        FoodType foodName = food.getFoodName();
        Animal animal = (Animal) proceedingJoinPoint.getTarget();

        if (animal.getPossibleFeedTypes().stream().noneMatch(foodType -> foodType.equals(foodName))) {
            System.out.println(animal.getClass().getSimpleName() + " doesn't eat " + foodName);
            return false;
        } else if (LocalDateTime.now().isAfter(food.getExpirationDate())) {
            System.out.println(food.getFoodName() + " is expired.");
            return false;
        } else {
            return eatAround(proceedingJoinPoint);
        }
    }

    private Object eatAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String target = proceedingJoinPoint.getTarget().getClass().getSimpleName();
        System.out.println(target + " starts eating.");
        try {
            Object result = proceedingJoinPoint.proceed();
            System.out.println(target + " ends eating.");
            return result;
        } catch (Throwable e) {
            System.out.println(target + " eating failed: " + e.getMessage());
            throw e;
        }
    }
}
