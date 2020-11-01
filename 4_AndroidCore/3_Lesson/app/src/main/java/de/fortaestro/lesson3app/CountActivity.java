package de.fortaestro.lesson3app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CountActivity extends AppCompatActivity
{
//    private int counter;
    private TextView textCounter;
    private EditText textEditCounter;

    private CountPresenter countPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);

        String instanceState;

        // первый запуск?
        if (savedInstanceState == null )
        {
            instanceState = "Первый запуск!" ;
        }
        else
        {
            instanceState = "Повторный запуск!" ;
        }

        // выведем, какой это запуск
        Toast. makeText (getApplicationContext(), instanceState + " - onCreate()", Toast.LENGTH_SHORT).show();

        countPresenter = CountPresenter.getInstance();

        textCounter = findViewById(R.id.textCounter); // Поле счетчика
        textCounter.setText(((Integer)countPresenter.getCounter()).toString()); // Выводим счетчик на экран
        textEditCounter = findViewById(R.id.textEditCounter); // Поле счетчика
        textEditCounter.setText(((Integer)countPresenter.getCounter()).toString());

        Button button = findViewById(R.id.button); // Кнопка
        button.setOnClickListener( new View.OnClickListener() { // Обработка нажатий
            @Override
            public void onClick(View v)
            {
                // Увеличим счетчик на 1 и выведем на экран
                countPresenter.incrementCounter();

                textCounter.setText(((Integer)countPresenter.getCounter()).toString());
                textEditCounter.setText(((Integer)countPresenter.getCounter()).toString());
            }
        });
    }

//    @Override
//    protected void onSaveInstanceState (Bundle saveInstanceState)
//    {
//        super.onSaveInstanceState(saveInstanceState);
//
//        Toast.makeText(getApplicationContext(), "onSaveInstanceState()", Toast.LENGTH_SHORT).show();
//        saveInstanceState.putInt( "Counter" , counter); // Сохраняем счетчик
//    }
//
//    @Override
//    protected void onRestoreInstanceState (Bundle saveInstanceState){
//        super .onRestoreInstanceState(saveInstanceState);
//        Toast.makeText(getApplicationContext(), "Повторный запуск!! - onRestoreInstanceState()" , Toast.LENGTH_SHORT).show();
//        counter = saveInstanceState.getInt( "Counter" ); // Восстанавливаем счетчик
//        textCounter.setText(((Integer)counter).toString()); // Выводим счетчик в поле
//    }
}