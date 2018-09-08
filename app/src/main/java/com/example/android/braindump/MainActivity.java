package com.example.android.braindump;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{


    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView lstPersons;
    List<Ideas> data = new ArrayList();
    DbHelper db;
    Button buttonAdd;
    ImageView delete;
    Button save;
    TextView ideasView;
    EditText thoutgts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DbHelper(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        buttonAdd = (Button)findViewById(R.id.button_add);
        delete = (ImageView)findViewById(R.id.button_delete);
        save = (Button)findViewById(R.id.button_save);
        lstPersons = (RecyclerView) findViewById(R.id.my_recycler_view);
        thoutgts = (EditText)findViewById(R.id.thoughts_input);
        ideasView = (TextView)findViewById(R.id.tv_ideas);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new RecyclerViewAdapter(data);
        mRecyclerView.setAdapter(mAdapter);


        refreshData();

/*

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             startActivity(new Intent(MainActivity.this, InputIdeas.class));
            }
        });
*/


    }
/*    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.button_add) {
            startActivity(new Intent(MainActivity.this, InputIdeas.class));
            finish();

        }
    }*/

    public void botton_Add(View view){
        startActivity(new Intent(this, InputIdeas.class));
        finish();

    }

    public void button_delete(View view){
        ideasView = (TextView)findViewById(R.id.tv_ideas);
        Ideas person = new Ideas(ideasView.getText().toString());
        db.deleteIdeas(person);
        refreshData();
    }

    private void refreshData(){
        data=db.getAllPerson();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(data);
        lstPersons.setAdapter(adapter);
    }


}
