package bathroon;

import java.util.LinkedHashSet;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

import Person.Person;
import Person.Sex;

public class Bathroon {
	private static final int CAPACITY = 5;
	private static Bathroon instance = new Bathroon(CAPACITY);
	
	private Semaphore semaphore;
	private Semaphore entry;
	private Semaphore exit;
	
	private Sex currentsex;
	private final int capacity;
	private LinkedHashSet<Person> people;
	
	public Bathroon(int capacity) {
		this.capacity = capacity;
        this.currentsex = Sex.NONE;
        this.people = new LinkedHashSet<>();
        this.semaphore = new Semaphore(this.capacity, true);
        this.exit = new Semaphore(1, true);
        this.entry = new Semaphore(1, true);
	}	
	
	 public static Bathroon getInstance() {
		 return instance;
	 }
	 
	 public void addUser(Person person) {
        try {
            this.semaphore.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Bathroon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (this.isEmpty()) {
            this.currentsex = person.getSex();
        }

        if (!this.isFull() && !this.people.contains(person)
                && getCurrentSex().equals(person.getSex())) {
            try {
                this.entry.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(Bathroon.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
    
            if (this.people.add(person)) {
                System.out.println(person.getName() + " entered the bathroom");
            }
            this.entry.release();

            if (this.isFull()) {
                System.out.println("The bathroon is full");
            }
        }
    }
	 
	public void removeUser(Person person) {
		this.semaphore.release();

        if (!this.isEmpty()) {
            try {
                this.entry.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(Bathroon.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
            if (this.people.remove(person)) {
                System.out.println(person.getName() + " left the bathroon");
            }
            this.exit.release();

            if (this.isEmpty()) {
                System.out.println("The bathroon is empty");
                this.currentsex= Sex.NONE;
            }
        }
    }
	
	public boolean isInTheBathroom(Person person) {
        return this.people.contains(person);
    }

    public boolean isFull() {
        return this.capacity == this.people.size();
    }

    public boolean isEmpty() {
        return this.people.isEmpty();
    }

    public Sex getCurrentSex() {
        return this.currentsex;
    }

    @Override
    public String toString() {
        return "Bathroon {" + "currentSex = " + this.currentsex
                + ", capacity = " + this.capacity
                + ", numberOfUsers = " + this.people.size() + '}';
    }
	 
}
