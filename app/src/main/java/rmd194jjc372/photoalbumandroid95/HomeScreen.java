package rmd194jjc372.photoalbumandroid95;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.util.ArrayList;

import rmd194jjc372.photoalbumandroid95.model.Album;

public class  HomeScreen extends AppCompatActivity
{
    public static ArrayList<Album> albumAL = new ArrayList<Album>();
    public static ArrayList<String> stringAL = new ArrayList<String>();
    public static Album selected;
    public static Activity activity;
    public static HomeScreen homeScr;
    private String fileName = "data.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        homeScr = this;
        /* if(albumAL.isEmpty())
        {
            albumAL.add(new Album("TestAlbum"));

        } */
        /*Intent intent = new Intent(HomeScreen.this, DataManagement.class);

        startActivity(intent);
        */
        loadData();

        activity = this;
        loadStrings();

        ListView mListView = (ListView) findViewById(R.id.AlbumListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringAL);

        mListView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.AddButton);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
                startActivity(new Intent(view.getContext(),AddNewAlbum.class));
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                selected = albumAL.get(position);
                finish();
                Intent intent = new Intent(HomeScreen.this, AlbumView.class);

                startActivity(intent);

            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            AddNewTag.search = true;
            Intent intent = new Intent(HomeScreen.this, AddNewTag.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void loadStrings()
    {
        stringAL.clear();
        for(Album a: albumAL)
        {
            //Log.e("My app", a.getName());
            stringAL.add(a.getName());
        }
    }


    public void saveData() {
        try {

            ArrayList<Album> DataToSave = albumAL;
            if(openFileOutput(fileName, Context.MODE_PRIVATE) == null){
                File file = new File(getFilesDir(), fileName);
                FileOutputStream newfos = new FileOutputStream(file);
                newfos.flush();
                newfos.close();
            }
            FileOutputStream fos = getApplicationContext().openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(DataToSave);
            os.close();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData() {
        try {

            FileInputStream fis = getApplicationContext().openFileInput(fileName);
            ObjectInputStream is = new ObjectInputStream(fis);

            albumAL = (ArrayList<Album>) is.readObject();

            is.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
