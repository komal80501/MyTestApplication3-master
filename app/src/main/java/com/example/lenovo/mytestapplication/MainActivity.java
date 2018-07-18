package com.example.lenovo.mytestapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DbHandler db;
    Button btSubmit;
    EditText edName,edEmail,edMobileNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btSubmit = (Button) findViewById(R.id.btnSubmit);
        edName = (EditText) findViewById(R.id.editName);
        edEmail = (EditText) findViewById(R.id.editEmail);
        edMobileNo=(EditText) findViewById(R.id.editMobileNo);

        db = new DbHandler(this);
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setName(edName.getText().toString());
                user.setEmail(edEmail.getText().toString());
                user.setMobileno(edMobileNo.getText().toString());
                boolean result = db.insert_USER(user);
                Log.i("result :", String.valueOf(result));
                    String name=edName.getText().toString();
                if (result == true) {
                    Toast.makeText(MainActivity.this, "data is submited", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, HomeViewActivity.class);
                    startActivity(i);
                    i.putExtra(name,""+name);
                } else {
                    Toast.makeText(MainActivity.this, "data is not submited", Toast.LENGTH_LONG).show();

                }
            }
        });

    }

}
