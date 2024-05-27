package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotes;
    private FloatingActionButton buttonAddNote;

    private DataBase dataBase = DataBase.getInstance();                                             // колекция объектов

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();

        // Выводим заметки
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AddNoteActivity.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        showNotes();
    }

    private void initViews() {
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
        buttonAddNote = findViewById(R.id.buttonAddNote);
    }

    private void showNotes() {                                                                        // Метод который отображает все заметки
        recyclerViewNotes.removeAllViews();
        for (Note note : dataBase.getNotes()) {
            View view = getLayoutInflater().inflate(R.layout.note_item, recyclerViewNotes, false);   // Создаем View из макета note_item при помощи LayoutInflater()
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dataBase.remove(note.getId());
                    showNotes();
                }
            });
            TextView textViewNote = view.findViewById(R.id.textViewNote);                           // Поиск элементов внутри View
            textViewNote.setText(note.getText());                                                   // Устанавливаем текст View

            int colorResId;                                                                         // Устанавливаем цвет View, по id цвета
            switch (note.getPriority()) {
                case 0:
                    colorResId = android.R.color.holo_green_light;                                  // Установка цвета из андроид
                    break;
                case 1:
                    colorResId = android.R.color.holo_orange_light;                                 // Установка цвета из андроид
                    break;
                default:
                    colorResId = android.R.color.holo_red_light;                                    // Установка цвета из андроид по дефолту
                    break;

            }
            int color = ContextCompat.getColor(this, colorResId);                            // Присваивание цвета
            textViewNote.setBackgroundColor(color);                                                 // Установка цвета
            recyclerViewNotes.addView(view);                                                        // Добавление в linearLayoutNotes
        }
    }
}