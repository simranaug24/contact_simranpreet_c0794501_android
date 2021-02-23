package com.example.contact_simranpreet_c0794501_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contact_simranpreet_c0794501_android.Model.RowModel;

public class UpdateActivity extends AppCompatActivity {

    EditText FirstName,LastName,EmailID,Phone,Address;
    Button btnupdate;
    SQLiteHelper helper;
    RowModel model;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        int incomingId = getIntent().getIntExtra("id",1);
         helper = new SQLiteHelper(this);
         
         model = helper.getOneContact(incomingId);
         
         
        FirstName = findViewById(R.id.edtFirstname);
        LastName = findViewById(R.id.edtLastName);
        EmailID = findViewById(R.id.edtEmail);
        Phone = findViewById(R.id.edtPhn);
        Address = findViewById(R.id.edtAdd);
        btnupdate = findViewById(R.id.btnUpdate);
      
        FirstName.setText(model.getfName());
        LastName.setText(model.getlName());
       EmailID.setText(model.getEmail());
       Phone.setText(model.getPhone());
       Address.setText(model.getAddress());
       
       
       btnupdate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
             boolean bool =   helper.updateData(incomingId,FirstName.getText().toString(),LastName.getText().toString(),EmailID.getText().toString(),Phone.getText().toString(),Address.getText().toString());

               if (bool == false) {
                   Toast.makeText(UpdateActivity.this, "Updated...", Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(UpdateActivity.this,showContact.class);
                   startActivity(intent);
                   finish();
               }
               else {
                   Toast.makeText(UpdateActivity.this, "not updated ", Toast.LENGTH_SHORT).show();
               }
           }
       });
        




    }


}