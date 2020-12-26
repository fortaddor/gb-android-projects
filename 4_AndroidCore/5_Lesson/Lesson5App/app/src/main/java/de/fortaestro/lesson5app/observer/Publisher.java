package de.fortaestro.lesson5app.observer;

import java.util.ArrayList;
import java.util.List;

public class Publisher
{
    private List<Observer> observers; // Все обозреватели

    public Publisher ()
    {
        this.observers = new ArrayList<>();
    }

    // Подписать
    public void subscribe (Observer observer)
    {
        this.observers.add(observer);
    }

    // Отписать
    public void unsubscribe (Observer observer)
    {
        this.observers.remove(observer);
    }

    // Разослать событие
    public void notify (String text)
    {
        for (Observer observer : this.observers)
        {
            observer.updateText(text);
        }
    }
}
