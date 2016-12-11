package rmd194jjc372.photoalbumandroid95.model;

import android.graphics.Bitmap;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

// TODO: Auto-generated Javadoc
/**
 * The Photo object.
 */
public class Photo implements Serializable
{
	
	/** The photo directory. */
	//public String photoLoc;
	
	/** The caption. */
	//String caption;
	
	/** The photo file. */
	private Bitmap photo;
	/** the photo name */
	private String name;
	/** The date taken. */
	//long takenDate;

	/** */
    private byte[] compressedBMP;
	
	/** The date taken with milliseconds appended. */
	//Calendar takenCal;
	
	/** The tag list. */
	ArrayList<Tag> tagList;

	/**
	 * Instantiates a new photo.
	 *
	 * @param photoIn the photo in
	 */
	public Photo(Bitmap photoIn)
	{
		photo = photoIn;
		if(photoIn != null)
		{
			//photoLoc = photoIn.getPath();
			//takenDate = photo.lastModified();
			//takenCal = Calendar.getInstance();
			//takenCal.setTimeInMillis(photo.lastModified());
			//takenCal.set(Calendar.MILLISECOND, 0);
		}
		tagList = new ArrayList<Tag>();
	}

    public Photo (Bitmap photoIn, String newName)
    {
        photo = photoIn;
        if(photoIn != null)
        {
            name = newName;
            //photoLoc = photoIn.getPath();
        }
        tagList = new ArrayList<Tag>();
    }

    public Photo (byte[] bytes, String newName)
    {
        compressedBMP = bytes;
        if(compressedBMP != null)
        {
            name = newName;
            //photoLoc = photoIn.getPath();
        }
        tagList = new ArrayList<Tag>();
    }

	/**
	 * Gets the photo.
	 *
	 * @return the photo
	 */
    public Bitmap getPhoto(){
		return photo;
	}

	/**
	 * Gets the directory.
	 *
	 * @return the directory
	 */
	/*public String getLoc()
	{
		//return photoLoc;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	/*public String getDate()
	{
		String date;
		SimpleDateFormat ymd = new SimpleDateFormat("yyyy/MM/dd");
		date = ymd.format(takenCal.getTime());
		return date;
	}

	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	/*public String getTime()
	{
		String time;
		SimpleDateFormat tms = new SimpleDateFormat("k:m:s");
		time = tms.format(takenCal.getTime());
		return time;
	}

	/**
	 * Gets the long time.
	 *
	 * @return the long time
	 */
	/*public long getLongTime()
	{
		return takenDate;
	}

	/**
	 * Gets the caption.
	 *
	 * @return the caption
	 */
	/*public String getCaption()
	{
		return caption;
	}

	/**
	 * Gets the tag list.
	 *
	 * @return the tag list
	 */
	public ArrayList<Tag> getTagList()
	{
		return tagList;
	}

	/**
	 * Sets the file.
	 *
	 * @param photoIn the new file
	 */
	public void setFile(Bitmap photoIn)
	{
		photo = photoIn;
		//photoLoc = photoIn.getPath();
		//takenDate = photo.lastModified();
		//takenCal = Calendar.getInstance();
		//takenCal.setTimeInMillis(photo.lastModified());
		//takenCal.set(Calendar.MILLISECOND, 0);
	}

	/**
	 * Sets the caption.
	 *
	 * //@param captionIn the new caption
	 */
	/*public void setCaption(String captionIn)
	{
		caption = captionIn;
	}

	/**
	 * Adds the tag.
	 *
	 * @param tagIn the tag in
	 * @return the boolean
	 */
	public Boolean addTag(Tag tagIn){
		if(!tagList.isEmpty()){
			for(int i = 0; i < tagList.size(); i++){
				if(tagList.get(i).toString().equals(tagIn.toString()))
					return false;
			}
			tagList.add(tagIn);
		}
		else
			tagList.add(tagIn);
		return true;
	}

	/**
	 * Edits the tag.
	 *
	 * @param tagMatch the tag match
	 * @param tagIn the tag in
	 * @return the boolean
	 */
	public Boolean editTag(String tagMatch, Tag tagIn){
		if(!tagList.isEmpty()){
			for(int i = 0; i < tagList.size(); i++){
				if(tagList.get(i).toString().equals(tagMatch.toString())){
					if(!this.addTag(tagIn)){
						return false;
					}
					else{
						removeTag(tagMatch);
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Removes the tag.
	 *
	 * @param tagMatch the tag match
	 * @return the boolean
	 */
	public Boolean removeTag(String tagMatch){
		if(!tagList.isEmpty()){
			for(int i = 0; i < tagList.size(); i++){
				if(tagList.get(i).toString().equals(tagMatch)){
					tagList.remove(i);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Sets the tag list.
	 *
	 * @param list the new tag list
	 */
	public void setTagList(ArrayList<Tag> list)
	{
		tagList = list;
	}

    public byte[] getCompressedBMP()
    {
        return  compressedBMP;
    }
}