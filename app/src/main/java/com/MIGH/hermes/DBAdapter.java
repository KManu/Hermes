// ------------------------------------ DBADapter.java ---------------------------------------------

// TODO: Change the package to match your project.
package com.MIGH.hermes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;








import com.MIGH.hermes.CompanyModel;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

// Search for "TODO", and make the appropriate changes.
public class DBAdapter {

	/////////////////////////////////////////////////////////////////////
	//	Constants & Data
	/////////////////////////////////////////////////////////////////////
	// For logging:
	private static final String LOG = "DBAdapter";
	
	// DB Fields
	public static final String KEY_CIN = "IdentificationNumber"; // The company identification number. Doubles as Row index. 
	public static final int COL_ROWID = 0;  
	/*
	 * CHANGE 1:
	 */
	// TODO: Setup your fields here. They must match the Company Data model. Both names and order
	public static final String KEY_NAME = "Name";
	public static final String KEY_PHONENUMBER = "PhoneNumber";
	public static final String KEY_LOCATION ="Location";
	public static final String KEY_LOGO= "Logo";
	public static final String KEY_WEBSITE = "Website";
	public static final String KEY_EMAIL = "Email";
	public static final String KEY_CATEGORY="Category";
	public static final String KEY_POSTAL ="Postal";
	public static final String KEY_DESCRIPTION = "Description";
	public static final String KEY_GPS = "GPS";
	public static final String KEY_TAGS="Tags";
	public static final String KEY_ADVERT="Advert";
	public static final String KEY_FACEBOOK ="Facebook";
	public static final String KEY_TWITTER ="Twitter";
	public static final String KEY_GOOGLE = "Google";
	
	
	
	// TODO: Setup your field numbers here (0 = KEY_CIN, 1=...)
	public static final int COL_NAME = 1;
	public static final int COL_PHONENUMBER = 2;
	public static final int COL_LOCATION = 3;
	public static final int COL_LOGO = 4;
	public static final int COL_WEBSITE = 5;
	public static final int COL_EMAIL = 6;
	public static final int COL_CATEGORY = 7;
	public static final int COL_POSTAL = 8;
	public static final int COL_DESCRIPTION = 9;
	public static final int COL_GPS = 10;
	public static final int COL_TAGS = 11;
	public static final int COL_ADVERT = 12;
	public static final int COL_FACEBOOK = 13;
	public static final int COL_TWITTER = 14;
	public static final int COL_GOOGLE = 15;
	
	
	public static final String[] ALL_KEYS = new String[] {
		KEY_CIN,
		KEY_NAME,
		KEY_PHONENUMBER,
		KEY_LOCATION, 
		KEY_LOGO,
		KEY_WEBSITE,
		KEY_EMAIL,
		KEY_CATEGORY,
		KEY_POSTAL,
		KEY_DESCRIPTION,
		KEY_GPS,
		KEY_TAGS,
		KEY_ADVERT,
		KEY_FACEBOOK,
		KEY_TWITTER,
		KEY_GOOGLE
		};
	
	// DB info: it's name, and the table we are using (just one).
	public static final String DATABASE_NAME = "CompanyDB";
	public static final String DATABASE_TABLE = "CompanyInformation";
	// Track DB version if a new version of your app changes the format.
	//public static final int DATABASE_VERSION = 1;	
	
