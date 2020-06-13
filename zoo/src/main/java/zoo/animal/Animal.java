package zoo.animal;

import zoo.dto.Food;
import zoo.dto.FoodType;

import java.util.List;

public interface Animal {
    void voice();
    boolean eat(Food food);
    boolean isHungry();
    List<FoodType> getPossibleFeedTypes();
}
