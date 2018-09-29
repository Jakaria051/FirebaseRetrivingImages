package com.itiorchid.firebaseretrivingimages;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.itiorchid.firebaseretrivingimages.Model.ModeIClass;
import com.itiorchid.firebaseretrivingimages.Model.ViewHolder;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    FirebaseDatabase mFireBaseDatabase;
    DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Posts Activity");

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerviewid);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    
        mFireBaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFireBaseDatabase.getReference("Data");

    }



    private void firebaseSearch(String searchText)
    {
        Query firebaseSearchQuery = mDatabaseReference.orderByChild("title").startAt(searchText).endAt(searchText  + "\uf8ff");

        FirebaseRecyclerAdapter <ModeIClass,ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<ModeIClass, ViewHolder>(
                        ModeIClass.class,
                        R.layout.raw,
                        ViewHolder.class,
                        firebaseSearchQuery) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, ModeIClass model, int position) {

                        viewHolder.details_Informations(getApplicationContext(),model.getTitle(),model.getDescription(),model.getImage());
                    }
                };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      

        getMenuInflater().inflate(R.menu.manu,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                firebaseSearch(query);
                
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

              
                firebaseSearch(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_setting)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