	private static final String DATABASE_CREATE_SQL = 
			"create table " + DATABASE_TABLE 
			+ " (" + KEY_CIN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			
			/*
			 * CHANGE 2:
			 */
			// TODO: Place your fields here!
			// + KEY_{...} + " {type} not null"
			//	- Key is the column name you created above.
			//	- {type} is one of: text, integer, real, blob
			//		(http://www.sqlite.org/datatype3.html)
			//  - "not null" means it is a required field (must be given a value).
			// NOTE: All must be comma separated (end of line!) Last one must have NO comma!!
			+ KEY_NAME + " TEXT NOT NULL, "
			+ KEY_PHONENUMBER + " TEXT NOT NULL, "
			+ KEY_LOCATION + "TEXT NOT NULL,"
			+ KEY_LOGO + "TEXT NOT NULL, "
			+ KEY_WEBSITE + " TEXT NOT NULL DEFAULT 'None Available', "
			+ KEY_EMAIL +"TEXT NOT NULL DEFAULT 'None Available', "
			+ KEY_CATEGORY + "TEXT NOT NULL, "
			+ KEY_POSTAL +"TEXT DEFAULT 'None Available', "
			+ KEY_DESCRIPTION + "TEXT DEFAULT 'None Available', "
			+ KEY_GPS + "TEXT, "
			+ KEY_TAGS + "TEXT, "
			+ KEY_ADVERT + "TEXT, "
			+ KEY_FACEBOOK + "TEXT DEFAULT 'None Available', "
			+ KEY_TWITTER + "TEXT DEFAULT 'None Available', "		
			+ KEY_GOOGLE + "TEXT DEFAULT 'None Available' "
			// Rest  of creation:
			+ ");";
	
	// Context of application who uses us.
	private final Context context;
	
	private DatabaseHelper myDBHelper;
	private SQLiteDatabase db;

	/////////////////////////////////////////////////////////////////////
	//	Public methods:
	/////////////////////////////////////////////////////////////////////
	
	//Constructor 
	public DBAdapter(Context ctx) {
		this.context = ctx;
		myDBHelper = new DatabaseHelper(context);
	}
	
	// Open the database connection.
				// For the web adapter
	public DBAdapter open() {
		db = myDBHelper.getWritableDatabase();
		return this;
	}
	

	
	// Close the database connection.
	public void close() {
		myDBHelper.close();
	}
	
