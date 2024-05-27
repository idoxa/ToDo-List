package com.example.todolist;

import java.util.ArrayList;
import java.util.Random;

public class DataBase {
    private ArrayList<Note> notes = new ArrayList<>();

    private static DataBase instance = null;                                                        // Патерн для работы с одним и тем же набором элементов во всех уголках программы
                                                                                                    //
    public static DataBase getInstance(){                                                           //
        if (instance == null) {                                                                     // проверяем наличи записей
            instance = new DataBase();                                                              // создаем новыую базу записей
        }
        return instance;                                                                            // возращаем имеющиеся базу записей
    }

    public DataBase () {
        Random random = new Random();                                                               // Переменная со случайными числами
        for (int i = 0; i < 20; i++) {
            Note note = new Note(i, "Note " + i, random.nextInt(3));                     // Заполняем заметки со случайным значением приоритета
            notes.add(note);                                                                        // Добавляем заметки
        }
    }

    public void add(Note note) {
        notes.add(note);
    }

    public void remove (int id) {
        for (int i = 0; i < notes.size(); i++) {
            Note note = notes.get(i);
            if (note.getId() == id) {
                notes.remove(note);
            }
        }
    }

    public ArrayList<Note> getNotes() {
        return new ArrayList<>(notes);
    }

}
