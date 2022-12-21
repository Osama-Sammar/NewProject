package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    private Button btnSaveData;
    private Button btnLoadData;
    private static final String DATA = "DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();

    }



    public void btnSaveData(View view){
        Book[] books = new Book[2];

        books[0] = new Book("Java Core", "John");
        books[0] = new Book("C# in a NutshelL", "Mark");

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String booksString = gson.toJson(books);

        editor.putString(DATA, booksString);
        editor.commit();
        Toast.makeText(this, "Data Saved:\n" + booksString,
                Toast.LENGTH_SHORT).show();

    }

    public void btnLoadClick(){

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String str = prefs.getString(DATA, "");
        if(!str.equals("")){
            Book[] books = gson.fromJson(str, Book[].class);

            Toast.makeText(this, "numbers of books" + books.length
                    ,Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this, "Data not found",
                    Toast.LENGTH_SHORT).show();
        }
    }


    public void setupViews(){

        btnSaveData = findViewById(R.id.btnSaveData);
        btnLoadData = findViewById(R.id.btnLoadData);
    }


}