package de.fortaestro.lesson5app.cityheraldry;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import de.fortaestro.lesson5app.R;

public class CoatOfArmsFragment extends Fragment
{
    public static final String PARCEL = "parcel" ;

    // Фабричный метод создания фрагмента
    // Фрагменты рекомендуется создавать через фабричные методы.
    public static CoatOfArmsFragment create(Parcel parcel)
    {
        CoatOfArmsFragment f = new CoatOfArmsFragment(); // создание
        // Передача параметра
        Bundle args = new Bundle();
        args.putSerializable(PARCEL , parcel);
        f.setArguments(args);

        return f;
    }

    // Получить посылку из параметра
    public Parcel getParcel()
    {
        return (Parcel) getArguments().getSerializable(PARCEL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View layout = inflater.inflate(R.layout.fragment_coat_of_arms, container, false);
        ImageView coatOfArms = layout.findViewById(R.id.imageView);
        TextView cityNameView = layout.findViewById(R.id.textView);

        // Получить из ресурсов массив указателей на изображения гербов
        TypedArray imgs = getResources().obtainTypedArray(R.array.coatofarms_imgs);
        Parcel parcel = getParcel();

        // Выбрать по индексу подходящий
        coatOfArms.setImageResource(imgs.getResourceId(parcel.getImageIndex(), - 1));
        cityNameView.setText(parcel.getCityName());

        return layout;
    }
}