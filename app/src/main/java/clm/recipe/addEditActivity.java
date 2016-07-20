package clm.recipe;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class addEditActivity extends AppCompatActivity {
    Intent intent;
    Cursor cursor;
    EditText nameET;
    EditText descET;
    RatingBar ratingBAR;
    myRecipe recipe;


    int requestCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        intent=getIntent();
        requestCode=Integer.getInteger(intent.getStringExtra(myConstants.DB_ID),-1);

        nameET=(EditText) findViewById(R.id.recipenameET);
        descET=(EditText) findViewById(R.id.recipeDescET);
        ratingBAR=(RatingBar) findViewById(R.id.ratingBAR);

        myCommands commands=new myCommands(this);
        if (requestCode!=-1) {
            // load recipe to EditText boxes
            cursor= commands.getDbQuery(requestCode);
            recipe=commands.getRecipeFromCursor(cursor);
            nameET.setText(recipe.recipe_name);
            descET.setText(recipe.recipe_description);
            ratingBAR.setNumStars(recipe.recipe_rating);
        }

        Button saveBTN =(Button) findViewById(R.id.saveBTN);





        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (requestCode==-1){ // add a new Record
                    myCommands commands = new myCommands(addEditActivity.this);

                    RatingBar ratingBAR=(RatingBar) findViewById(R.id.ratingBAR);

                    myRecipe recipe=new myRecipe(nameET.getText().toString(),descET.getText().toString(),ratingBAR.getNumStars());

                    commands.addRecipe(recipe);
                    setResult(myConstants.OK);


                }
                finish();


            }
        });


    }
}
