package rmd194jjc372.photoalbumandroid95;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import rmd194jjc372.photoalbumandroid95.model.Album;
import rmd194jjc372.photoalbumandroid95.model.Photo;

public class MovePhoto extends AppCompatActivity {
    public static ArrayList<String> stringAL = new ArrayList<String>();
    public static Album selected;
    public static Photo current;

    AlertDialog.Builder dlgAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_photo);
        selected = HomeScreen.selected;
        current = PhotoDetails.current;
        dlgAlert = new AlertDialog.Builder(this);
        loadStringsExclusive();

        ListView mListView = (ListView) findViewById(R.id.AlbumListView_Move);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringAL);

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if(HomeScreen.albumAL.get(getTrueAlbum(stringAL.get(position))).addPhoto(current)) {
                    selected.removePhoto(current);
                    AlbumView.activity.finish();
                    Intent intent = new Intent(MovePhoto.this, AlbumView.class);
                    finish();
                    startActivity(intent);
                }
                else {
                    dlgAlert.setMessage("Photo " + current.getName() +
                            " already exists in album " + HomeScreen.albumAL.get(getTrueAlbum(stringAL.get(position))).getName());
                    dlgAlert.setTitle("Error");
                    dlgAlert.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //Dismiss
                                }
                            });
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                    return;
                }

            }

        });
    }

    public int getTrueAlbum(String albumName)
    {
        for(Album a: HomeScreen.albumAL)
            if(albumName.equals(a.getName()))
                return HomeScreen.albumAL.indexOf(a);
        return -1;
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(MovePhoto.this, PhotoDetails.class);
        intent.putExtra("title", PhotoDetails.current.getName());
        intent.putExtra("image", PhotoDetails.current.getCompressedBMP());
        startActivity(intent);
    }

    public void loadStringsExclusive()
    {
        stringAL.clear();
        for(Album a: HomeScreen.albumAL)
        {
            if(!(selected.getName().equals(a.getName())))
                stringAL.add(a.getName());
        }
    }
}
