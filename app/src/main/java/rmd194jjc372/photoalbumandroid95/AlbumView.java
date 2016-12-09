package rmd194jjc372.photoalbumandroid95;

import android.app.ActionBar;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.GridView;

import java.util.ArrayList;

import rmd194jjc372.photoalbumandroid95.model.Album;
import rmd194jjc372.photoalbumandroid95.model.GridViewAdapter;
import rmd194jjc372.photoalbumandroid95.model.Photo;

public class AlbumView extends AppCompatActivity {

    private GridView gridView;
    private GridViewAdapter gridAdapter;
    private Album selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        selected = HomeScreen.selected;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_view);

        //getActionBar().setTitle("PhotoAlbum95: " + selected.getName());
        //getSupportActionBar().setTitle("PhotoAlbum95: " + selected.getName());
        //Log.d("Tag", selected.getName());
        //setSupportActionBar(toolbar);
        //toolbar.setTitle("PhotoAlbumAndroid95: " + selected.getName());

        gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, getData());
        gridView.setAdapter(gridAdapter);
    }



    // Prepare some dummy data for gridview
    private ArrayList<Photo> getData() {
        final ArrayList<Photo> imageItems = selected.getPhotoList();//set array of photos in use to current album's
        return imageItems;
    }
}
