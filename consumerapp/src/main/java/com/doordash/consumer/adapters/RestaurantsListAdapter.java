package com.doordash.consumer.adapters;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.doordash.consumer.ContextSingleton;
import com.doordash.consumer.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import Interfaces.OnLoadMoreListener;
import rest.models.Restaurant;

public class RestaurantsListAdapter extends RecyclerView.Adapter<RestaurantsListAdapter.RestaurantViewHolder> {

	private List<Restaurant> dataList;
	private Context context;
	private RecyclerView recyclerView;

	// The minimum amount of items to have below your current scroll position
	// before loading more.
	private int visibleThreshold = 5;
	private int lastVisibleItem, totalItemCount;
	private boolean loading;
	private OnLoadMoreListener onLoadMoreListener;

	public RestaurantsListAdapter(Context context, List<Restaurant> dataList, RecyclerView recyclerView) {
		this.context = context;
		this.dataList = dataList;
		this.recyclerView = recyclerView;

		if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

			final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
			this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
				@Override
				public void onScrolled(RecyclerView recyclerView,
									   int dx, int dy) {
					super.onScrolled(recyclerView, dx, dy);

					totalItemCount = linearLayoutManager.getItemCount();
					lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
					if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
						// End has been reached
						// Do something
						if (onLoadMoreListener != null) {
							onLoadMoreListener.onLoadMore();
						}
						loading = true;
					}
				}
			});
		}
	}

	class RestaurantViewHolder extends RecyclerView.ViewHolder {

		public final View mView;

		private TextView txtTitle;
		private TextView txtDescription;
		private ImageView coverImage;
		private TextView txtStatus;

		RestaurantViewHolder(View itemView) {
			super(itemView);
			mView = itemView;

			txtTitle = (TextView) mView.findViewById(R.id.title);
			coverImage = (ImageView) mView.findViewById(R.id.restaurant_img);
			txtDescription = (TextView) mView.findViewById(R.id.desc);
			txtStatus = (TextView) mView.findViewById(R.id.status);
		}
	}

	@Override
	public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		View view = layoutInflater.inflate(R.layout.list_item, parent, false);
		return new RestaurantViewHolder(view);
	}

	@Override
	public void onBindViewHolder(RestaurantViewHolder holder, int position) {
		Restaurant restaurant = dataList.get(position);

		holder.txtTitle.setText(restaurant.getName());
		holder.txtDescription.setText(restaurant.getDescription());
		holder.txtStatus.setText(restaurant.getStatus());

		Picasso picasso = ContextSingleton.getPicassoInstance(context);
		picasso.load(dataList.get(position).getImgUrl()).into(holder.coverImage);
	}

	@Override
	public int getItemCount() {
		return dataList.size();
	}

	public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
		this.onLoadMoreListener = onLoadMoreListener;
	}

	public void setLoaded() {
		this.loading = false;
	}
}
