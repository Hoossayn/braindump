package com.example.android.braindump;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class InputIdeas extends AppCompatActivity {

    Button save;
    TextView thoughtsView;
    EditText thoutgts;
    List<Ideas> data = new ArrayList();
    DbHelper db;
    RecyclerView lstPersons;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ideas_input);

        db = new DbHelper(this);

        save = (Button)findViewById(R.id.button_save);
      /*  ideasView = (TextView)findViewById(R.id.tv_ideas);
        thoutgts = (EditText)findViewById(R.id.thoughts_input);*/
        lstPersons = (RecyclerView) findViewById(R.id.my_recycler_view);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                thoughtsView = (TextView)findViewById(R.id.tv_ideas);
                thoutgts = (EditText)findViewById(R.id.thoughts_input);
                if(thoutgts != null) {
                    Ideas person = new Ideas(thoutgts.getText().toString());
                    db.addIdeas(person);
                }
                startActivity(new Intent(InputIdeas.this, MainActivity.class));
                finish();

            }

        });
    }
   /* private void refreshData(){
        data=db.getAllPerson();
        RecyclerViewAdapter adapter;
        adapter = new RecyclerViewAdapter(this, InputIdeas, thoutgts);
        lstPersons.setAdapter(adapter);
    }*/
}
