package lesson1.fruitbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Box <T extends Fruit>
{
    private List<T> fruits = new ArrayList<>();
    private String name;

    public Box(String name)
    {
        this.name = name;
    }

    public Box(String name, T[] fruits)
    {
        this(name);

        this.fruits.addAll(Arrays.asList(fruits));
    }

    public Box(String name, Box<T> box)
    {
        this(name);

        this.add(box);
        box.clear();
    }

    public void add(T fruit)
    {
        this.fruits.add(fruit);
    }

    public void add(Box<T> otherBox)
    {
        this.fruits.addAll(otherBox.fruits);
    }

    public boolean compare(Box<? super T> otherBox)
    {
        return Math.abs(this.getWeight() - otherBox.getWeight()) < 0.001;
    }

    public void empty(Box<T> bigBox)
    {
        if (this.isMyself(bigBox))
        {
            return;
        }

        bigBox.add(this);
        this.clear();
    }

    public void clear()
    {
        this.fruits.clear();
    }

    public int size()
    {
        return this.fruits.size();
    }

    public float getWeight()
    {
        if (this.fruits.size() == 0)
        {
            return 0.0f;
        }

        return this.fruits.size() * this.fruits.get(0).getWeight();
    }

    public String getName()
    {
        return this.name;
    }

    private boolean isMyself(Box<?> box)
    {
        return box == this;
    }
}
