package rmd194jjc372.photoalbumandroid95;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import rmd194jjc372.photoalbumandroid95.model.Album;

import static android.R.id.list;
import static rmd194jjc372.photoalbumandroid95.R.id.AlbumListView;

public class HomeScreen extends AppCompatActivity
{
    public static ArrayList<Album> albumAL = new ArrayList<Album>();
    public static ArrayList<String> stringAL = new ArrayList<String>();
    public static Album selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /* if(albumAL.isEmpty())
        {
            albumAL.add(new Album("TestAlbum"));

        } */
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
                startActivity(new Intent(view.getContext(),AddNewAlbum.class));
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                selected = albumAL.get(position);

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
        if (id == R.id.action_settings) {
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
}
