package rmd194jjc372.photoalbumandroid95.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

// TODO: Auto-generated Javadoc
/**
 * The Album object.
 */
public class Album implements Serializable
{
	
	/** The album name. */
	String albumName;
	
	/** The owner of a given album's name. */
	//String ownerName;
	//String fileLoc;
	/** The photo list. */
	//Some kind of data to contain the photos.
	ArrayList<Photo> photoList;


	/**
	 * Instantiates a new album.
	 *
	 * @param albumNameIn the album name
	 * //@param ownerNameIn the owner name
	 */
	public Album(String albumNameIn)
	{
		albumName = albumNameIn;
		//ownerName = ownerNameIn;
		photoList = new ArrayList<Photo>();
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName()
	{
		return albumName;
	}

	/**
	 * Sets the name.
	 *
	 * @param albumNameIn the new name
	 */
	public void setName(String albumNameIn)
	{
		albumName = albumNameIn;
	}

	/**
	 * Gets the photo list.
	 *
	 * @return the photo list
	 */
	public ArrayList<Photo> getPhotoList(){
		return photoList;
	}

	/**
	 * Adds the photo.
	 *
	 * @param photoIn the photo to be added
	 * @return the boolean success or failure to add
	 */
	public Boolean addPhoto(Photo photoIn){
		if(!photoList.isEmpty()){
			for(int i = 0; i < photoList.size(); i++){
				if(photoList.contains(photoIn))
					return false;
			}
			photoList.add(photoIn);
		}
		else
			photoList.add(photoIn);
		return true;
	}

	/**
	 * Removes the photo.
	 *
	 * @param photoIn the photo to be removed.
	 */
	public void removePhoto(Photo photoIn){
		for(int i = 0; i < photoList.size(); i++){
			if(photoList.contains(photoIn)){
				photoList.remove(i);
			}
		}
	}

	/**
	 * Sets the photo list.
	 *
	 * @param list the new photo list
	 */
	public void setPhotoList(ArrayList<Photo> list)
	{
		photoList = list;
	}
	/*public String getLoc()
	{
		return fileLoc;
	}*/
}