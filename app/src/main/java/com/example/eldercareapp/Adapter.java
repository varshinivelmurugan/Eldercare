package com.example.eldercareapp;
        import android.content.Context;
        import android.content.Intent;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    List<Diary> notes;

    Adapter(Context context,List<Diary> notes){
        this.inflater = LayoutInflater.from(context);
        this.notes = notes;
    }
    /*------------code referred from internet---------------*/
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_view,viewGroup,false);
        return new ViewHolder(view);
    }
    /*------------code referred from internet---------------*/
    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder viewHolder, int position) {
        String title = notes.get(position).getTitle();
        String date = notes.get(position).getDate();
        String time = notes.get(position).getTime();
        long id = notes.get(position).getID();

        viewHolder.noteTitle.setText(title);
        viewHolder.noteDate.setText(date);
        viewHolder.noteTime.setText(time);
        viewHolder.listId.setText(String.valueOf(notes.get(position).getID()));
    }
    /*------------code referred from internet---------------*/
    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView noteTitle,noteDate,noteTime,listId;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            noteTitle = itemView.findViewById(R.id.noteTitle);
            noteDate= itemView.findViewById(R.id.noteDate);
            noteTime = itemView.findViewById(R.id.noteTime);
            listId = itemView.findViewById(R.id.listId);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(),Details.class);
                    i.putExtra("ID",notes.get(getAdapterPosition()).getID());
                    view.getContext().startActivity(i);
                }
            });
        }
    }
}

