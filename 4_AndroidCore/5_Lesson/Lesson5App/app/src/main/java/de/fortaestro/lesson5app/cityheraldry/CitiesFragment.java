package de.fortaestro.lesson5app.cityheraldry;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.fortaestro.lesson5app.R;

import static de.fortaestro.lesson5app.cityheraldry.CoatOfArmsFragment.*;

// Фрагмент выбора города из списка
public class CitiesFragment extends Fragment
{
    private boolean isExistCoatOfArms;      // Можно ли расположить рядом фрагмент с гербом
    private Parcel currentParcel ;

    // При создании фрагмента укажем его макет
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_cities, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    // activity создана, можно к ней обращаться. Выполним начальные действия
    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        // Определение, можно ли будет расположить рядом герб в другом фрагменте
        this.isExistCoatOfArms = this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        // Если это не первое создание, то восстановим текущую позицию
        if (savedInstanceState != null)
        {
            this.currentParcel = (Parcel) savedInstanceState.getSerializable("CurrentCity");
        }
        else
        {
            //+ Если восcтановить не удалось, то сделаем объект с первым индексом
            this.currentParcel = new Parcel(0 , getResources().getStringArray(R.array.cities)[0]);
        }

        // Если можно нарисовать рядом герб, то сделаем это
        if (this.isExistCoatOfArms)
        {
            this.showCoatOfArms(this.currentParcel);
        }
    }

    // Сохраним текущую позицию (вызывается перед выходом из фрагмента)
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState)
    {
        outState.putSerializable("CurrentCity", this.currentParcel);

        super.onSaveInstanceState(outState);
    }

    // создаем список городов на экране из массива в ресурсах
    private void initList(View view)
    {
        LinearLayout layoutView = (LinearLayout) view;
        String[] cities = getResources().getStringArray(R.array.cities);

        // В этом цикле создаем элемент TextView,
        // заполняем его значениями,
        // и добавляем на экран.
        // Кроме того, создаем обработку касания на элемент
        for (int i = 0; i < cities.length; i++)
        {
            String city = cities[i];
            TextView tv = new TextView(getContext());
            tv.setText(city);
            tv.setTextSize(30);
            layoutView.addView(tv);
            final int fi = i;

            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    currentParcel = new Parcel(fi , getResources().getStringArray(R.array.cities)[fi]);
                    showCoatOfArms(currentParcel);
                }
            });
        }
    }

    // Показать герб. Ecли возможно, то показать рядом со списком,
    // если нет, то открыть вторую activity
    private void showCoatOfArms(Parcel parcel)
    {
        if (isExistCoatOfArms)
        {
            // Проверим, что фрагмент с гербом существует в activity
            CoatOfArmsFragment detail = (CoatOfArmsFragment) getFragmentManager().findFragmentById(R.id.coat_of_arms);

            // Если есть необходимость, то выведем герб
            if (detail == null || detail.getParcel().getImageIndex() != parcel.getImageIndex())
            {
                // Создаем новый фрагмент с текущей позицией для вывода герба
                detail = CoatOfArmsFragment.create(parcel);
                // Выполняем транзакцию по замене фрагмента
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.coat_of_arms, detail); // замена фрагмента
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        }
        else
        {
            // Если нельзя вывести герб рядом, откроем вторую activity
            Intent intent = new Intent();
            intent.setClass(getActivity(), CoatOfArmsActivity.class);
            // и передадим туда параметры
            intent.putExtra(PARCEL, parcel);
            startActivity(intent);
        }
    }
}