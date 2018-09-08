package com.example.android.braindump;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class
IdeasAdapter extends BaseAdapter {

    Activity activity;
    List<Ideas> listIdeas;
    LayoutInflater inflater;
    TextView tv_ideas;
    EditText thoutgts;


    public IdeasAdapter(Activity activity, List<Ideas> listId, TextView ideas_tv) {
        this.activity = activity;
        this.listIdeas = listId;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.tv_ideas = ideas_tv;

    }

    @Override
    public int getCount() {
        return listIdeas.size();
    }

    @Override
    public Object getItem(int position) {
        return listIdeas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null ) {

            convertView = inflater.inflate(R.layout.idea_view, null);
            final TextView ideas_view;
            ideas_view = (TextView)convertView.findViewById(R.id.tv_ideas);
            thoutgts = (EditText)convertView.findViewById(R.id.thoughts_input);

            ideas_view.setText(""+listIdeas.get(position).getIdeas());



            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    tv_ideas.setText(thoutgts.getText().toString());

                }
            });

        }return convertView;
    }
}
