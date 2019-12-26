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
            listitems.add(new MainRecycleViewItem("Local Bound Service"));
            listitems.add(new MainRecycleViewItem("Remote Messenger Service"));
            listitems.add(new MainRecycleViewItem("Remote AIDL Service"));
            listitems.add(new MainRecycleViewItem("Work Manager"));
            listitems.add(new MainRecycleViewItem("Local Broadcast"));
            listitems.add(new MainRecycleViewItem("Fragments"));
            listitems.add(new MainRecycleViewItem("Kotlin Activity"));
            listitems.add(new MainRecycleViewItem("Kotlin RecycleView"));
            listitems.add(new MainRecycleViewItem("DataBinding Activity"));


        }
     }

    public ArrayList<MainRecycleViewItem> getListitems() {
        if(listitems == null){
            generateListItems();
        }
        return listitems;
    }



}
