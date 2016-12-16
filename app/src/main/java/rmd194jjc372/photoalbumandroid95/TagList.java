package rmd194jjc372.photoalbumandroid95;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import rmd194jjc372.photoalbumandroid95.model.Album;
import rmd194jjc372.photoalbumandroid95.model.Photo;
import rmd194jjc372.photoalbumandroid95.model.Tag;

/**
 * Created by JOEL on 12/13/2016.
 */

public class TagList extends AppCompatActivity {
    public static ArrayList<String> stringAL = new ArrayList<String>();
    public static Tag selectedTag;
    public static Photo current;
    public static Boolean edit = false;
    public static Activity activity;

    AlertDialog.Builder dlgAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_list);
        current = PhotoDetails.current;
        dlgAlert = new AlertDialog.Builder(this);
        activity = this;
        loadStrings();
        getSupportActionBar().setTitle("Tags for: " + current.getName());

        ListView mListView = (ListView) findViewById(R.id.TagListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringAL);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addTagButton);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AddNewTag.edit = false;
                AddNewTag.search = false;
                startActivity(new Intent(view.getContext(),AddNewTag.class));
            }
        });

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                selectedTag = current.getTagList().get(position);
                AddNewTag.edit = true;
                AddNewTag.search = false;
                return;
            }

        });
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(TagList.this, PhotoDetails.class);
        intent.putExtra("title", PhotoDetails.current.getName());
        intent.putExtra("image", PhotoDetails.current.getCompressedBMP());
        startActivity(intent);
    }

    public void loadStrings()
    {
        stringAL.clear();
        for(Tag t: current.getTagList())
        {
            //Log.e("My app", a.getName());
            stringAL.add(t.toString());
        }
    }
}
