package lesson7;

/**
 * Entity Plate.
 *
 * <author>Valerij Krauter</author>
 * <date>20.07.2020</date>
 */
public class Plate
{
    private int foodAmount;

    public Plate(int foodAmount)
    {
        this.foodAmount = foodAmount;
    }

    public void add(int food)
    {
        this.setFoodAmount(this.getFoodAmount() + food);
    }

    public void reduce(int food)
    {
        if (this.getFoodAmount() < food)
        {
            return;
        }

        this.setFoodAmount(this.getFoodAmount() - food);
    }

    public void printInfo()
    {
        System.out.println(this);
    }

    public String toString()
    {
        return "Food on plate: " + this.getFoodAmount();
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(int foodAmount) {
        this.foodAmount = foodAmount;
    }
}
