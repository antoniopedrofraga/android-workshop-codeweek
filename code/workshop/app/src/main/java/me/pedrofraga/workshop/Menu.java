package me.pedrofraga.workshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {

    ListView listView;
    ArrayList<String> stringArray = new ArrayList();
    ArrayAdapter<String> adapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        listView = (ListView) findViewById(R.id.listView);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        stringArray = new ArrayList();
        stringArray.add("Workshop");
        stringArray.add("Android");
        stringArray.add("18 outubro");

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stringArray);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                stringArray.remove(i);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        fab
    }
}
