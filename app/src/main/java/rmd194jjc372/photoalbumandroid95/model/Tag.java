package rmd194jjc372.photoalbumandroid95.model;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Tag.
 */
public class Tag implements Serializable
{
	
	/** The list text. */
	String type, val, listText;
	
	/**
	 * Instantiates a new tag.
	 *
	 * @param typeIn the type in
	 * @param valIn the val in
	 */
	public Tag(String typeIn, String valIn){
		type = typeIn;
		val = valIn;
		listText = type + ":" + val;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType(){
		return type;
	}

	/**
	 * Gets the val.
	 *
	 * @return the val
	 */
	public String getVal(){
		return val;
	}

	/**
	 * Sets the type.
	 *
	 * @param typeIn the new type
	 */
	public void setType(String typeIn){
		type = typeIn;
		listText = type + ":" + val;
	}

	/**
	 * Sets the val.
	 *
	 * @param valIn the new val
	 */
	public void setVal(String valIn){
		val = valIn;
		listText = type + ":" + val;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return type + ":" + val;
	}
}