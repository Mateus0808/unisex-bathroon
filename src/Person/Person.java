package Person;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import bathroon.Bathroon;

public class Person implements Runnable {
	private final String name;
    private final Sex sex;
    private final Bathroon bathroon;
    private boolean goOut;
    private boolean needBathroom;
    
    public Person(String name, Sex sex, Bathroon bathroon) {
        this.name = name;
        this.sex = sex;
        this.bathroon = bathroon;
        this.goOut = false;
        this.needBathroom = true;
    }
    
    public void useBathroom() {
        this.bathroon.addUser(this);
        if (this.bathroon.isInTheBathroom(this)) {
            try {
                TimeUnit.SECONDS.sleep((new Random()).nextInt(1) + 1);
                this.goOut = true;
            } catch (InterruptedException ex) {
                Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void leaveBathroom() {
        this.bathroon.removeUser(this);
        this.goOut = false;
        this.needBathroom = false;
    }

    public String getName() {
        return this.name;
    }
    
    public Sex getSex() {
        return this.sex;
    }
    
    @Override
    public void run() {
        System.out.println(this.getName());
        while (this.needBathroom) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            }
            if ((this.bathroon.getCurrentSex().equals(this.getSex())
                    || this.bathroon.getCurrentSex().equals(Sex.NONE))
                    && !this.bathroon.isFull()
                    && !this.bathroon.isInTheBathroom(this)) {
                this.useBathroom();
            }
            if (this.goOut) {
                this.leaveBathroom();
            }
        }
    }

    @Override
    public String toString() {
        return "Pessoa{" + "nome = " + this.name + ", sexo = " + this.sex + '}';
    }
}
