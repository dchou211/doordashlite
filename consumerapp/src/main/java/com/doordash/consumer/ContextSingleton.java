package com.doordash.consumer;


import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import rest.agents.DataAgent;

public class ContextSingleton {
	private static Picasso picasso;
	private static DataAgent dataAgent;

	public static final int PAGE_SIZE = 20;

	public static Picasso getPicassoInstance (Context context) {
		if (picasso == null) {
			Picasso.Builder builder = new Picasso.Builder(context);
			builder.downloader(new OkHttp3Downloader(context));
			picasso = builder.build();
		}

		return picasso;
	}


	public static DataAgent getRestaurantAgent () {
		if (dataAgent == null) {
			dataAgent = new DataAgent();
		}
		return dataAgent;
	}
}
