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
    private static final String DATABASE_NAME = "58.db";
    private static final int DB_VERSION = 3;

    // таблицы
    private static final String TABLE_NOTE = "NOTE";


    // столбцы таблицы Note
    private static final String COLUMN_ID = "_id";//Обязательно с подчеркиванием
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_INFO = "info";
    private static final String COLUMN_COMM = "comm";
    private static final String COLUMN_IMAGE = "image";


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
                + COLUMN_COMM + " TEXT,"
                + COLUMN_IMAGE + " TEXT);");

        // Добавить пару записей в таблицу
        db.execSQL("INSERT INTO " + TABLE_NOTE + "(" + COLUMN_NAME + ", "+COLUMN_INFO +", "+COLUMN_COMM +", "+ COLUMN_IMAGE+") values('Молодежка','Дата выхода: 7 октября 2013 г.\n" +
                "Жанр: драма\n" +
                "Страна: Россия\n" +
                "Режиссёры: Сергей Арланов, Андрей Головков','Коментарий сериала:','molodezka')");
        db.execSQL("INSERT INTO " + TABLE_NOTE + "(" + COLUMN_NAME + ", "+COLUMN_INFO +", "+COLUMN_COMM +", "+ COLUMN_IMAGE+") values('Трудные подростки','Дата выхода: 24 октября 2019 г.\n" +
                "Жанр: драма, комедия\n" +
                "Страна: Россия\n" +
                "Режиссёры: Николай Рыбников, Александр Цой, Рустам Ильясов','Коментарий сериала:','trudnie')");
        db.execSQL("INSERT INTO " + TABLE_NOTE + "(" + COLUMN_NAME + ", "+COLUMN_INFO +", "+COLUMN_COMM +", "+ COLUMN_IMAGE+") values('Ивановы-Ивановы','Дата выхода: 16 октября 2017 г.\n" +
                "Жанр: комедия\n" +
                "Страна: Россия\n" +
                "Режиссёры: Антон Федотов, Фёдор Стуков, Андрей Элинсон, Сергей Знаменский, Алена Корчагина','Коментарий сериала:','ivan')");
        db.execSQL("INSERT INTO " + TABLE_NOTE + "(" + COLUMN_NAME + ", "+COLUMN_INFO +", "+COLUMN_COMM +", "+ COLUMN_IMAGE+") values('1+1','Жанр: драма, комедия, биография\n" +
                "Страна: Франция\n" +
                "Режиссёры: Оливье Накаш, Эрик Толедано\n" +
                "Музыка: Людовико Эйнауди','Коментарий сериала:','plus')");
        db.execSQL("INSERT INTO " + TABLE_NOTE + "(" + COLUMN_NAME + ", "+COLUMN_INFO +", "+COLUMN_COMM +", "+ COLUMN_IMAGE+") values('Кухня','Дата выхода: 22 октября 2012 г.\n" +
                "Жанр: комедия\n" +
                "Страна: Россия\n" +
                "Режиссёры: Антон Федотов, Жора Крыжовников, Дмитрий Дьяченко','Коментарий сериала:','kuhn')");
        db.execSQL("INSERT INTO " + TABLE_NOTE + "(" + COLUMN_NAME + ", "+COLUMN_INFO +", "+COLUMN_COMM +", "+ COLUMN_IMAGE+") values('Волк с Уолл-стрит','Жанр: драма, преступление, биография, комедия\n" +
                "Страна: США\n" +
                "Режиссёр: Мартин Скорсезе\n" +
                "Музыка: Говард Шор','Коментарий сериала:', 'volk')");
        db.execSQL("INSERT INTO " + TABLE_NOTE + "(" + COLUMN_NAME + ", "+COLUMN_INFO +", "+COLUMN_COMM +", "+ COLUMN_IMAGE+") values('Атака титанов','Жанр: аниме, мультфильм, фантастика, драма, боевик\n" +
                "Страна: Япония\n" +
                "Режиссёры: Тэцуро Араки, Хироюки Танака, Юдзуру Татикава, Дайсукэ Токудо, Синпэй Эдзаки, Киёси Фукумото, Ёсихидэ Ибата, Сатанобу Кикути […]Тэцуро Араки, Хироюки Танака, Юдзуру Татикава, Дайсукэ Токудо, Синпэй Эдзаки, Киёси Фукумото, Ёсихидэ Ибата, Сатанобу Кикути, Ясуси Муроя, Ёсиюки Фудзивара\n" +
                "Музыка: Хироюки Савано, Ёсики, Ямамото Кота','Коментарий сериала:', 'ataka')");
        db.execSQL("INSERT INTO " + TABLE_NOTE + "(" + COLUMN_NAME + ", "+COLUMN_INFO +", "+COLUMN_COMM +", "+ COLUMN_IMAGE+") values('Кавказская пленница','Жанр: комедия, приключения, мелодрама, мюзикл\n" +
                "Страны: СССР, Россия\n" +
                "Режиссёр: Леонид Гайдай\n" +
                "Музыка: Александр Зацепин','Коментарий сериала:', 'kavkaz')");

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
        String[] columns = new  String[] {COLUMN_NAME,COLUMN_INFO,COLUMN_COMM,COLUMN_IMAGE};

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