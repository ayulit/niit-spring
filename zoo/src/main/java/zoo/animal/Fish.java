package zoo.animal;

import org.springframework.stereotype.Component;
import zoo.dto.Food;
import zoo.dto.FoodType;

import java.util.Collections;
import java.util.List;

import static zoo.dto.FoodType.SEAWEED;

@Component
public class Fish implements Animal {
    private boolean hungry = true;

    @Override
    public void voice() {
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
        return Collections.singletonList(SEAWEED);
    }
}
