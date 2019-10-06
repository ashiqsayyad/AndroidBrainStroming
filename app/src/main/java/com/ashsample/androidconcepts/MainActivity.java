package com.ashsample.androidconcepts;

import android.content.Intent;
import android.os.Bundle;

import com.ashsample.androidconcepts.mvvm.room.NoteDao;
import com.ashsample.androidconcepts.mvvm.room.NoteDatabase;
import com.ashsample.androidconcepts.mvvm.room.NoteEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.provider.ContactsContract;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.ashsample.androidconcepts.recycleviews.pojos.MainRecycleViewAdapter;
import com.ashsample.androidconcepts.recycleviews.pojos.MainRecycleViewItem;
import com.ashsample.androidconcepts.recycleviews.pojos.MainRecycleViewItemsGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<MainRecycleViewItem> listitems;
    RecyclerView.Adapter mainRecycleViewAdapter;
    RecyclerView.LayoutManager mainRecycleViewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent temp = new Intent(MainActivity.this,MainKotlinActivity.class);
                startActivity(temp);

                /*new Thread(new Runnable() {
                    @Override
                    public void run() {
                        NoteDao noteDao = NoteDatabase.getInstance(MainActivity.this).getNoteDao();
                        NoteEntity temp = new NoteEntity("AshiqNote1","this is ashiq1",1);
                        noteDao.insert(temp);

                        NoteEntity temp1 = new NoteEntity("AshiqNote12","this is ashiq12",2);
                        noteDao.insert(temp1);

                        List<NoteEntity> tempList = noteDao.getAllNotes();
                       // NoteEntity temp3 = new NoteEntity("AshiqNote2","this is ashiq2",2);
                       // temp.setId(tempList.get(0).getId());


                        noteDao.deleteByDescription("this is ashiq12");
                        tempList = noteDao.getAllNotes();
                    }
                });*///.start();



            }
        });
        initMainRecylceView();
        tryMaps();


    }
     public void tryMaps(){
        ArrayMap<String, String> temp = new ArrayMap<>();
         temp.put("second","tiger");
        temp.put("first","zebra");
        temp.put("thrid","deer");

         HashMap<String, String> temp1 = new HashMap<>();
         temp1.put("second","tiger");
         temp1.put("first","zebra");
         temp1.put("thrid","deer");



    }

    public void initMainRecylceView(){
       listitems = MainRecycleViewItemsGenerator.getInstance().getListitems();
        mainRecycleViewAdapter = new MainRecycleViewAdapter(listitems);
        mainRecycleViewLayoutManager =new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        RecyclerView mainRecycleView = findViewById(R.id.mainrecycleview);
        mainRecycleView.setAdapter(mainRecycleViewAdapter);
        mainRecycleView.setLayoutManager(mainRecycleViewLayoutManager);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Ashiq","pppppppppppppppppp");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
