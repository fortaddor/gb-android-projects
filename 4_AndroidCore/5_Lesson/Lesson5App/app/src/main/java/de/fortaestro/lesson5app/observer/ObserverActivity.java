package de.fortaestro.lesson5app.observer;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import de.fortaestro.lesson5app.R;

public class ObserverActivity extends AppCompatActivity implements PublisherGetter
{
    private Publisher publisher = new Publisher();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observer);

        // Создаем фрагменты
        ObserverFragment1 fragment1 = new ObserverFragment1();
        ObserverFragment2 fragment2 = new ObserverFragment2();
        ObserverMainFragment mainFragment = new ObserverMainFragment();

        // Подписываем фрагменты
        this.publisher.subscribe(fragment1);
        this.publisher.subscribe(fragment2);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        // Добавить фрагменты
        fragmentTransaction.add(R.id.fragment_observer_main, mainFragment);
        fragmentTransaction.add(R.id.fragment_observer1, fragment1);
        fragmentTransaction.add(R.id.fragment_observer2, fragment2);

        // Закрыть транзакцию
        fragmentTransaction.commit();
    }

    // Снимем с activity обязанность по передаче событий классу Publisher
    // Главный фрагмент будет использовать его для передачи событий
    @Override
    public Publisher getPublisher()
    {
        return this.publisher ;
    }
}