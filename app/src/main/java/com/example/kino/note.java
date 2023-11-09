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
    TextView infoEditText;
    EditText commEditText;
    ImageView imageView;

    Map<String,Integer> ImagesList = new HashMap<>();
    String Position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImagesList.put("molodezka", R.drawable.molodezka);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        nameEditText = findViewById(R.id.nameEditText);
        infoEditText = findViewById(R.id.infoEditText);
        commEditText = findViewById(R.id.commEditText);
        imageView = findViewById(R.id.imageView);

        Intent fromMainActivityIntent = getIntent();

        nameEditText.setText(fromMainActivityIntent.getExtras().getString(MainActivity.KEY_NAME));
        infoEditText.setText(fromMainActivityIntent.getExtras().getString(MainActivity.KEY_INFO));
        commEditText.setText(fromMainActivityIntent.getExtras().getString(MainActivity.KEY_COMM));
        imageView.setImageResource(ImagesList.get(fromMainActivityIntent.getExtras().getString(MainActivity.KEY_IMAGE)));

        Position = fromMainActivityIntent.getExtras().getString(MainActivity.KEY_POSITION);




    }

    public void OnBackButtonClick(View view)
    {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(MainActivity.KEY_NAME,nameEditText.getText().toString());
        returnIntent.putExtra(MainActivity.KEY_INFO,infoEditText.getText().toString());
        returnIntent.putExtra(MainActivity.KEY_COMM,commEditText.getText().toString());
        returnIntent.putExtra(MainActivity.KEY_POSITION, Integer.valueOf(Position));
        setResult(RESULT_OK,returnIntent);
        finish();

    }
}