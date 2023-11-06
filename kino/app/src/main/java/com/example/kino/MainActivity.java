package com.example.kino;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String DATABASE_NAME = "db.db";
    private static final int DB_VERSION = 3;

    // таблицы
    private static final String TABLE_NOTE = "NOTE";

    // столбцы таблицы Note
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_THEME = "theme";
    private static final String COLUMN_NOTE = "note";

    private DataBaseAccessor mDBHelper;
    private SQLiteDatabase mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Создаем объект DataBaseHelper
        mDBHelper = new DataBaseAccessor(this);

        // Получаем доступ к базе данных
        mDB = mDBHelper.getWritableDatabase();

        // Заполняем список данными из базы данных
        ListView listView = findViewById(R.id.listView);
        List<ContactsContract.CommonDataKinds.Note> noteList = getNoteList();
        NoteAdapter adapter = new NoteAdapter(this, noteList);
        listView.setAdapter(adapter);
    }

    private void updateNote(int id, String theme, String note) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_THEME, theme);
        values.put(COLUMN_NOTE, note);
        mDB.update(TABLE_NOTE, values, COLUMN_ID + "=" + id, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Закрываем подключение к базе данных
        mDB.close();
    }
}
