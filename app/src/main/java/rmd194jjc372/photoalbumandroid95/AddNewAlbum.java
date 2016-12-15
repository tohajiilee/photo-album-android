package rmd194jjc372.photoalbumandroid95;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import rmd194jjc372.photoalbumandroid95.model.Album;

public class AddNewAlbum extends AppCompatActivity {
    //public static ArrayList<Album> albumAL = HomeScreen.albumAL;
    Button   mButton;
    EditText mEdit;
    TextView mText;
    public static Boolean rename = false;

    Album newAlbum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_album);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mText = (TextView)findViewById(R.id.errorText);
        mButton = (Button) findViewById(R.id.create);
        mEdit   = (EditText)findViewById(R.id.albumNameIn);

        if(rename){
            mText.setText("Rename current album:");
            mEdit.setText(HomeScreen.selected.getName());
        }

        mButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String input = mEdit.getText().toString().trim();
                if(input.equals(""))
                {
                    mEdit.setError("Please enter an album name.");
                    return;
                }
                newAlbum = new Album(input);
                for(Album a: HomeScreen.albumAL)
                {
                    if(newAlbum.getName().equals(a.getName()))
                    {
                        mEdit.setError("Album with that name already exists.");
                        return;
                    }
                }
                if(rename) {
                    HomeScreen.selected.setName(input);
                    HomeScreen.homeScr.saveData();
                    AlbumView.activity.finish();
                    finish();
                    startActivity(new Intent(view.getContext(),HomeScreen.class));
                }
                else {
                    HomeScreen.albumAL.add(newAlbum);
                    HomeScreen.homeScr.saveData();
                    finish();
                    startActivity(new Intent(view.getContext(), HomeScreen.class));
                }
            }
        });
    }



}