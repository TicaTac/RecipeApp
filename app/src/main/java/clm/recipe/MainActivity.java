package clm.recipe;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    SimpleCursorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODONE database helper
        // todone commands
        // todone constants

        // todone listview simple cursor adapter

        refreshList();
        lv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

       // super.onActivityResult(requestCode, resultCode, data);
        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.exitMI :
                Toast.makeText(this,"Exit",Toast.LENGTH_SHORT).show();
                finish();
                break;

            case R.id.add_recipeMI:
                Toast.makeText(this,"Add item",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,addEditActivity.class);

                intent.putExtra(myConstants.DB_ID,myConstants.RC_ADD);
                startActivityForResult(intent,1);

                break;
        }

        return true;
    }

    @Override
    protected void onResume() {
        refreshList();
        super.onResume();

    }

    public void refreshList(){
        lv = (ListView) findViewById(R.id.recipeLV);
        myCommands commands = new myCommands(this);
        Cursor c=commands.getDbQuery();
        String[] from = {myConstants.DB_RNAME,myConstants.DB_RDESC,myConstants.DB_RATING};
        int[] to = {R.id.nameTV,R.id.descTV,R.id.ratingTV};

        adapter = new SimpleCursorAdapter(this,R.layout.recipe_list_item,c,from,to);
        lv.setAdapter(adapter);
    }
}
