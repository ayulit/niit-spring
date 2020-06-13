package zoo.animal;

import org.springframework.stereotype.Component;
import zoo.dto.Food;
import zoo.dto.FoodType;

import java.util.Collections;
import java.util.List;

import static zoo.dto.FoodType.GRASS;

@Component
public class Cow implements Animal {
    private boolean hungry = true;

    @Override
    public void voice() {
        System.out.println("moo");
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
        return Collections.singletonList(GRASS);
    }
}
