
package com.example.android.travelagent;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder>  {
    private ArrayList<Hotel> mHotelData;
    private Context mContext;

    HotelAdapter(Context context, ArrayList<Hotel> hotelData) {
        this.mHotelData = hotelData;
        this.mContext = context;
    }

    @Override
    public HotelAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(HotelAdapter.ViewHolder holder,
                                 int position) {

        Hotel currentHotel = mHotelData.get(position);
        holder.bindTo(currentHotel);
    }

    @Override
    public int getItemCount() {
        return mHotelData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView mHotelImage;


        ViewHolder(View itemView) {
            super(itemView);

            mTitleText = itemView.findViewById(R.id.title);
            mInfoText = itemView.findViewById(R.id.subTitle);
            mHotelImage = itemView.findViewById(R.id.hotelImage);

            itemView.setOnClickListener(this);
        }

        void bindTo(Hotel currentHotel){
            mTitleText.setText(currentHotel.getTitle());
            mInfoText.setText(currentHotel.getInfo());
            Glide.with(mContext).load(
                    currentHotel.getImageResource()).into(mHotelImage);
        }

        @Override
        public void onClick(View view) {
            Hotel currentHotel = mHotelData.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, DetailActivity.class);
            detailIntent.putExtra("title", currentHotel.getTitle());
            detailIntent.putExtra("image_resource",
                    currentHotel.getImageResource());
            mContext.startActivity(detailIntent);
        }
    }
}
