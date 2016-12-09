package rmd194jjc372.photoalbumandroid95;

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


    Album newAlbum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_album);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mButton = (Button) findViewById(R.id.create);
        mEdit   = (EditText)findViewById(R.id.albumNameIn);

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
                HomeScreen.albumAL.add(newAlbum);
                startActivity(new Intent(view.getContext(),HomeScreen.class));
            }
        });
    }

}
