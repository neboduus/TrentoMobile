package com.trentomobile.DAO_;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

class DataBaseTransportHelper extends SQLiteOpenHelper {
    private final Context myContext;
    private static final String DATABASE_PATH = "/data/data/com.trentomobile.trentomobile/databases/";
    private static final String DATABASE_NAME = "trasportiTrento.db";
    public static final String TABLE_STOPS = "Stop";
    public static final String COLUMN_ID = "stop_id";
    public static final String COLUMN_NAME = "stop_name";
    public static final String COLUMN_CODE = "stop_code";
    public static final String COLUMN_DESC = "stop_desc";
    public static final String COLUMN_LAT = "stop_lat";
    public static final String COLUMN_LON = "stop_lon";

    public DataBaseTransportHelper(Context context) {
        super(context, "trasportiTrento.db", (CursorFactory)null, 1);
        this.myContext = context;
    }

    public void onCreate(SQLiteDatabase db) {
        boolean dbExist = this.checkDataBase();
        if(!dbExist) {
            this.getReadableDatabase();

            try {
                this.copyDataBase();
            } catch (IOException var4) {
                throw new Error("Error copying database");
            }
        }

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;

        try {
            String myPath = "/data/data/com.trentomobile.trentomobile/databases/trasportiTrento.db";
            checkDB = SQLiteDatabase.openDatabase(myPath, (CursorFactory)null, 1);
        } catch (SQLiteException var3) {

        }

        if(checkDB != null) {
            checkDB.close();
        }

        return checkDB != null;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = this.myContext.getAssets().open("trasportiTrento.db");
        String outFileName = "/data/data/com.trentomobile/databases/trasportiTrento.db";
        FileOutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];

        int length;
        while((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }
}
