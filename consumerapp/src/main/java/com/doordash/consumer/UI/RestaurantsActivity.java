package com.doordash.consumer.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.doordash.consumer.ContextSingleton;
import com.doordash.consumer.R;
import com.doordash.consumer.adapters.RestaurantsListAdapter;

import java.util.List;

import Interfaces.OnLoadMoreListener;
import rest.models.Restaurant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantsActivity extends AppCompatActivity {

	private RestaurantsListAdapter adapter;
	private RecyclerView recyclerView;

	private List<Restaurant> restaurantList;

	private int offset = 0;
	private final int PAGE_SIZE = ContextSingleton.PAGE_SIZE;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurants);

		retrieveData();
	}

	private void generateDataList(List<Restaurant> restaurantList) {
		recyclerView = (RecyclerView) findViewById(R.id.recycleView);
		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RestaurantsActivity.this);
		recyclerView.setLayoutManager(layoutManager);

		adapter = new RestaurantsListAdapter(this, restaurantList, recyclerView);

		adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
			@Override
			public void onLoadMore() {
				offset += PAGE_SIZE;
				retrieveData();
			}
		});

		recyclerView.setAdapter(adapter);
	}

	private void retrieveData () {
		ContextSingleton.getRestaurantAgent().getRestaurantsNearHQ(offset, PAGE_SIZE, new Callback<List<Restaurant>>() {
			@Override
			public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
				if(response.body() == null) {
					return;
				}

				if (restaurantList == null) {
					restaurantList = response.body();
					generateDataList(restaurantList);
				} else {
					restaurantList.addAll(response.body());
					adapter.notifyDataSetChanged();
					adapter.setLoaded();
				}
			}

			@Override
			public void onFailure(Call<List<Restaurant>> call, Throwable t) {
				Toast.makeText(RestaurantsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();

				// Allow retry to happen
				adapter.setLoaded();
				offset -= PAGE_SIZE;
			}
		});
	}
}
