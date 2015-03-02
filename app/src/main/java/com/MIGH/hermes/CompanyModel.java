package com.MIGH.hermes;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

/* This is the data structure for the companies to be stored. 
 * This would be primarily used to inflate and populate the listViews in the app.
 * The 'perfect' form of this data structure would involve a parent company class,
 *  with child classes, specific to the various types of companies in Ghana.
 *  The web contact info, website, twitter, facebook, etc, would be stored in a map,
 *  or some other key-value pair data structure, and groups of companies would also be stored
 *  in such data structures, with the key being the Companies Identification Number, CIN
 *   
 *   Any differences from this ideal goal you find in the implementation would be due to
 *   my inexperience in the java environment, and android coding 
 *   lack of time
 *  */

/**
 * @author Kobby
 *
 */
// #TODO Implement the Parcelable interface on this class. WOuld come in handy later on

public class CompanyModel {
	
	/**
	 * @param cIN
	 * @param name
	 * @param phoneNumber
	 * @param location
	 * @param logo
	 * @param website
	 * @param email
	 * @param category
	 * @param postal
	 * @param description
	 * @param gPS
	 * @param tags
	 * @param advert
	 * @param facebook
	 * @param twitter
	 * @param google
	 */
	// This key uniquely identifies each company. Same as DB primary key.
	private String CIN;	
	private String Name;
	//Phone numbers are in an array because a place may have more than one. Stored as strings because phone numbers arent integer values
	private String [] PhoneNumber; 
	private String location;
	private String logo; //Final form would be an image
	private String website;
	private String email;
	private String category;
	private String postal; // Postal Address of the company
	private String description; //Description of the company, including working hours
	private String GPS; // GPS coordinates of the company's location. Used for the maps interface
	private String tags;
	// These would be null valued until the information is gathered
	private String advert; //Final form would be an image
	private String facebook; 
	private String twitter;
	private String google;
	
	
	// CONSTRUCTOR
	public CompanyModel(
			String cIN,
			String name,
			String phoneNumber, // This is made a string cos the DB cant store arrays. 
			String location,
			String logo,
			String website,
			String email,
			String category,
			String postal,
			String description,
			String gPS,
			String tags,
			String advert,
			String facebook,
			String twitter,
			String google) {
		super();
		this.CIN = cIN;
		this.Name = name;
		
		
		// Assignment procedure for the PhoneNumber is a longer cos i need to break the string into an array
		this.PhoneNumber = phoneNumberSplitter(phoneNumber);
		this.location = location;
		//this.logo= convertToBitmap(logo);
		this.logo = logo;
		this.website = website;
		this.email = email;
		this.category = category;
		this.postal = postal;
		this.description = description;
		this.GPS = gPS;
		this.tags = tags;
		//this.advert=convertToBitmap(advert);
		this.advert = advert;
		this.facebook = facebook;
		this.twitter = twitter;
		this.google = google;
	}
	

	
	/* Defining setters and getters for class methods*/
	public void setCIN(String CIN){
		this.CIN = CIN;
	}
	public String getCIN(){
		return this.CIN;
	}
	
	public void setName(String name){
		this.Name= name;
	}
	public String getName(){
		return this.Name;
	}

	public void setPhone(String[] phone){
		System.arraycopy(phone, 0, this.PhoneNumber, 0, phone.length);
	}
	public String [] getPhone(){
		return this.PhoneNumber;
	}
	
	public void setWebsite(String website){
		this.website= website;
	}
	public String getWebsite(){
		return this.website;
	}
	
	public void setEmail(String email){
		this.email= email;
	}
	public String getEmail(){
		return this.email;
	}
	
	public void setLocation(String loc){
		this.location= loc;
	}
	public String getLocation(){
		return this.location;
	}
	
	public void setPost(String post){
		this.postal= post;
	}
	public String getPost(){
		return this.postal;
	}
	
	public void setLogo(String logo){
		this.logo= logo;
	}
	public String getLogo(){
		return this.logo;
	}
	
	public void setCategory(String category){
		this.category= category;
	}
	public String getCategory(){
		return this.category;
	}
	
	public void setDescription(String description){
		this.description= description;
	}
	public String getDescription(){
		return this.description;
	}
	
	public void setGPS(String GPS){
		this.GPS= GPS;
	}
	public String getGPS(){
		return this.GPS;
	}
	
	public void setTags(String tags){
		this.tags= tags;
	}
	public String getTags(){
		return this.tags;
	}
	
	public void setAdvert(String advert){
		
		this.advert= advert;
	}
	public String getAdvert(){
		return this.advert;
	}
	
	public void setTwitter(String twitter){
		this.twitter= twitter;
	}
	public String getTwitter(){
		return this.twitter;
	}
	
	public void setFacebook(String facebook){
		this.facebook= facebook;
	}
	public String getFacebook(){
		return this.facebook;
	}

	public void setGoogle(String google){
		this.google= google;
	}
	public String getGoogle(){
	return this.google;
	}
	
	//Other methods for converting stuff and making life easier
	
	public String [] phoneNumberSplitter(String numbers){
		String [] phones =null;
		phones = numbers.split("\\s+");
		
		return phones;
		
	}
	
	public Bitmap convertToBitmap(byte []b ){
		if (b == null){
			return null;
		}
		else{
			ByteArrayInputStream BAIS = new ByteArrayInputStream(b);
			Bitmap fin = BitmapFactory.decodeStream(BAIS);
			return fin;
		}
		
		
	}
	
	
	
}



