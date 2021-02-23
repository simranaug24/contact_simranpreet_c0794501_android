package com.example.contact_simranpreet_c0794501_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText mEdtFirstName,mEdtLastName,mEdtEmail,mEdtPhone,mEdtAddress;
    Button mBtnAdd,mBtnList;

    SQLiteHelper helper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mEdtFirstName = findViewById(R.id.edtFirstName);
        mEdtLastName = findViewById(R.id.edtLastName);
        mEdtEmail = findViewById(R.id.edtEmail);
        mEdtPhone = findViewById(R.id.edtPhone);
        mEdtAddress = findViewById(R.id.edtAddress);
        mBtnAdd = findViewById(R.id.btnAdd);
        mBtnList = findViewById(R.id.btnList);


        helper = new SQLiteHelper(this);


        // add record to sqlite
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String fName = mEdtFirstName.getText().toString();
                String lName = mEdtLastName.getText().toString();
                String email = mEdtEmail.getText().toString();
                String phone = mEdtPhone.getText().toString();
                String address = mEdtAddress.getText().toString();


                long res =helper.insertData(fName,lName,email,phone,address);

                if (res != -1){
                    Toast.makeText(MainActivity.this, "Datainserted", Toast.LENGTH_SHORT).show();
                    clearFields();
                }else {
                    Toast.makeText(MainActivity.this, "Cannot insert", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // show record list
        mBtnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,showContact.class);

                startActivity(intent);
                finish();
            }
        });
    }

    private void clearFields() {


        mEdtFirstName.setText("");
        mEdtLastName.setText("");
        mEdtEmail.setText("");
        mEdtPhone.setText("");
        mEdtAddress.setText("");
    }

}