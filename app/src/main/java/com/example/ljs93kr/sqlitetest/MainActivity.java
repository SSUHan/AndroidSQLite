package com.example.ljs93kr.sqlitetest;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    DBManager dbManager;

    EditText inputName;
    EditText inputIngredient;
    EditText inputCal;

    EditText deleteName;
    TextView result;

    String tableName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        inputName = (EditText)findViewById(R.id.inputName);
        inputIngredient = (EditText)findViewById(R.id.inputIngredient);
        inputCal = (EditText)findViewById(R.id.inputCal);

        deleteName = (EditText)findViewById(R.id.deleteName);

        result = (TextView)findViewById(R.id.result);

        tableName = DBSupport.EATEN_FOOD_TABLE;

        dbManager = new DBManager(getApplicationContext(),"food.db",null,1);

       result.setText( dbManager.PrintData() );
    }

    public void createClick(View v){
        dbManager.createTable(tableName,"( _id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, ingredient TEXT, calory INTEGER, time datetime);");
        Toast.makeText(getApplicationContext(),"create "+tableName+" complete",Toast.LENGTH_SHORT).show();
    }

    public void insertClick(View v){
        String name = inputName.getText().toString();
        String ingred = inputIngredient.getText().toString();
        String cal = inputCal.getText().toString();
        try{
            dbManager.insert(tableName,name,ingred, cal);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"insert error",Toast.LENGTH_SHORT).show();
        }
        result.setText( dbManager.PrintData() );
    }

    public void deleteClick(View v){
        String name = deleteName.getText().toString();
        dbManager.delete(tableName,name,DBSupport.BY_NAME);
        result.setText( dbManager.PrintData() );
    }

    public void dropClick(View v){
        dbManager.dropTable("drop table if exists "+tableName);
        result.setText(dbManager.PrintData());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
