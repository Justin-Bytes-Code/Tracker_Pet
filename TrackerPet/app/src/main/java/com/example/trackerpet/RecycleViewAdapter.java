package com.example.trackerpet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    private Context context;
    private List<Animal> animalList;


    public interface  OnItemClickListener {
        void onItemClick(Animal animal);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public RecycleViewAdapter(Context context, List<Animal> animalList) {
        this.context = context;
        this.animalList = animalList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.one_line_animal, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Animal animal = animalList.get(position);

        holder.Name_TV.setText(animal.getNameOfAnimal());
        holder.SpeciesName.setText(animal.getAnimalSpecies());
        holder.DOBTV.setText(String.valueOf(animal.getDateOfBirth()));
        holder.CurrentWeightTV.setText(String.valueOf(animal.getCurrentWeight()));
        holder.GoalWeightTV.setText(String.valueOf(animal.getGoalWeight()));

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(animal);
            }
        });
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Name_TV, SpeciesName, DOBTV, CurrentWeightTV, GoalWeightTV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Name_TV = itemView.findViewById(R.id.Name_TV);
            SpeciesName = itemView.findViewById(R.id.SpeciesName);
            DOBTV = itemView.findViewById(R.id.DOBTV);
            CurrentWeightTV = itemView.findViewById(R.id.CurrentWeightTV);
            GoalWeightTV = itemView.findViewById(R.id.GoalWeightTV);
        }
    }
}
