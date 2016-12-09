package rmd194jjc372.photoalbumandroid95;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

import rmd194jjc372.photoalbumandroid95.model.GridViewAdapter;
import rmd194jjc372.photoalbumandroid95.model.Photo;

public class AlbumView extends AppCompatActivity {

    private GridView gridView;
    private GridViewAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_view);

        gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, getData());
        gridView.setAdapter(gridAdapter);
    }

    // Prepare some dummy data for gridview
    private ArrayList<Photo> getData() {
        final ArrayList<Photo> imageItems = new ArrayList<>();//set array of photos in use to current album's
        return imageItems;
    }
}
