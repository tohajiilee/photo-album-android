package rmd194jjc372.photoalbumandroid95;

import android.app.ActionBar;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import rmd194jjc372.photoalbumandroid95.model.Album;
import rmd194jjc372.photoalbumandroid95.model.GridViewAdapter;
import rmd194jjc372.photoalbumandroid95.model.Photo;

import static android.R.attr.data;
import static android.R.attr.translateY;

public class AlbumView extends AppCompatActivity {

    private GridView gridView;
    private GridViewAdapter gridAdapter;
    private Album selected;
    private ArrayList<Photo> photos;
    private static final int RESULT_LOAD_IMAGE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        selected = HomeScreen.selected;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_view);
        photos = getData();
        //getActionBar().setTitle("PhotoAlbum95: " + selected.getName());
        //getSupportActionBar().setTitle("PhotoAlbum95: " + selected.getName());
        //Log.d("Tag", selected.getName());
        //setSupportActionBar(toolbar);
        //toolbar.setTitle("PhotoAlbumAndroid95: " + selected.getName());

        gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, getData());
        gridView.setAdapter(gridAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addPhotoButton);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

                startActivityForResult(chooserIntent,  RESULT_LOAD_IMAGE);


            }
        });




        /*
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

        startActivityForResult(chooserIntent, PICK_IMAGE);*/
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case RESULT_LOAD_IMAGE: //this is a constant, in your case I think it should be '1'
                if (data != null) {// e.g. "back" pressed"
                    Uri contentURI = data.getData();
                    try
                    {
                        Bitmap bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(contentURI));
                        HomeScreen.selected.addPhoto(new Photo(bmp, getURIFileName(contentURI)));
                    }
                    catch(FileNotFoundException f)
                    {

                    }
                }

                break;
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_album_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_delete)
        {
            HomeScreen.albumAL.remove(selected);
            selected = null;
            startActivity(new Intent(this,HomeScreen.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    //Get dataset from current album
    private ArrayList<Photo> getData() {
        final ArrayList<Photo> imageItems = selected.getPhotoList();
        return imageItems;
    }
    private String getURIFileName(Uri uri) {
        String fileName = "noFileName";
        if (uri.getScheme().equals("file")) {
            fileName = uri.getLastPathSegment();
        } else {
            Cursor cursor = null;
            try {
                cursor = getContentResolver().query(uri, new String[]{
                        MediaStore.Images.ImageColumns.DISPLAY_NAME
                }, null, null, null);

                if (cursor != null && cursor.moveToFirst()) {
                    fileName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DISPLAY_NAME));
                    //Log.d(TAG, "name is " + fileName);
                }
            } finally {

                if (cursor != null) {
                    cursor.close();
                }
            }
        }

        return fileName;
    }

}
