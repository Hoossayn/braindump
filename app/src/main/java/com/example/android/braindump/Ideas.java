package com.example.android.braindump;

public class Ideas {

    String idea;

    public Ideas(){ }


    public Ideas(String Idea ){
        this.idea = Idea;
    }

    public String getIdeas(){return idea;}

    public void setIdeas(String ideas) {
        this.idea = ideas;
    }

}
