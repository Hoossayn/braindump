package com.example.android.braindump;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerSwipeAdapter<RecyclerViewAdapter.myViewHolder> {

    private Context mcontext;
    private ArrayList<Ideas> ideaList;
    TextView ideasView;
    DbHelper db;
    String textView;
    EditText editText;



        public RecyclerViewAdapter(Context context, ArrayList<Ideas> ideasList) {
            this.mcontext = context;
            this.ideaList = ideasList;
        }

        @Override
        public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {



            View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.idea_view, parent, false);


            return new myViewHolder(itemview);
        }

        @Override
        public void onBindViewHolder(final myViewHolder holder, final int position) {

            Ideas ideas = ideaList.get(position);

            holder.thoughtsView.setText(ideas.getIdeas());

            holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);


            holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, holder.swipeLayout.findViewById(R.id.bottom_wrapper1));


            holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewById(R.id.bottom_wraper));


            holder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
                @Override
                public void onStartOpen(SwipeLayout layout) {

                }

                @Override
                public void onOpen(SwipeLayout layout) {

                }

                @Override
                public void onStartClose(SwipeLayout layout) {

                }

                @Override
                public void onClose(SwipeLayout layout) {

                }

                @Override
                public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

                }

                @Override
                public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

                }
            });

            holder.edit_btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                        /*ideaList.get(position);*/
                        Ideas ideas = ideaList.get(position);
                        String oneideas =  ideaList.get(position).getIdeas();
                        ideasView = (TextView) v.findViewById(R.id.tv_ideas);
                        editText = (EditText)v.findViewById(R.id.thoughts_edit);


                      /*  textView = (TextView) v.findViewById(R.id.tv_ideas);*/
                        textView = oneideas;
                        Intent intent = new Intent(v.getContext(), EditIdeas.class);
                        Bundle new_thought = new Bundle();
                        oneideas = ideas.getIdeas();
                        new_thought.putString("new ideas", (oneideas));
                        intent.putExtras(new_thought);
                        v.getContext().startActivity(intent);
                }
            });

            holder.delete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {


                        mItemManger.removeShownLayouts(holder.swipeLayout);
                        ideaList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, ideaList.size());
                        mItemManger.closeAllItems();
                        ideasView = (TextView)view.findViewById(R.id.tv_ideas);
                      /*  Ideas person = new Ideas(ideasView.getText().toString());
                        db.deleteIdeas(person);*/
                      /*  view.getContext().refreshData();*/

                }
            });
            mItemManger.bindView(holder.itemView, position);

            }



        @Override
        public int getItemCount() {
            return ideaList.size();
        }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {

        public SwipeLayout swipeLayout;
        public TextView thoughtsView;
        public TextView ideasView;
        public TextView delete;
        public ImageButton edit_btn;
        public TextView textView;
        public myViewHolder(View itemView) {

            super(itemView);
            delete = (TextView)itemView.findViewById(R.id.button_delete);
            textView = (TextView) itemView.findViewById(R.id.thoughts_edit);
            edit_btn = (ImageButton)itemView.findViewById(R.id.btn_edit);
            ideasView = (TextView) itemView.findViewById(R.id.tv_ideas);
            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
            thoughtsView = (TextView) itemView.findViewById(R.id.tv_ideas);

        }
    }
}