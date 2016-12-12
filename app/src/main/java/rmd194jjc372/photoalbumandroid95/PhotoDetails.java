package rmd194jjc372.photoalbumandroid95;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import rmd194jjc372.photoalbumandroid95.model.Album;
import rmd194jjc372.photoalbumandroid95.model.Photo;
import rmd194jjc372.photoalbumandroid95.model.bmpCompress;

public class PhotoDetails extends ActionBarActivity {

    private Album selected;
    private Photo current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        selected = HomeScreen.selected;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_details);

        String title = getIntent().getStringExtra("title");
        byte[] bytes = getIntent().getByteArrayExtra("image");
        Bitmap bitmap = bmpCompress.decompress(bytes);

        TextView titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText(title);

        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setImageBitmap(bitmap);
        for(Photo p : selected.getPhotoList()){
            if(p.getName().equals(title)){
                current = p;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photo_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_delete_photo)
        {
            HomeScreen.selected.removePhoto(current);
            finish();
            startActivity(new Intent(this,AlbumView.class));
            return true;
        }
        else if(id == R.id.action_tags)
        {

        }

        return super.onOptionsItemSelected(item);
    }
}