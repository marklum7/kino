package com.example.kino;import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;


public class note extends AppCompatActivity {

    TextView nameEditText;
    EditText infoEditText;
    EditText commEditText;
    ImageView imageView;

    Map<String,Integer> ImagesList = new HashMap<>();
    String Position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //для банеров фильма
        ImagesList.put("molodezka", R.drawable.molodezka);
        ImagesList.put("trudnie", R.drawable.trudnie);
        ImagesList.put("ivan", R.drawable.ivan);
        ImagesList.put("plus", R.drawable.plus);
        ImagesList.put("kuhn", R.drawable.kuhn);
        ImagesList.put("volk", R.drawable.volk);
        ImagesList.put("ataka", R.drawable.ataka);
        ImagesList.put("kavkaz", R.drawable.kavkaz);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        //получение элементов из активити
        nameEditText = findViewById(R.id.nameEditText);
        infoEditText = findViewById(R.id.infoEditText);
        commEditText = findViewById(R.id.commEditText);
        imageView = findViewById(R.id.imageView);

        //заполняем поля
        Intent fromMainActivityIntent = getIntent();
        String info = fromMainActivityIntent.getExtras().getString(MainActivity.KEY_INFO);
        String comm = fromMainActivityIntent.getExtras().getString(MainActivity.KEY_COMM);
        nameEditText.setText(fromMainActivityIntent.getExtras().getString(MainActivity.KEY_NAME));
        infoEditText.setText(info);
        commEditText.setText(comm);
        imageView.setImageResource(ImagesList.get(fromMainActivityIntent.getExtras().getString(MainActivity.KEY_IMAGE)));

        Position = fromMainActivityIntent.getExtras().getString(MainActivity.KEY_POSITION);




    }

    public void OnBackButtonClick(View view)
    {
        //возврат активити и передача в бд элементов
        Intent returnIntent = new Intent();
        returnIntent.putExtra(MainActivity.KEY_NAME,nameEditText.getText().toString());
        returnIntent.putExtra(MainActivity.KEY_INFO,infoEditText.getText().toString());
        returnIntent.putExtra(MainActivity.KEY_COMM,commEditText.getText().toString());
        returnIntent.putExtra(MainActivity.KEY_POSITION, Integer.valueOf(Position));
        setResult(RESULT_OK,returnIntent);
        finish();

    }
}