package com.ashsample.androidconcepts.recycleviews.pojos;

import java.util.ArrayList;

public final class MainRecycleViewItemsGenerator {

    private ArrayList<MainRecycleViewItem> listitems;

    private static MainRecycleViewItemsGenerator instance;
    static {
        instance = new MainRecycleViewItemsGenerator();
    }

    private MainRecycleViewItemsGenerator(){

    }

    public static MainRecycleViewItemsGenerator getInstance(){
        return instance;
    }
     private void generateListItems(){
        if(listitems == null){
            listitems = new ArrayList<>();
            listitems.add(new MainRecycleViewItem("LRUCache"));
            listitems.add(new MainRecycleViewItem("Room"));

        }
     }

    public ArrayList<MainRecycleViewItem> getListitems() {
        if(listitems == null){
            generateListItems();
        }
        return listitems;
    }



}
