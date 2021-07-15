//internet code refered for writting this
package com.example.eldercareapp.yAdapter;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eldercareapp.Model.Exercise;
import com.example.eldercareapp.R;
import com.example.eldercareapp.Interface.ItemClickListener;
import com.example.eldercareapp.ViewExercise;

import java.util.List;

// RecycleView used for listview

class RecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView image;
    public TextView text, description;

    public ItemClickListener itemClickListener; //interface which has onclick method as abstract method

    public RecycleViewHolder(View itemView) {
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.ex_img);
        text = (TextView) itemView.findViewById(R.id.ex_name);
        itemView.setOnClickListener(this);
    }

    //setter method
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    //override the interface abstract method
    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v, getAdapterPosition());
    }


}


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecycleViewHolder> {

    private List<Exercise> exerciseList;      // for exercise class which has image of exercise and name list is created
    private Context context;

    public RecyclerViewAdapter(List<Exercise> exerciseList, Context context) {
        this.exerciseList = exerciseList;
        this.context = context;
    }

    @NonNull
    @Override
    //this method is used to inflate the item_exercise.xml

    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());
        View itemview = inflator.inflate(R.layout.item_exercise, parent, false);
        return new RecycleViewHolder(itemview);
    }

    @Override
    //this method is used to set data to each row

    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        holder.image.setImageResource(exerciseList.get(position).getImage_id());
        holder.text.setText(exerciseList.get(position).getName());
        holder.setItemClickListener(new ItemClickListener() {

            @Override
// onclicking the recycleview data following activity take place
            public void onClick(View View, int position) {

                Intent intent = new Intent(context, ViewExercise.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("video_id", exerciseList.get(position).getVideo_id());
                intent.putExtra("name", exerciseList.get(position).getName());
                intent.putExtra("description", exerciseList.get(position).getDescription());
                context.startActivity(intent);
            }
        });

    }

    //used to get the count of exerciselist
    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
}
