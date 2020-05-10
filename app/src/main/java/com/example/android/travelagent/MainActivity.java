package com.example.android.travelagent;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private ArrayList<Hotel> mHotelData;
    private HotelAdapter mAdapter;
    private EditText mEdit;
    private String varos ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mHotelData = new ArrayList<>();
        mAdapter = new HotelAdapter(this, mHotelData);
        mRecyclerView.setAdapter(mAdapter);
        mEdit = (EditText)findViewById(R.id.searchFilter);
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(mEdit.getWindowToken(), 0);
        initializeData();
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper
                .SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                        ItemTouchHelper.DOWN | ItemTouchHelper.UP,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {

                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(mHotelData, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                mHotelData.remove(viewHolder.getAdapterPosition());

                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });


        helper.attachToRecyclerView(mRecyclerView);
    }




    private void initializeData() {

        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(mEdit.getWindowToken(), 0);
        String[] hotelList = getResources()
                .getStringArray(R.array.hotel_titles);
        String[] hotelInfo = getResources()
                .getStringArray(R.array.hotel_info);
        TypedArray hotelImageResources = getResources()
                .obtainTypedArray(R.array.hotel_images);
        String[] hotelCity = getResources()
                .getStringArray(R.array.hotel_city);

        mHotelData.clear();

        for (int i = 0; i < hotelList.length; i++) {
            if(varos.equals("")) {
                mHotelData.add(new Hotel(hotelList[i], hotelInfo[i],
                        hotelImageResources.getResourceId(i, 0), hotelCity[i]));
            }else if(varos.equals(hotelInfo[i])){
                mHotelData.add(new Hotel(hotelList[i], hotelInfo[i],
                        hotelImageResources.getResourceId(i, 0), hotelCity[i]));
            }
        }

        hotelImageResources.recycle();
        mAdapter.notifyDataSetChanged();
    }

    public void resetHotel(View view) {
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(mEdit.getWindowToken(), 0);
        varos =  mEdit.getText().toString();
        initializeData();
    }
}
