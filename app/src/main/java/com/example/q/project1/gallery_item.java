package com.example.q.project1;

import android.graphics.Bitmap;


public class gallery_item {

    private Bitmap bm;
    private String link;

    public Bitmap getBm(){
        return bm;
    }
    public String getLink() {return link;}

    public void setBm(Bitmap bm){
        this.bm = bm;
    }
    public void setLink(String link){
        this.link = link;
    }
}