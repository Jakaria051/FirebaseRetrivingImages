package com.itiorchid.firebaseretrivingimages.Model;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itiorchid.firebaseretrivingimages.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public ViewHolder(View itemView) {

        super(itemView);
        mView = itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClickListener(view,getAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClickListener(view,getAdapterPosition());
                return true;
            }
        });

    }

    public void details_Informations(Context context,String title,String description,String images)
    {
        TextView mTitletextView = mView.findViewById(R.id.rTitle);
        TextView mDescriptionTextView = mView.findViewById(R.id.rDescription);
        ImageView mImageView = mView.findViewById(R.id.rImageView);

        mTitletextView.setText(title);
        mDescriptionTextView.setText(description);
        Picasso.get().load(images).into(mImageView);
    }

    private ViewHolder.ClickListener mClickListener;

    public interface ClickListener
    {
        void onItemClickListener(View view,int position);
        void onItemLongClickListener(View view,int position);

    }

    public void setOnClickListener(ViewHolder.ClickListener clickListener)
    {
        mClickListener = clickListener;
    }
}