	// Add a new set of values to the database. This method would be used by the web sync adapter. 
	//#TODO UPDATE TO REFLECT CURRENT COMPANY MODEL DESIGN
	public long insertRow(CompanyModel model) {
		
		String CIN = model.getCIN();
		
		String name= model.getName();
		
		String phoneNumber=""; 
		for(int i =0; i < model.getPhone().length; i++){
			phoneNumber = model.getPhone()[i]; // cos the phoneNumbers are returned in an array
		}
		
		String location= model.getLocation();
		
		String logo = model.getLogo();
		
		String website= model.getWebsite();
		String email= model.getEmail();
		String category=model.getCategory();
		String postal= model.getPost();
		String description= model.getDescription();
		String gps= model.getGPS();
		String tags=model.getTags();
		
		String advert = model.getAdvert();
		
//		byte [] advert=null;
//		BAOS.reset();
//		model.getAdvert().compress(Bitmap.CompressFormat.PNG, 90, BAOS);
//		advert= BAOS.toByteArray();
		
		String facebook = model.getFacebook();
		String twitter= model.getTwitter();
		String google= model.getGoogle();
		
		
		
		// Create row's data:
		// ContentValues is a key-value data structure. KEY are the keys, and the corresponding values are besides. 
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_CIN, CIN);
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_PHONENUMBER, phoneNumber);
		initialValues.put(KEY_LOCATION, location);
		initialValues.put(KEY_LOGO, logo);
		initialValues.put(KEY_WEBSITE, website);
		initialValues.put(KEY_EMAIL, email);
		initialValues.put(KEY_CATEGORY, category);
		initialValues.put(KEY_POSTAL, postal);
		initialValues.put(KEY_DESCRIPTION, description);
		initialValues.put(KEY_GPS, gps);
		initialValues.put(KEY_TAGS, tags);
		initialValues.put(KEY_ADVERT, advert);
		initialValues.put(KEY_FACEBOOK, facebook);
		initialValues.put(KEY_TWITTER,twitter);
		initialValues.put(KEY_GOOGLE, google);
		
		
		
		// Insert it into the database.
		return db.insert(DATABASE_TABLE, null, initialValues);
	}
	
	// Delete a row from the database, by CIN (primary key)
	public boolean deleteRow(String rowId) {
		String where = KEY_CIN + "=" + rowId;
		return db.delete(DATABASE_TABLE, where, null) != 0;
	}
	
	public void deleteAll() {
		Cursor c = setCursorOverAll();
		int columnIndex = c.getColumnIndexOrThrow(KEY_CIN);
		if (c.moveToFirst()) {
			do {
				// Gets the String at the column index, which is the company id.
				deleteRow(c.getString(columnIndex));				
			} while (c.moveToNext());
		}
		c.close();
	}
	
	//This method gives a cursor maximum scope on all entries. used to delete all
	private Cursor setCursorOverAll(){
		String where = null;
		Cursor b = 	db.query(true, DATABASE_TABLE, ALL_KEYS, 
							where, null, null, null, null, null);
		if (b != null) {
			b.moveToFirst();
		}
		return b;
	}
	
	// Return all data in the database.
	public ArrayList<CompanyModel> getAllRows() {
		ArrayList<CompanyModel> AllCompanies = new ArrayList<CompanyModel>(); 
		String where = null;
		Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS, 
							where, null, null, null, null, null);
		
		if (c.moveToFirst()){
			do{
				// Create a model of the company and fill it with db info
				CompanyModel comp = new CompanyModel(
						c.getString(COL_ROWID),
						c.getString(COL_NAME),
						c.getString(COL_PHONENUMBER),
						c.getString(COL_LOCATION),
						c.getString(COL_LOGO),
						c.getString(COL_WEBSITE),
						c.getString(COL_EMAIL),
						c.getString(COL_CATEGORY),
						c.getString(COL_POSTAL),
						c.getString(COL_DESCRIPTION),
						c.getString(COL_GPS),
						c.getString(COL_TAGS),
						c.getString(COL_ADVERT),
						c.getString(COL_FACEBOOK),
						c.getString(COL_TWITTER),
						c.getString(COL_GOOGLE)
						);
				AllCompanies.add(comp);
			}
			while(c.moveToNext());
		}
		
		
		return AllCompanies;
	}
	
	// Search for row based on word passed.
	public ArrayList<CompanyModel> searchRow(String query){
		ArrayList<CompanyModel> searchResults = new ArrayList<CompanyModel>();
		//String [] Searchable ={KEY_NAME, KEY_CATEGORY, KEY_TAGS, KEY_LOCATION, KEY_DESCRIPTION};
		String where = KEY_NAME+ " LIKE '%"+ query +"%' "+ " OR " 
					  +KEY_CATEGORY+ " LIKE '%"+ query +"%' "+ " OR " 
					  +KEY_TAGS+ " LIKE '%"+ query +"%' "+ " OR "
					  +KEY_LOCATION+ " LIKE '%"+ query +"%' "+ " OR "
					  +KEY_DESCRIPTION+ " LIKE '%"+ query +"%'  ;";
		
		//where = KEY_NAME + " LIKE " + query +" ; ";
		Cursor c = db.query(true, DATABASE_TABLE,ALL_KEYS,
					where, null, null, null, null, null );
		
		if (c.moveToFirst()){
			do{
				// Create a model of the company and fill it with db info
				CompanyModel comp = new CompanyModel(
						c.getString(COL_ROWID),
						c.getString(COL_NAME),
						c.getString(COL_PHONENUMBER),
						c.getString(COL_LOCATION),
						c.getString(COL_LOGO),
						c.getString(COL_WEBSITE),
						c.getString(COL_EMAIL),
						c.getString(COL_CATEGORY),
						c.getString(COL_POSTAL),
						c.getString(COL_DESCRIPTION),
						c.getString(COL_GPS),
						c.getString(COL_TAGS),
						c.getString(COL_ADVERT),
						c.getString(COL_FACEBOOK),
						c.getString(COL_TWITTER),
						c.getString(COL_GOOGLE)
						);
				searchResults.add(comp);
			}
			while(c.moveToNext());
		}
		
		return searchResults;
				
	}

	// Get a specific row (by rowId) rowId is returned when an insert is performed. That is how you get it to pass here
	public CompanyModel getRow(String rowId) {
		String where = KEY_CIN + "=" + "'"+ rowId+"'";
		Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS, 
						where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		CompanyModel takeBack = new CompanyModel(
				c.getString(COL_ROWID),
				c.getString(COL_NAME),
				c.getString(COL_PHONENUMBER),
				c.getString(COL_LOCATION),
				c.getString(COL_LOGO),
				c.getString(COL_WEBSITE),
				c.getString(COL_EMAIL),
				c.getString(COL_CATEGORY),
				c.getString(COL_POSTAL),
				c.getString(COL_DESCRIPTION),
				c.getString(COL_GPS),
				c.getString(COL_TAGS),
				c.getString(COL_ADVERT),
				c.getString(COL_FACEBOOK),
				c.getString(COL_TWITTER),
				c.getString(COL_GOOGLE)
				);
		
		return takeBack;
	}
	
	// Change an existing row to be equal to new data.
	public boolean updateRow(
			String rowId, 
			String name,
			String phonenumber,
			String location,
			String logo,
			String website,
			String email,
			String category,
			String postal,
			String description,
			String gps,
			String tags,
			String advert,
			String facebook,
			String twitter,			
			String google
			) {
		String where = KEY_CIN + "=" + rowId;

		/*
		 * CHANGE 4:
		 */
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_CIN, rowId);
		newValues.put(KEY_NAME, name);
		newValues.put(KEY_PHONENUMBER, phonenumber);
		newValues.put(KEY_LOCATION, location);
		newValues.put(KEY_LOGO, logo);
		newValues.put(KEY_WEBSITE, website);
		newValues.put(KEY_EMAIL, email);
		newValues.put(KEY_CATEGORY, category);
		newValues.put(KEY_POSTAL, postal);
		newValues.put(KEY_DESCRIPTION, description);
		newValues.put(KEY_GPS, gps);
		newValues.put(KEY_TAGS, tags);
		newValues.put(KEY_ADVERT, advert);
		newValues.put(KEY_FACEBOOK, facebook);
		newValues.put(KEY_TWITTER, twitter);
		newValues.put(KEY_GOOGLE, google);
		
		
		// Insert it into the database.
		return db.update(DATABASE_TABLE, newValues, where, null) != 0;
	}
	
	
	
	/////////////////////////////////////////////////////////////////////
	//	Private Helper Classes:
	/////////////////////////////////////////////////////////////////////
	
	/**
	 * Private class which handles database creation and upgrading.
	 * Used to handle low-level database access.
	 */
	
	/*
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase _db) {
			_db.execSQL(DATABASE_CREATE_SQL);			
		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
			Log.w(LOG, "Upgrading application's database from version " + oldVersion
					+ " to " + newVersion + ", which will destroy all old data!");
			
			// Destroy old database:
			_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			
			// Recreate new database:
			onCreate(_db);
		}
	} 
	*/
	
	
	
	
	/*
	 * SQLite AssetHelper class implementation. Its an alternative to the SQLiteOpenHelper
	 */
	public class DatabaseHelper extends SQLiteAssetHelper {
		
		private static final String DATABASE_FILE_NAME = "TESTDB_DATA.db";
		private static final int DATABASE_VERSION =2;

		
		@Override
		public void setForcedUpgrade() {
			// TODO Auto-generated method stub
			super.setForcedUpgrade();
			
		}
		
		public DatabaseHelper(Context context) {
			
			super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
			setForcedUpgrade();
		}
		
		

	}

	
}















