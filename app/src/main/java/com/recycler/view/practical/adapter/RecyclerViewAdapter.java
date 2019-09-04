package com.recycler.view.practical.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.recycler.view.practical.R;
import com.recycler.view.practical.model.Food;
import java.util.ArrayList;

public class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>
{
    private Context activity_context;
    private ArrayList<Food> recyclerViewItemModelArrayList;

    public RecyclerViewAdapter(Context context)
    {
        this.activity_context = context;
    }

    public void feedData(ArrayList<Food> recyclerViewItemModelArrayList)
    {
        this.recyclerViewItemModelArrayList = recyclerViewItemModelArrayList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(activity_context).inflate(R.layout.recycler_view_item_row, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, final int position) {

        Food currentItem = recyclerViewItemModelArrayList.get(position);
        ((ItemViewHolder)itemViewHolder).setData(currentItem);
    }

    @Override
    public int getItemCount() {
        return (null != recyclerViewItemModelArrayList ? recyclerViewItemModelArrayList.size() : 0);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder
    {
        private TextView itemFirstTextView;
        private TextView itemSecondTextView;
        private TextView itemThirdTextView;
        private ImageView itemImageView;

        public ItemViewHolder(View itemView)
        {
            super(itemView);
            itemFirstTextView = itemView.findViewById(R.id.food_name);
            itemSecondTextView = itemView.findViewById(R.id.food_price);
            itemThirdTextView =itemView.findViewById(R.id.food_description);
            itemImageView = itemView.findViewById(R.id.food_image_view);
        }

        public void setData(Food recyclerViewItemModel)
        {
           itemFirstTextView.setText(recyclerViewItemModel.getName());
           itemSecondTextView.setText(recyclerViewItemModel.getDescription());
           itemThirdTextView.setText("Price : \u20B9 "+recyclerViewItemModel.getPrice());
           Glide.with(activity_context).load(recyclerViewItemModel.getThumbnail()).apply(RequestOptions.centerCropTransform()).into(itemImageView);
        }
    }
}
