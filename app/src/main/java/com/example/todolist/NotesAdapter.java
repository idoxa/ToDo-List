package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private ArrayList<Note> notes = new ArrayList<>();

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotesViewHolder viewHolder, int position) {
        Note note = notes.get(position);
        viewHolder.textViewNote.setText(note.getText());                                             // Устанавливаем текст View

        int colorResId;                                                                              // Устанавливаем цвет View, по id цвета
        switch (note.getPriority()) {
            case 0:
                colorResId = android.R.color.holo_green_light;                                       // Установка цвета из андроид
                break;
            case 1:
                colorResId = android.R.color.holo_orange_light;                                      // Установка цвета из андроид
                break;
            default:
                colorResId = android.R.color.holo_red_light;                                         // Установка цвета из андроид по дефолту
                break;

        }
        int color = ContextCompat.getColor(viewHolder.itemView.getContext(), colorResId);            // Присваивание цвета
        viewHolder.textViewNote.setBackgroundColor(color);

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNote;
        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNote = itemView.findViewById(R.id.textViewNote);
        }
    }

}