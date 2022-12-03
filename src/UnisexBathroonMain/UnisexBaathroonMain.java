package UnisexBathroonMain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Person.Person;
import Person.Sex;
import bathroon.Bathroon;

public class UnisexBaathroonMain {

	public static void main(String[] args) {
		Bathroon bathroon= Bathroon.getInstance();
        List<Person> people = new ArrayList<>();
        
        for (int i = 0; i < ((new Random()).nextInt(10) + 15); i++) {
            if (new Random().nextInt(2) == 0) {
                people.add(new Person(("João MALE - " + i), Sex.MALE, bathroon));
            } else {
            	people.add(new Person(("Maria FEMALE - " + i), Sex.FEMALE, bathroon));
            }
        }
        
        people.stream().map((Person) -> new Thread(Person)).forEach((t) -> {
            t.start();
        });
        
        people.stream().map((Person) -> new Thread(Person)).forEach((t) -> {
            try {
                t.join();
            } catch (InterruptedException ex) {
                
            }
        });

	}

}
