package rmd194jjc372.photoalbumandroid95;

import android.app.ActionBar;
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

import java.util.ArrayList;

import rmd194jjc372.photoalbumandroid95.model.Album;
import rmd194jjc372.photoalbumandroid95.model.GridViewAdapter;
import rmd194jjc372.photoalbumandroid95.model.Photo;

import static android.R.attr.data;

public class AlbumView extends AppCompatActivity {

    private GridView gridView;
    private GridViewAdapter gridAdapter;
    private Album selected;
    static final int  LOAD_IMAGE_RESULTS = 1;
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

                startActivityForResult(chooserIntent, LOAD_IMAGE_RESULTS);
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

        // Here we need to check if the activity that was triggers was the Image Gallery.
        // If it is the requestCode will match the LOAD_IMAGE_RESULTS value.
        // If the resultCode is RESULT_OK and there is some data we know that an image was picked.
        if (requestCode == LOAD_IMAGE_RESULTS && resultCode == RESULT_OK && data != null) {
            // Let's read picked image data - its URI
            Uri pickedImage = data.getData();
            // Let's read picked image path using content resolver
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();
            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);

            // Do something with the bitmap


            // At the end remember to close the cursor or you will end with the RuntimeException!
            cursor.close();
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
}
