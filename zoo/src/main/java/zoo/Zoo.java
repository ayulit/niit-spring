package zoo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import zoo.animal.Animal;

import java.util.List;

@Component
public class Zoo {

    @Value("${zoo.name}")
    private  String name;

    private final List<Animal> animals;

    @Autowired
    public Zoo(List<Animal> animals) {
        this.animals = animals;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public String getName() {
        return name;
    }
}
