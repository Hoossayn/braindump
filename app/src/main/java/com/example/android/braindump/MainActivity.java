package com.example.android.braindump;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.daimajia.swipe.util.Attributes;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{


    private RecyclerView mRecyclerView;
    RecyclerView list_inputs;
    ArrayList<Ideas> inputdata;
    public DbHelper db;
    TextView textView;
    Button buttonAdd;
    RecyclerViewAdapter mAdapter;
    TextView delete;
    Button save;
    TextView ideasView;
    TextView tvEmptyTextView;
    EditText thoutgts;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DbHelper(this);

        editText = (EditText)findViewById(R.id.thoughts_edit);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        list_inputs = (RecyclerView) findViewById(R.id.my_recycler_view);
        buttonAdd = (Button)findViewById(R.id.button_add);

        delete = (TextView)findViewById(R.id.button_delete);
        save = (Button)findViewById(R.id.button_save);

        tvEmptyTextView = (TextView) findViewById(R.id.empty_view);

        //
        textView = (TextView) findViewById(R.id.thoughts_edit);

     /*   lstPersons = (RecyclerView) findViewById(R.id.my_recycler_view);*/
        thoutgts = (EditText)findViewById(R.id.thoughts_input);
        ideasView = (TextView)findViewById(R.id.tv_ideas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        inputdata = new ArrayList<>();


      /*  mRecyclerView.addOnItemTouchListener(new RecyclerTouchEvent(getApplicationContext(),mRecyclerView, new RecyclerTouchEvent.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Ideas ideas = data.get(position);
           //     ideasView = mRecyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.tv_ideas);
                ideasView = (TextView)findViewById(R.id.tv_ideas);
                String textView = ideasView.getText().toString();

                Intent intent = new Intent(MainActivity.this, InputIdeas.class);
                Bundle new_thought = new Bundle();
                textView = ideas.getIdeas();
                new_thought.putString("new ideas", textView);

                intent.putExtras(new_thought);
                startActivity(intent);



                *//* String textView = ideasView.getText().toString();
                EditText editText = (EditText)findViewById(R.id.thoughts_input);
                editText.getText(editText, TextView.BufferType.EDITABLE).toString();

                startActivity(new Intent(MainActivity.this, InputIdeas.class));
                finish();*//*
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));*/
/*

        if(inputdata.isEmpty()){
            mRecyclerView.setVisibility(View.GONE);
            tvEmptyTextView.setVisibility(View.VISIBLE);
        }else{
            mRecyclerView.setVisibility(View.VISIBLE);
            tvEmptyTextView.setVisibility(View.GONE);
        }
*/

         mAdapter = new RecyclerViewAdapter(this, inputdata);

        ((RecyclerViewAdapter) mAdapter).setMode(Attributes.Mode.Single);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.e("RecyclerView", "onScrollStateChanged");
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        refreshData();
    }




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
        inputdata= (ArrayList<Ideas>) db.getAllPerson();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getApplicationContext(),inputdata);
        list_inputs.setAdapter(adapter);
    }

}
