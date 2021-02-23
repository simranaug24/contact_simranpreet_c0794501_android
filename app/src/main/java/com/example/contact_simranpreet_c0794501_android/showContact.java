package com.example.contact_simranpreet_c0794501_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.contact_simranpreet_c0794501_android.Model.RowModel;
import com.example.contact_simranpreet_c0794501_android.MyAdapter.Adapter;

import java.util.ArrayList;
import java.util.List;

public class showContact extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter myAdapter;
    ArrayList<RowModel> modelList;
    SQLiteHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);

        helper = new SQLiteHelper(this);
        recyclerView = findViewById(R.id.recyclerView);
        modelList = new ArrayList<>();

        modelList = helper.getAllContacts();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new Adapter(this, modelList, helper);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.show_contact_menu, menu);

        MenuItem search = menu.findItem(R.id.search_id);
        SearchView searchView = (SearchView) search.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                myAdapter.getFilter().filter(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }


}