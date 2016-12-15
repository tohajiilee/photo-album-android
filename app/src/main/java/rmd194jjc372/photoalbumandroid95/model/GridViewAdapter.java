package rmd194jjc372.photoalbumandroid95.model;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import rmd194jjc372.photoalbumandroid95.R;


public class GridViewAdapter extends ArrayAdapter {
    private Context context;
    private int layoutResourceId;
    private ArrayList<Photo> data = new ArrayList<Photo>();
    private static Bitmap myBitmap;

    public GridViewAdapter(Context context, int layoutResourceId, ArrayList data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = new ViewHolder();

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageTitle = (TextView) row.findViewById(R.id.textView);
            holder.image = (ImageView) row.findViewById(R.id.ImageView);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        Photo item = data.get(position);

        if( holder.imageTitle == null)
        {
            Log.e("Error", "Image title broken");
        }
        holder.imageTitle.setText(item.getName());
        holder.image.setImageBitmap(bmpCompress.decompress(item.getCompressedBMP()));


        return row;
    }

    static class ViewHolder
    {
        TextView imageTitle;
        ImageView image;
    }
}