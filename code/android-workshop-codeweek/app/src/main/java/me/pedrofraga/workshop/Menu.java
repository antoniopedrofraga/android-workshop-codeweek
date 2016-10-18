package me.pedrofraga.workshop;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;

public class Menu extends AppCompatActivity {

    ListView listView;
    FloatingActionButton fab;
    ListLoader listLoader;

    ArrayList<String> stringArray;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        listView = (ListView) findViewById(R.id.listView);
        listLoader = new ListLoader(getBaseContext());

        stringArray = listLoader.loadList();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringArray);
        listView.setAdapter(adapter);

        Toast.makeText(this, "Clica no botão para adicionar um elemento", Toast.LENGTH_SHORT).show();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText input = new EditText(Menu.this);
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                input.setLayoutParams(lp);

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Menu.this, R.style.AppTheme);
                alertDialog.setMessage("Elemento a adicionar?");
                alertDialog.setView(input);
                alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (input.getText().toString().equals("")) return;
                        stringArray.add(input.getText().toString());
                        adapter.notifyDataSetChanged();
                    }
                });

                alertDialog.show();

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int index, long l) {
                stringArray.remove(index);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        try {
            listLoader.saveList(stringArray);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
