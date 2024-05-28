package com.example.todolist;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1)                                                     // Не забывать повышать версию, при изменении полей или добавлении таблиц
public abstract class NoteDataBase extends RoomDatabase {

    private static NoteDataBase instance = null;
    private static final String DB_NAME = "notes.db";

    public static NoteDataBase getInstance(Application application) {                               // метод возвращает экземпляр Бызы данных
        if (instance == null) {
            instance = Room.databaseBuilder(                                                        // получение экземпляра(наследника) класса NoteDataBase
                    application,
                    NoteDataBase.class,
                    DB_NAME
            )
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract NotesDao notesDao ();
}
