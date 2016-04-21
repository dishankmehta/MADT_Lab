package com.example.dishank.madt_lab;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dishank on 4/12/2016.
 */
public class ShowContent extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private ContentListAdapter adapter;
    private RecyclerView recyclerView;
    private List<DataProviderContent> datalist = new ArrayList<>();
    ContentResolver cr;
    Cursor c;
    Uri uri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_content);

        recyclerView = (RecyclerView) findViewById(R.id.contentlist);


        RecyclerView.LayoutManager rlayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rlayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        getLoaderManager().initLoader(0, null, this);
        adapter = new ContentListAdapter(datalist);
        recyclerView.setAdapter(adapter);

        setdata();

    }



    public void setdata(){
        cr = getApplicationContext().getContentResolver();
        String[] projection = {ProviderFile._ID,ProviderFile.PLAYERNAME,ProviderFile.GOALS};
        c = cr.query(ProviderFile.CONTENT_URI, projection, null, null, null);
        if(c!=null){
            if(c.moveToFirst()){
                do{
                    int col1 = c.getColumnIndex(ProviderFile.PLAYERNAME);
                    int col2 = c.getColumnIndex(ProviderFile.GOALS);
                    String name = c.getString(col1);
                    String goal = c.getString(col2);
                    DataProviderContent data = new DataProviderContent(R.drawable.ic_account_circle_black_24dp,name,goal);
                    datalist.add(data);
                }while(c.moveToNext());
            }
        }else{
            Toast.makeText(getApplicationContext(),"No entry found..!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


}
