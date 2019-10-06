package com.ashsample.androidconcepts.recycleviews.pojos;

public class MainRecycleViewItem {
    public MainRecycleViewItem(String tilte1){
        this.title = tilte1;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;
}
