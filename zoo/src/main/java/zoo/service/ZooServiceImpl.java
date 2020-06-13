package zoo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import zoo.Zoo;
import zoo.animal.Animal;
import zoo.dto.Food;
import zoo.event.HungryEvent;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZooServiceImpl implements ZooService {
    private final Zoo zoo;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public ZooServiceImpl(Zoo zoo) {
        this.zoo = zoo;
    }

    @Override
    public void feedAnimals(Food food) {
        List<Animal> hungryAnimals = zoo.getAnimals()
                .stream()
                .filter(Animal::isHungry)
                .peek(animal -> animal.eat(food))
                .filter(Animal::isHungry)
                .collect(Collectors.toList());

        System.out.println(hungryAnimals);
        if(!hungryAnimals.isEmpty()) {
            applicationEventPublisher.publishEvent(new HungryEvent(this, "I'm hungry"));
        }

    }
}
