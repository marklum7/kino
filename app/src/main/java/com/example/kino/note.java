package com.example.kino;import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


public class note extends AppCompatActivity {

    EditText nameEditText;
    EditText infoEditText;
    EditText commEditText;

    String Position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        nameEditText = findViewById(R.id.nameEditText);
        infoEditText = findViewById(R.id.infoEditText);
        commEditText = findViewById(R.id.commEditText);

        Intent fromMainActivityIntent = getIntent();

        nameEditText.setText(fromMainActivityIntent.getExtras().getString(MainActivity.KEY_NAME));
        infoEditText.setText(fromMainActivityIntent.getExtras().getString(MainActivity.KEY_INFO));
        commEditText.setText(fromMainActivityIntent.getExtras().getString(MainActivity.KEY_COMM));
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