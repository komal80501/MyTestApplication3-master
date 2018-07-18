package com.example.lenovo.mytestapplication;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.jar.Attributes;

public class ProfileViewActivity extends AppCompatActivity {
    SQLiteDatabase database;
    TextView Name,Mail,Age;
    Button Submitdatabtn,Showdatabtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        Name=(TextView) findViewById(R.id.userName);
        Mail=(TextView) findViewById(R.id.userEmail);

        User user = new User();
        user.setName(Name.setText());


    }


}
