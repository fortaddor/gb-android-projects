package lesson5;

import java.util.ArrayList;
import java.util.Arrays;

public class Race
{
    private ArrayList<Stage> stages;
    private Car winner;

    public Race(Stage... stages)
    {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public ArrayList<Stage> getStages()
    {
        return stages;
    }

    public void setWinner(Car car)
    {
        if (this.winner != null)
        {
            return;
        }

        this.winner = car;
        System.out.println(this.winner.getName() + " - WIN!!!");
    }
}
