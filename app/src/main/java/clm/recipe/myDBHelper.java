package clm.recipe;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CLM on 20/07/2016.
 */
public class myDBHelper extends SQLiteOpenHelper{
    public myDBHelper(Context context) {
        super(context, myConstants.DB_NAME, null, myConstants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createCommand="CREATE TABLE "
                +myConstants.DB_TABLE+" ("
                +myConstants.DB_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +myConstants.DB_RNAME+" TEXT,"
                +myConstants.DB_RDESC+" TEXT,"
                +myConstants.DB_RATING+" INTEGER );";
        db.execSQL(createCommand);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
