package com.example.kino;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final public static String KEY_NAME = "name";
    final public static String KEY_INFO = "info";
    final public static String KEY_COMM = "comm";
    final public static String KEY_IMAGE = "image";
    final public static String KEY_POSITION = "position";

    ListView ThemesListView;

    SimpleCursorAdapter noteAdapter;
    DataBaseAccessor db;

    // создание launcher для получения данных из дочерней активити
    ActivityResultLauncher<Intent> NotesLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    // все ли хорошо при получении данных из дочерней активити?
                    if(result.getResultCode() == Activity.RESULT_OK)
                    {
                        //получить данные
                        Intent returnedIntent = result.getData();
                        int id = returnedIntent.getIntExtra(KEY_POSITION,-1);
                        String name = returnedIntent.getStringExtra(KEY_NAME);
                        String info = returnedIntent.getStringExtra(KEY_INFO);
                        String comm = returnedIntent.getStringExtra(KEY_COMM);
                        String image = returnedIntent.getStringExtra(KEY_IMAGE);

                        //обновить БД и интерфейс
                        db.updateNote(id,name,info,comm,image);
                        noteAdapter = AdapterUpdate();
                    }
                    else
                    {
                        Log.d("MainActivity" ,"Invalid note activity result");
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // создать аксессор к бд
        db = new DataBaseAccessor(this);

        setContentView(R.layout.activity_main);
        ThemesListView = findViewById(R.id.ListView);

        noteAdapter = AdapterUpdate();
        Intent NoteIntent = new Intent(this, note.class);

        // обработка клика по listView
        ThemesListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                //Добыть данные из адаптера

                String name = ((Cursor) noteAdapter.getItem(position)).getString(1);
                String info = ((Cursor) noteAdapter.getItem(position)).getString(2);
                String comm = ((Cursor) noteAdapter.getItem(position)).getString(3);
                String image = ((Cursor) noteAdapter.getItem(position)).getString(4);
                //отправить данные в дочернюю акливити
                NoteIntent.putExtra(KEY_NAME, name);
                NoteIntent.putExtra(KEY_INFO, info);
                NoteIntent.putExtra(KEY_COMM, comm);
                NoteIntent.putExtra(KEY_IMAGE, image);


                //id - идентификатор записи в БД
                //без приведения к int перидется и получать long а я не хотел переписывать дочернюю активити
                NoteIntent.putExtra(KEY_POSITION,String.valueOf((int) id));
                Toast.makeText(MainActivity.this, String.valueOf((int) id), Toast.LENGTH_SHORT).show();

                //запустить дочернюю активити
                NotesLauncher.launch(NoteIntent);
            }
        });
    }

    /**
     * Обновляет listView путем установки нового адаптера
     * @return Адаптер для обновления listView
     */
    private SimpleCursorAdapter AdapterUpdate() {
        // получить адаптер из класса
        SimpleCursorAdapter adapter = db.getCursorAdapter(this,
                android.R.layout.two_line_list_item, // Разметка одного элемента ListView
                new int[]{android.R.id.text1,android.R.id.text2}); // текст этого элемента

        // установить адаптер в listview
        ThemesListView.setAdapter(adapter);
        return adapter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // закрыть БД
        db.close();
    }

    public void onAddButtonClick(View view){

    }
}