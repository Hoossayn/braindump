package com.example.android.braindump;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EditIdeas extends AppCompatActivity {
    Button save;
    TextView thoughtsView;
    Editable textView;
    EditText thoutgts;
    List<Ideas> data = new ArrayList();
    DbHelper db;
    RecyclerView list_inputs;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ideas_input);

        db = new DbHelper(this);


        save = (Button)findViewById(R.id.button_save);
      /*  ideasView = (TextView)findViewById(R.id.tv_ideas);
        thoutgts = (EditText)findViewById(R.id.thoughts_input);*/
        list_inputs = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                thoughtsView = (TextView)findViewById(R.id.tv_ideas);
                thoutgts = (EditText)findViewById(R.id.thoughts_input);
                if(thoutgts != null) {
                    Ideas person = new Ideas(thoutgts.getText().toString());
                    db.updateIdeas(person);
                }
                startActivity(new Intent(EditIdeas.this, MainActivity.class));
                finish();

            }

        });

       /* textView = (EditText)findViewById(R.id.thoughts_input);
        textView = thoutgts.getText();*/
        thoutgts = (EditText)findViewById(R.id.thoughts_input);
        Bundle b = getIntent().getExtras();
        String textView = b.getString("new ideas");
        thoutgts.setText(textView);

   /*     thoughtsView = mRecyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.tv_ideas);
        String textView = thoughtsView.getText().toString();
        EditText editText = (EditText)findViewById(R.id.thoughts_input);
        editText.setText("google is your friend", TextView.BufferType.EDITABLE);

*/
    }
}
