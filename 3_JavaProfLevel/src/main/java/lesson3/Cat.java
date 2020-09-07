package lesson3;

import java.io.Serializable;

public class Cat implements Serializable {
    private static final long serialVersionUID = 5548958564035958741L;

    private String name;
    private int age;
    private int liveCount;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
