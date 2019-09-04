package com.recycler.view.practical.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import android.app.ProgressDialog;
import android.view.View;
import android.widget.Toast;
import com.recycler.view.practical.model.Food;
import com.recycler.view.practical.model.FoodListModel;
import com.recycler.view.practical.R;
import com.recycler.view.practical.listener.RecyclerTouchListener;
import com.recycler.view.practical.adapter.RecyclerViewAdapter;
import com.recycler.view.practical.listener.RecyclerViewItemClickListener;
import com.recycler.view.practical.constants.Constants;
import com.recycler.view.practical.network.ApiService;
import com.recycler.view.practical.network.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Food> recyclerViewItemModelArrayList;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ProgressDialog progressDialog;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        progressDialog = new ProgressDialog(RecyclerViewActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
        setRecyclerViewItem();
    }

    private void setRecyclerViewItem()
    {
        recyclerView =findViewById(R.id.vertical_recyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearVertical = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearVertical);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerViewAdapter =new RecyclerViewAdapter(RecyclerViewActivity.this);
        recyclerView.setAdapter(recyclerViewAdapter);

        apiService = ApiUtils.getAPIService();
        Call<FoodListModel> call = apiService.getMyJSON();

        call.enqueue(new Callback<FoodListModel>()
        {
            @Override
            public void onResponse(Call<FoodListModel> call, Response<FoodListModel> response)
            {
                if (response.isSuccessful())
                {
                    recyclerViewItemModelArrayList = new ArrayList<>();
                    recyclerViewItemModelArrayList = response.body().getFoodList();
                    Toast.makeText(getApplicationContext(), "Size : "+recyclerViewItemModelArrayList.size(), Toast.LENGTH_SHORT).show();
                    recyclerViewAdapter.feedData(recyclerViewItemModelArrayList);
                    recyclerViewAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<FoodListModel> call, Throwable t)
            {
                progressDialog.dismiss();
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerViewItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                Intent detailIntent = new Intent(RecyclerViewActivity.this, DetailActivity.class);
                Food clickedItem = recyclerViewItemModelArrayList.get(position);
                detailIntent.putExtra(Constants.EXTRA_NAME, clickedItem.getName());
                detailIntent.putExtra(Constants.EXTRA_DESCRIPTION, clickedItem.getDescription());
                detailIntent.putExtra(Constants.EXTRA_PRICE, ""+clickedItem.getPrice());
                detailIntent.putExtra(Constants.EXTRA_THUMBNAIL, clickedItem.getThumbnail());
                startActivity(detailIntent);
            }

            @Override
            public void onItemLongClick(View view, int position)
            {
                Toast.makeText(getApplicationContext(), "onItemLongClick : "+position, Toast.LENGTH_SHORT).show();
            }
        }));
    }
}