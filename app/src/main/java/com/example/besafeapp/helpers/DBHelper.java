package com.example.besafeapp.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "BeSafe.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE UserDetails(phoneNo TEXT primary key, name TEXT, password TEXT, sosNumber INTEGER, " +
                "discrete TEXT, emergNo1 INTEGER, emergNo2 TEXT, emergNo3 TEXT, emergNo4 TEXT, totalEmergNo TEXT)");
        DB.execSQL("CREATE TABLE LikedHelplines(helplineNo TEXT NOT NULL, name TEXT, phoneNo TEXT NOT NULL, FOREIGN KEY (phoneNo) REFERENCES UserDetails(phoneNo), CONSTRAINT h_pk PRIMARY KEY(helplineNo, phoneNo))");
        DB.execSQL("INSERT INTO UserDetails VALUES('9840112345','Supraja','password',100,'Enabled','0','0','0','0',0 )");
        DB.execSQL("INSERT INTO UserDetails VALUES('1020304050','Shruthi','password',123,'Enabled','0','0','0','0',0 )");
//        DB.execSQL("INSERT INTO LikedHelplines VALUES('100','Police','1020304050')");
//        DB.execSQL("INSERT INTO LikedHelplines VALUES('108','Ambulance','1020304050')");
//        DB.execSQL("INSERT INTO LikedHelplines VALUES('100','Police','9840112345')");
        DB.execSQL("CREATE TABLE NearbySafePlaces(locationName TEXT PRIMARY KEY, latitude TEXT, longitude TEXT, safePlace1 TEXT,safePlace2 TEXT,safePlace3 TEXT,safePlace4 TEXT, safePlace5 TEXT)");
        DB.execSQL("CREATE TABLE NamesPhno(nid INTEGER primary key autoincrement, callerName1 TEXT, callerName2 TEXT, callerName3 TEXT, callerName4 TEXT, phoneNo TEXT references UserDetails(phoneNo))");

        DB.execSQL("INSERT INTO NearbySafePlaces VALUES('SSN College of Engineering','12.7517','80.2033','Chettinad Super Speciality Hospital, Chennai, Tamil Nadu','praveena hospital kelambakkam','DILIP MULTISPECIALITY HOSPITAL, Rajiv Gandhi IT Expressway, Thiruporur, Tamil Nadu','Kelambakkam Police Station','Sathyabama General Hospital')");
        DB.execSQL("INSERT INTO NearbySafePlaces VALUES('Express Avenue, Pattullos Road, Express Estate, Thousand Lights, Chennai, Tamil Nadu','13.0585','80.2642','The Crescent Hospital, Doctor Besant Road, Ice House, Mirsahibpet, Royapettah, Chennai, Tamil Nadu','MYSAAN HOSPITAL, Thayar Sahib Street, Mount Road, Border Thottam, Padupakkam, Triplicane, Chennai, Tamil Nadu','Laksha Hospital (A Multi speciality hospital)','E2 Police Station, Gowdiamutt Road, Azad Nagar, Royapettah, Chennai, Tamil Nadu','F4 Thousand Lights Police Station, Greams Road, Thousand Lights East, Thousand Lights, Chennai, Tamil Nadu')");
        DB.execSQL("INSERT INTO NearbySafePlaces VALUES('Sathyabama Institute of Science and Technology','12.8729','80.2261','Sathyabama General Hospital','SRI SUGAM HOSPITAL, Sholinganallur','J-10 Semmenchery Police Station, Rajiv Gandhi Salai, Ezhil Nagar, Sholinganallur, Chennai, Tamil Nadu','semmancheri police station, Semmancheri, Arasankalani, Sithalapakkam, Chennai, Tamil Nadu','Laksha Hospital (A Multi speciality hospital)')");
        DB.execSQL("INSERT INTO NearbySafePlaces VALUES('Muttukadu Boat House, Muthukadu, Tamil Nadu','12.8270','80.2403','Paraprofessional Institute of Aquaculture Technology, Tamil Nadu Dr J Jayalalithaa Fisheries University','MUTTUKADU POLICE OUT POST, Muthukadu, Tamil Nadu','J-12 Kaanathur, Police Station, Muthukadu, Tamil Nadu','praveena hospital kelambakkam','DakshinaChitra Heritage Museum, State Highway 49, Muthukadu, Tamil Nadu')");
        DB.execSQL("INSERT INTO NearbySafePlaces VALUES('Holiday Inn Express Chennai Omr Thoraipakkam, an IHG Hotel','12.930476','80.231302','Nanthini Hospitals, Old Mahabalipuram Road, Chandrasekhar Avenue, Thoraipakkam, Tamil Nadu','Cloudnine Hospital - Old Mahabalipuram Road, Rajiv Gandhi Salai, Mettukuppam, Thoraipakkam, Tamil Nadu','J-9 Thuraipakkam Police Booth, Pillaiyar Koil Street, Jothi Nagar, Thoraipakkam, Tamil Nadu','J-10 Semmenchery Police Station, Rajiv Gandhi Salai, Ezhil Nagar, Sholinganallur, Chennai, Tamil Nadu','Taj Wellington Mews, Chennai')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP Table if exists UserDetails");
        DB.execSQL("DROP Table if exists LikedHelplines");
        DB.execSQL("DROP Table if exists NamesPhoneNo");
    }

    public Boolean insertProduct(String locationName, String latitude, String longitude, String safePlace1, String safePlace2, String safePlace3, String safePlace4, String safePlace5){
        //get database connection
        SQLiteDatabase DB = this.getWritableDatabase();

        //Write content into DB
        ContentValues contentValues = new ContentValues();
        contentValues.put("locationName", locationName);
        contentValues.put("latitude", latitude);
        contentValues.put("longitude", longitude);
        contentValues.put("safePlace1", safePlace1);
        contentValues.put("safePlace2", safePlace2);
        contentValues.put("safePlace3", safePlace3);
        contentValues.put("safePlace4", safePlace4);
        contentValues.put("safePlace5", safePlace5);

        //execute the insert query
        Long result = DB.insert("NearbySafePlaces", null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Boolean deleteProduct(String locationName){
        //get database connection
        SQLiteDatabase DB = this.getWritableDatabase();

        //Find record with the ID
        Cursor findRecord = DB.rawQuery("SELECT * FROM NearbySafePlaces WHERE locationName = ?",new String[]{String.valueOf(locationName)});
        if(findRecord.getCount() >0){
            //Delete record
            int result = DB.delete("NearbySafePlaces","locationName = ?",new String[]{String.valueOf(locationName)});
            if(result == -1)
                return false;
            else
                return true;
        }
        else
            return false;
    }

    public Cursor retrievePlace(String locationName){
        //get database connection
        SQLiteDatabase DB = this.getWritableDatabase();

        //Find record with the ID
        Cursor findRecord = DB.rawQuery("SELECT * FROM NearbySafePlaces WHERE locationName = ?",new String[]{String.valueOf(locationName)});
        return findRecord;
    }

    public Boolean insertUser(String phone, String name, String password){
        //get database connection
        SQLiteDatabase DB = this.getWritableDatabase();

        //Write content into DB
        ContentValues contentValues = new ContentValues();
        contentValues.put("phoneNo", phone);
        contentValues.put("name", name);
        contentValues.put("password", password);
        contentValues.put("sosNumber", 100);
        contentValues.put("discrete", "disabled");
        contentValues.put("emergNo1", 0);
        contentValues.put("emergNo2", 0);
        contentValues.put("emergNo3", 0);
        contentValues.put("emergNo4", 0);
        contentValues.put("totalEmergNo", 0);

        //execute the insert query
        Long result = DB.insert("UserDetails", null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Boolean deleteEmergNo(String phone, String emerNo){
        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        Cursor findRecord = DB.rawQuery("SELECT * FROM UserDetails WHERE phoneNo = ? AND emergNo1 = ?", new String[]{phone, emerNo});
        if(findRecord.getCount()>0){

            contentValues.put("emergNo1", 0);
            contentValues.put("totalEmergNo", decTotal(phone));
            int result = DB.update("UserDetails", contentValues, "phoneNo = ?",new String[]{String.valueOf(phone)});
        }

        findRecord = DB.rawQuery("SELECT * FROM UserDetails WHERE phoneNo = ? AND emergNo2 = ?", new String[]{phone, emerNo});
        if(findRecord.getCount()>0){

            contentValues.put("emergNo2", 0);
            contentValues.put("totalEmergNo", decTotal(phone));
            int result = DB.update("UserDetails", contentValues, "phoneNo = ?",new String[]{String.valueOf(phone)});
        }

        findRecord = DB.rawQuery("SELECT * FROM UserDetails WHERE phoneNo = ? AND emergNo3 = ?", new String[]{phone, emerNo});
        if(findRecord.getCount()>0){

            contentValues.put("emergNo3", 0);
            contentValues.put("totalEmergNo", decTotal(phone));
            int result = DB.update("UserDetails", contentValues, "phoneNo = ?",new String[]{String.valueOf(phone)});
        }

        findRecord = DB.rawQuery("SELECT * FROM UserDetails WHERE phoneNo = ? AND emergNo4 = ?", new String[]{phone, emerNo});
        if(findRecord.getCount()>0){

            contentValues.put("emergNo4", 0);
            contentValues.put("totalEmergNo", decTotal(phone));
            int result = DB.update("UserDetails", contentValues, "phoneNo = ?",new String[]{String.valueOf(phone)});
        }

        return true;

    }

    public int decTotal(String phone){
        SQLiteDatabase DB = this.getWritableDatabase();
        int newTotal = 0;
        Cursor find = DB.rawQuery("SELECT * FROM UserDetails WHERE phoneNo=?", new String[]{phone});
        while(find.moveToNext()){
           newTotal = find.getInt(9)-1;
        }
        return newTotal;
    }


    public Boolean updatePassword(String phone, String password){
        //get database connection
        SQLiteDatabase DB = this.getWritableDatabase();

        //Write content into DB
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);

        //Find record with the ID
        Cursor currentRecord = DB.rawQuery("SELECT * FROM UserDetails WHERE phoneNo = ?",new String[]{String.valueOf(phone)});
        if(currentRecord.getCount() >0){
            //Update record if record exist
            int result = DB.update("UserDetails", contentValues, "phoneNo = ?",new String[]{String.valueOf(phone)});
            if(result == -1)
                return false;
            else
                return true;
        }
        else
            return false;
    }

    //update sosNumber
    public Boolean updateSosNumber(String phone, String sosNumber){
        //get database connection
        SQLiteDatabase DB = this.getWritableDatabase();

        //Write content into DB
        ContentValues contentValues = new ContentValues();
        contentValues.put("sosNumber", sosNumber);

        //Find record with the ID
        Cursor currentRecord = DB.rawQuery("SELECT * FROM UserDetails WHERE phoneNo = ?",new String[]{String.valueOf(phone)});
        if(currentRecord.getCount() >0){
            //Update record if record exist
            int result = DB.update("UserDetails", contentValues, "phoneNo = ?",new String[]{String.valueOf(phone)});
            if(result == -1)
                return false;
            else
                return true;
        }
        else
            return false;
    }

    public Boolean updateName(String phone, String name){
        //get database connection
        SQLiteDatabase DB = this.getWritableDatabase();

        //Write content into DB
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);

        //Find record with the ID
        Cursor currentRecord = DB.rawQuery("SELECT * FROM UserDetails WHERE phoneNo = ?",new String[]{String.valueOf(phone)});
        if(currentRecord.getCount() >0){
            //Update record if record exist
            int result = DB.update("UserDetails", contentValues, "phoneNo = ?",new String[]{String.valueOf(phone)});
            if(result == -1)
                return false;
            else
                return true;
        }
        else
            return false;
    }

    public void addSOS(String phno, int sos) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues nv = new ContentValues();
        nv.put("sosNumber", sos);
        DB.update("UserDetails", nv, "phoneNo = '" + phno + "'", null);

    }

    public void insertNames(String phno) {

        // TODO hardcoded

        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor findName = DB.rawQuery("SELECT * FROM NamesPhno where phoneNo = ?", new String[]{phno});

        if(findName != null && findName.getCount() == 0) {
            ContentValues nv = new ContentValues();
            nv.put("callerName1", "");
            nv.put("callerName2", "");
            nv.put("callerName3", "");
            nv.put("callerName4", "");
            nv.put("phoneNo", phno);

            DB.insert("NamesPhno", null, nv);
        }
    }

    public void insertPhno(String phno, String emg, String name) {

        SQLiteDatabase DB = this.getWritableDatabase();

        // TODO : remove hard coded number

        Cursor findRecord = DB.rawQuery("SELECT * FROM UserDetails WHERE phoneNo = ?", new String[]{phno});
        String emg1, emg2, emg3, emg4;
        int count = 0;

        while (findRecord.moveToNext()) {
            emg1 = findRecord.getString(5);
            if(emg1.equals("0")) {
                count = 1;
                ContentValues values = new ContentValues();
                values.put("emergNo1", emg);
                DB.update("UserDetails", values, "phoneNo = ?", new String[]{phno});

                ContentValues nv = new ContentValues();
                nv.put("callerName1", name);
                DB.update("NamesPhno", nv, "phoneNo = ?", new String[]{phno});

                break;
            }
            emg2 = findRecord.getString(6);
            if(emg2.equals("0")) {
                count = 2;
                ContentValues values = new ContentValues();
                values.put("emergNo2", emg);
                DB.update("UserDetails", values, "phoneNo = ?", new String[]{phno});

                ContentValues nv = new ContentValues();
                nv.put("callerName2", name);
                DB.update("NamesPhno", nv, "phoneNo = ?", new String[]{phno});

                break;
            }
            emg3 = findRecord.getString(7);
            if(emg3.equals("0")) {
                count = 3;
                ContentValues values = new ContentValues();
                values.put("emergNo3", emg);
                DB.update("UserDetails", values, "phoneNo = ?", new String[]{phno});

                ContentValues nv = new ContentValues();
                nv.put("callerName3", name);
                DB.update("NamesPhno", nv, "phoneNo = ?", new String[]{phno});

                break;
            }
            emg4 = findRecord.getString(8);
            if(emg4.equals("0")) {
                count = 4;
                ContentValues values = new ContentValues();
                values.put("emergNo4", emg);
                DB.update("UserDetails", values, "phoneNo = ?", new String[]{phno});

                ContentValues nv = new ContentValues();
                nv.put("callerName4", name);
                DB.update("NamesPhno", nv, "phoneNo = ?", new String[]{phno});

                break;
            }
        }
        ContentValues values = new ContentValues();
        values.put("totalEmergNo", count);
        DB.update("UserDetails", values, "phoneNo = ?", new String[]{phno});
    }

    public int getTotal(String phno) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor find = DB.rawQuery("SELECT * FROM UserDetails where phoneNo = ?",  new String[]{phno});
        int total = 0;

        while(find != null && find.moveToNext()) {
            total = find.getInt(9);
        }
        return total;
    }


    // TODO hardcoded
    public int retrieveSosNumber(String phno) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor find = DB.rawQuery("SELECT * FROM UserDetails where phoneNo = ?", new String[]{phno});

        int val = 0;

        while (find != null && find.moveToNext()) {
            val = find.getInt(3);
        }
        return val;
    }


    //add emergNo
    //remove emergNo

    public Boolean updateDiscrete(String phone, String discrete){
        //get database connection
        SQLiteDatabase DB = this.getWritableDatabase();

        //Write content into DB
        ContentValues contentValues = new ContentValues();
        contentValues.put("discrete", discrete);

        //Find record with the ID
        Cursor currentRecord = DB.rawQuery("SELECT * FROM UserDetails WHERE phoneNo = ?",new String[]{String.valueOf(phone)});
        if(currentRecord.getCount() >0){
            //Update record if record exist
            int result = DB.update("UserDetails", contentValues, "phoneNo = ?",new String[]{String.valueOf(phone)});
            if(result == -1)
                return false;
            else
                return true;
        }
        else
            return false;
    }


    public Cursor retrieveUser(String phone){
        //get database connection
        SQLiteDatabase DB = this.getWritableDatabase();

        //Find record with the ID
        Cursor findRecord = DB.rawQuery("SELECT * FROM UserDetails WHERE phoneNo = ?",new String[]{phone});
        return findRecord;
    }

    public Boolean isOldUser(String phone){
        SQLiteDatabase DB = this.getWritableDatabase();
        //Find record with the ID
        Cursor findRecord = DB.rawQuery("SELECT * FROM UserDetails WHERE phoneNo = ?",new String[]{phone});

        if(findRecord.getCount()==1) return true;
        else return false;
    }

    public String getPassword(String phone){
        //get database connection
        SQLiteDatabase DB = this.getWritableDatabase();

        //Find record with the ID
        Cursor findRecord = DB.rawQuery("SELECT password FROM UserDetails WHERE phoneNo = ?",new String[]{phone});
        if(findRecord.getCount()>0){
            while(findRecord.moveToNext()){
                return findRecord.getString(0);
            }
        }
        return null;
    }

    public Cursor retrieveAllUsers(){
        //get database connection
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor findAllRecords = DB.rawQuery("SELECT * FROM UserDetails", null);
        return findAllRecords;
    }

    public Cursor getLikedHelplines(String phone){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor liked = DB.rawQuery("SELECT * FROM LikedHelplines WHERE phoneNo = ? ", new String[]{phone});
        return liked;
    }

    public Cursor retrieveNames(String phone){
        //get database connection
        SQLiteDatabase DB = this.getWritableDatabase();

        //Find record with the ID
        Cursor findRecord = DB.rawQuery("SELECT * FROM NamesPhno WHERE phoneNo = ?",new String[]{phone});
        return findRecord;
    }

    public Cursor retrieveAllPlaces(){
        //get database connection
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor findAllRecords = DB.rawQuery("SELECT * FROM NearbySafePlaces", null);
        return findAllRecords;
    }
}

