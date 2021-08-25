package com.example.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    ToDoAdapter adapter;
    static private final String TAG = "ToDoList";
    static private final int ADD_ITEM_REQUEST_CODE = 1;
    static private String FILE_NAME = "ToDoList.txt";
    private ToDoDatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView listView = (ListView) findViewById(R.id.listView);
        adapter = new ToDoAdapter(new ArrayList<Item>());
        listView.setAdapter(adapter);

        Button addButton = (Button)findViewById(R.id.buttonAddItem);
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i(TAG, "Entered addButton.onClickListener.onClick");
                Intent intent = new Intent(ListActivity.this, AddNewActivity.class);
                startActivityForResult(intent, ADD_ITEM_REQUEST_CODE);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == ADD_ITEM_REQUEST_CODE) {
            Log.i(TAG, "Entered ListActivity.onActivityResult");
            Item item = new Item();
            item.setItem(data.getStringExtra("item"));
            item.setStatus(data.getBooleanExtra("status", false));
            item.setDate(data.getStringExtra("date"));
            item.setTime(data.getStringExtra("time"));
            adapter.add(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.delete:
                adapter.clear();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadItems(){
        dbHelper = new ToDoDatabaseHelper(this);
        Cursor cursor = dbHelper.getWritableDatabase().query(ToDoDatabaseHelper.TABLE_NAME,
                ToDoDatabaseHelper.columns, null, new String[] {}, null, null, null);

        List<Item> data = new ArrayList<Item>();

        while (cursor.moveToNext()){
            Item item = new Item();
            item.setItem(cursor.getString(cursor.getColumnIndex(ToDoDatabaseHelper.COLUMN_NAME_ITEM)));
            item.setStatus((cursor.getInt(cursor.getColumnIndex(ToDoDatabaseHelper.COLUMN_NAME_STATUS))) == 1 ? true : false);
            item.setDate(cursor.getString(cursor.getColumnIndex(ToDoDatabaseHelper.COLUMN_NAME_DATE)));
            item.setTime(cursor.getString(cursor.getColumnIndex(ToDoDatabaseHelper.COLUMN_NAME_TIME)));
            data.add(item);
        }
        adapter.addData(data);
    }


    private void writeItems(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(ToDoDatabaseHelper.TABLE_NAME, null, null);

        List<Item> data = adapter.getData();

        for (Item item : data){
            ContentValues values = new ContentValues();
            values.put(ToDoDatabaseHelper.COLUMN_NAME_ITEM, item.getItem());
            values.put(ToDoDatabaseHelper.COLUMN_NAME_STATUS, item.isStatus() ? 1 : 0);
            values.put(ToDoDatabaseHelper.COLUMN_NAME_DATE, item.getDate());
            values.put(ToDoDatabaseHelper.COLUMN_NAME_TIME, item.getTime());
            db.insert(ToDoDatabaseHelper.TABLE_NAME,null,values);

        }
    }

    @Override
    protected void onPause() {

        super.onPause();
        writeItems();
    }

    @Override
    protected void onResume() {

        super.onResume();
        if (adapter.getCount() == 0){
            loadItems();
        }

    }



}
