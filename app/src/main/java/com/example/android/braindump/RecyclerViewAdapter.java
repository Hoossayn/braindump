package com.example.android.braindump;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.myViewHolder> {


    private List<Ideas> ideaList;
    private final View.OnClickListener onClickListener = new MyOn

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView thoughtsView;
        EditText thoutgts;


        public myViewHolder(View itemView) {
            super(itemView);
            thoughtsView = (TextView) itemView.findViewById(R.id.tv_ideas);
            thoutgts = (EditText)itemView.findViewById(R.id.thoughts_input);

        }
    }

    public RecyclerViewAdapter(List<Ideas> ideasList){
        this.ideaList = ideasList;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.idea_view, parent, false);

        myViewHolder vh = new myViewHolder(itemview);
        return vh;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

        Ideas ideas = ideaList.get(position);
        holder.thoughtsView.setText(ideas.getIdeas());

    }

    @Override
    public int getItemCount() {
        return ideaList.size();
    }
}
