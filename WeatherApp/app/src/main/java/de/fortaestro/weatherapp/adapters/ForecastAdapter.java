package de.fortaestro.weatherapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.fortaestro.weatherapp.R;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder>
{
    private String[] dataSource;

    public ForecastAdapter(String[] dataSource)
    {
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public ForecastAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastAdapter.ViewHolder viewHolder, int i)
    {
        viewHolder.getTextView().setText(this.dataSource[i]);
    }

    @Override
    public int getItemCount()
    {
        return this.dataSource.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView textView;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            this.textView = (TextView) itemView;
        }

        public TextView getTextView()
        {
            return this.textView;
        }
    }
}