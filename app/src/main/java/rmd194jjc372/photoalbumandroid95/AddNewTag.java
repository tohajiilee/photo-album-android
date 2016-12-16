package rmd194jjc372.photoalbumandroid95;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RadioGroup;
import android.widget.RadioButton;

import rmd194jjc372.photoalbumandroid95.model.Album;
import rmd194jjc372.photoalbumandroid95.model.Photo;
import rmd194jjc372.photoalbumandroid95.model.Tag;

/**
 * Created by JOEL on 12/13/2016.
 */

public class AddNewTag extends AppCompatActivity {

    Button mButton;
    EditText mEdit;
    TextView mText;
    RadioGroup mGroup;
    RadioButton mRadioPerson, mRadioLocation;
    public static Album selected;
    public static Boolean edit = false;
    public static Boolean search = false;

    Photo current;
    Tag currentTag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_tag);
        current = PhotoDetails.current;
        getSupportActionBar().setTitle("Create New Tag");

        mText = (TextView)findViewById(R.id.tagTextView);
        mButton = (Button) findViewById(R.id.buttonCreateTag);
        mEdit   = (EditText)findViewById(R.id.editTagField);
        mGroup = (RadioGroup)findViewById(R.id.radioType);


        if(edit){
            currentTag = TagList.selectedTag;
            if(currentTag == null){
                Log.e("Fatal error", "Tag edit was accessed despite null tag entry");
                finish();
            }
            getSupportActionBar().setTitle("Editing Tag");
            mText.setText("Edit current tag:");
            mButton.setText("Edit Tag");
            mEdit.setText(currentTag.getVal());
            if(currentTag.getType().equals("location")) {
                ((RadioButton) findViewById(R.id.locationButton)).setChecked(true);
                ((RadioButton) findViewById(R.id.personButton)).setChecked(false);
            }
        }
        if(search){
            getSupportActionBar().setTitle("Search");
            mText.setText("Search for tag:");
            mButton.setText("Search");
            mEdit.setHint("(case sensitive)...");
        }

        mButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String input = mEdit.getText().toString().trim();
                Tag newTag;
                if( ((RadioButton) findViewById(R.id.locationButton)).isChecked() )
                    newTag = new Tag("location", input);
                else
                    newTag = new Tag("person", input);
                if(input.equals(""))
                {
                    mEdit.setError("Please enter a tag value.");
                    return;
                }
                if(edit) {
                    if(current.editTag(currentTag.toString(), newTag)){
                        TagList.activity.finish();
                        HomeScreen.homeScr.saveData();
                        finish();
                        startActivity(new Intent(view.getContext(), TagList.class));
                    }
                    else{
                        mEdit.setError("Tag with that type and value already exists.");
                        return;
                    }
                }
                else if(!search){
                    if(current.addTag(newTag)) {
                        TagList.activity.finish();
                        HomeScreen.homeScr.saveData();
                        finish();
                        startActivity(new Intent(view.getContext(), TagList.class));
                    }
                    else{
                        mEdit.setError("Tag with that type and value already exists.");
                        return;
                    }
                }
                else{
                    selected = searchWithTag(newTag);
                    finish();
                    startActivity(new Intent(view.getContext(), AlbumView.class));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(edit)
            getMenuInflater().inflate(R.menu.menu_add_new_tag, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete_tag) {
            current.removeTag(currentTag.toString());
            TagList.selectedTag = null;
            TagList.activity.finish();
            HomeScreen.homeScr.saveData();
            finish();
            startActivity(new Intent(AddNewTag.this, TagList.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public Album searchWithTag(Tag input){
        Album searchResult = new Album("Search Result");
        for (Album a : HomeScreen.albumAL)
            for(Photo p : a.getPhotoList())
                for(Tag t : p.getTagList())
                    if(input.getType().equals(t.getType()))
                        if(t.getVal().startsWith(input.getVal()))
                            searchResult.addPhoto(p);

        return searchResult;
    }

    @Override
    public void onBackPressed() {
        selected = null;
        finish();
    }
}
