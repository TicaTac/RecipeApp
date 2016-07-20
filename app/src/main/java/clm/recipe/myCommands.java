package clm.recipe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by CLM on 20/07/2016.
 */
public class myCommands {
    Context c;
    myDBHelper helper;

    public myCommands(Context c) {
        this.c = c;
        helper=new myDBHelper(c);
    }

    public Cursor getDbQuery(){
        Cursor tempCursor;
        tempCursor=helper.getReadableDatabase().query(myConstants.DB_TABLE,null,null,null,null,null,null);
        return tempCursor;
    }

    public Cursor getDbQuery(String searchTerm)
    {
        Cursor tempCursor;

        tempCursor=helper.getReadableDatabase().rawQuery("SELECT * FROM "
                +myConstants.DB_NAME
                +" WHERE "+myConstants.DB_RNAME
                +" LIKE '%"
                +searchTerm+"%'" , null);

        return tempCursor;


    }

    public Cursor getDbQuery(int dbID)
    {
        Cursor tempCursor;

        tempCursor=helper.getReadableDatabase().rawQuery("SELECT * FROM "
                +myConstants.DB_NAME
                +" WHERE "
                +myConstants.DB_ID
                +" ="
                +dbID+";" , null);

        return tempCursor;


    }

    public void addRecipe(myRecipe recipe)
    {
        ContentValues cv=new ContentValues();
        cv.put(myConstants.DB_RNAME,recipe.recipe_name);
        cv.put(myConstants.DB_RDESC,recipe.recipe_description);
        cv.put(myConstants.DB_RATING,recipe.recipe_rating);


        helper.getWritableDatabase().insert(myConstants.DB_TABLE,null,cv);

    }

    public void updateDB(myRecipe recipe)
    {
        if (recipe.sqlID!=-1){
            ContentValues cv=new ContentValues();
            cv.put(myConstants.DB_RNAME,recipe.recipe_name);
            cv.put(myConstants.DB_RDESC,recipe.recipe_description);
            cv.put(myConstants.DB_RATING,recipe.recipe_rating);

            helper.getWritableDatabase().update(myConstants.DB_TABLE,cv,myConstants.DB_ID,new String[]{""+recipe.sqlID});

        }

    }

    public myRecipe getRecipeFromCursor(Cursor c){
        myRecipe recipe= null;
        if (c.moveToNext()) {
            String name = c.getString(c.getColumnIndex(myConstants.DB_RNAME));
            String desc = c.getString(c.getColumnIndex(myConstants.DB_RDESC));
            int rating = c.getInt(c.getColumnIndex(myConstants.DB_RATING));
            recipe = new myRecipe(name, desc,rating);
        }

        return recipe;
    }


}
