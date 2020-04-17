package com.example.loginact.ui.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.loginact.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ScrollingActivity extends AppCompatActivity {
    private ListView listView;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);

        //Pasamos el mensaje a una list de mensajes
        ArrayList<String> listaMensaje = new ArrayList<String>();
        String[] arrayMensaje = message.split(" ");
        for (int x=0; x<arrayMensaje.length; x++) {
            listaMensaje.add(arrayMensaje[x]);
        }

        listaMensaje.remove(0);

        // Capture the layout's TextView and set the string as its text
        //TextView textView = findViewById(R.id.requestMsg);
        //textView.setText(message);

        listView = (ListView)findViewById(R.id.simpleListView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.activity_listview,
                R.id.textView,
                listaMensaje );

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String selectedFromList = listView.getItemAtPosition(position).toString().trim();
                Toast.makeText(getApplicationContext(),selectedFromList, Toast.LENGTH_LONG).show();
                Uri selectedFromListURI = Uri.parse(selectedFromList);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, selectedFromListURI);
                //mapIntent.setPackage("com.google.android.apps.maps");
                // Attempt to start an activity that can handle the Intent
                startActivity(mapIntent);
            }
        });
        //

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
