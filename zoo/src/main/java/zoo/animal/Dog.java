package zoo.animal;

import org.springframework.stereotype.Component;
import zoo.dto.Food;
import zoo.dto.FoodType;

import java.util.Arrays;
import java.util.List;

import static zoo.dto.FoodType.FISH;
import static zoo.dto.FoodType.MEAT;

@Component
public class Dog implements Animal {
    private boolean hungry = true;

    @Override
    public void voice() {
        System.out.println("gav");
    }

    @Override
    public boolean eat(Food food) {
        hungry = false;
        return isHungry();
    }

    @Override
    public boolean isHungry() {
        return hungry;
    }

    @Override
    public List<FoodType> getPossibleFeedTypes() {
        return Arrays.asList(FISH, MEAT);
    }
}
