package com.example.kino;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;

//Класс для доступа к БД
public class DataBaseAccessor extends SQLiteOpenHelper
{
    // Основные данные базы
    private static final String DATABASE_NAME = "db.db";
    private static final int DB_VERSION = 3;

    // таблицы
    private static final String TABLE_NOTE = "NOTE";


    // столбцы таблицы Note
    private static final String COLUMN_ID = "_id";//Обязательно с подчеркиванием
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_INFO = "info";
    private static final String COLUMN_COMM = "comm";


    public DataBaseAccessor(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Создать таблицу
        db.execSQL("CREATE TABLE " + TABLE_NOTE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_INFO + " TEXT,"
                + COLUMN_COMM + " TEXT);");

        // Добавить пару записей в таблицу
        db.execSQL("INSERT INTO " + TABLE_NOTE + "(" + COLUMN_NAME + ", "+COLUMN_INFO +", "+ COLUMN_COMM+") values('молодежка','400серий','сериал о спорте и жизни')");

    }

    /**
     * получить адаптер для listview.
     * @param layout  - разметка отдельного элемента listview
     * @param viewIds - идентификаторы элементов разметки ListView
     */
    public SimpleCursorAdapter getCursorAdapter(Context context,int layout, int[] viewIds)
    {
        // запрос данных для отображения
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_NOTE,null);

        // какие столбцы и в каком порядке показывать в listview
        String[] columns = new  String[] {COLUMN_NAME,COLUMN_INFO,COLUMN_COMM};

        // создание адаптера
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(context,layout,cursor,columns,viewIds,0);
        return  adapter;
    }


    public void updateNote(int id, String name,String info,String comm)
    {
        // выполнить запрос на обновление БД
        getReadableDatabase().execSQL("UPDATE "+ TABLE_NOTE
                + " SET "
                + COLUMN_NAME + "='" + name + "', "
                + COLUMN_INFO + "='" + info + "', "
                + COLUMN_COMM + "='" + comm + "'"
                + " WHERE "
                + COLUMN_ID + "=" + id);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try
        {
            //удалить старую таблицу
            db.execSQL("DROP TABLE " + TABLE_NOTE);
        }
        catch (Exception exception)
        {

        }
        finally {
            //создать новую и заполнить ее
            onCreate(db);
        }
    }
}