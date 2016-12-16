package rmd194jjc372.photoalbumandroid95;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import rmd194jjc372.photoalbumandroid95.model.Album;
import rmd194jjc372.photoalbumandroid95.model.Photo;
import rmd194jjc372.photoalbumandroid95.model.Tag;
import rmd194jjc372.photoalbumandroid95.model.bmpCompress;

public class PhotoDetails extends ActionBarActivity {

    public static ArrayList<String> stringAL = new ArrayList<String>();
    public static Album selected;
    public static Photo current;
    public static int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AddNewTag.search)
            selected = AddNewTag.selected;
        else
            selected = HomeScreen.selected;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_details);

        String title = getIntent().getStringExtra("title");
        byte[] bytes = getIntent().getByteArrayExtra("image");
        Bitmap bitmap = bmpCompress.decompress(bytes);

        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setImageBitmap(bitmap);
        for(Photo p : selected.getPhotoList()){
            if(p.getName().equals(title)){
                current = p;
                currentIndex = selected.getPhotoList().indexOf(p);
            }
        }
        getSupportActionBar().setTitle(current.getName());

        loadStrings();

        ListView mListView = (ListView) findViewById(R.id.tag_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.smalltextview, stringAL);

        mListView.setAdapter(adapter);

        imageView.setOnTouchListener(new OnSwipeTouchListener(this.getBaseContext()) {
            @Override
            public void onSwipeLeft() {
                if(!(currentIndex == (selected.getPhotoList().size() - 1))){
                    Photo item = selected.getPhotoList().get(currentIndex + 1);
                    Intent intent = new Intent(PhotoDetails.this, PhotoDetails.class);
                    intent.putExtra("title", item.getName());
                    intent.putExtra("image", item.getCompressedBMP());
                    finish();
                    startActivity(intent);
                }
            }
            public void onSwipeRight() {
                if(currentIndex != 0){
                    Photo item = selected.getPhotoList().get(currentIndex - 1);
                    Intent intent = new Intent(PhotoDetails.this, PhotoDetails.class);
                    intent.putExtra("title", item.getName());
                    intent.putExtra("image", item.getCompressedBMP());
                    finish();
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(!AddNewTag.search)
            getMenuInflater().inflate(R.menu.menu_photo_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_delete_photo)
        {
            selected.removePhoto(current);
            finish();
            startActivity(new Intent(PhotoDetails.this,AlbumView.class));
            return true;
        }
        else if(id == R.id.action_move)
        {
            finish();
            startActivity(new Intent(PhotoDetails.this,MovePhoto.class));
            return true;
        }
        else if(id == R.id.action_tags)
        {
            finish();
            startActivity(new Intent(PhotoDetails.this,TagList.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Detects left and right swipes across a view.
     */
    public class OnSwipeTouchListener implements OnTouchListener {

        private final GestureDetector gestureDetector;

        public OnSwipeTouchListener(Context context) {
            gestureDetector = new GestureDetector(context, new GestureListener());
        }

        public void onSwipeLeft() {
        }

        public void onSwipeRight() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends SimpleOnGestureListener {

            private static final int SWIPE_DISTANCE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float distanceX = e2.getX() - e1.getX();
                float distanceY = e2.getY() - e1.getY();
                if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (distanceX > 0)
                        onSwipeRight();
                    else
                        onSwipeLeft();
                    return true;
                }
                return false;
            }
        }
    }

    public void loadStrings()
    {
        stringAL.clear();
        for(Tag t: current.getTagList())
        {
            stringAL.add(t.toString());
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(PhotoDetails.this, AlbumView.class);
        startActivity(intent);
    }
}